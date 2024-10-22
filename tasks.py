import itertools
import operator
import pathlib
import re
import shutil
import subprocess
import time

import inquirer
import numpy as np
import pandas as pd
from invoke import task

DIR_THIS = pathlib.Path(__file__).parent
DIR_DATA = pathlib.Path(DIR_THIS, "data")
DIR_BUILD = pathlib.Path(DIR_THIS, "build")
FILE_INPUT = pathlib.Path(DIR_THIS, "2017-gcj-input.csv.gz")
FILE_SOURCE = pathlib.Path(DIR_THIS, "2017-gcj-source.csv.gz")
FILE_GRADIENT_AGENT = pathlib.Path(DIR_THIS, "lib/gradient-client-jvm-0.1.0-all.jar")
KEY_COLUMNS = ["Round", "Category", "Task", "Solution"]


@task(name="sourcei", default=True)
def source_extract_interactive(c):
    datasets = {it["name"]: it for it in c.config["dataset"]}
    questions = [inquirer.List("dataset", "Which dataset?", sorted(datasets.keys()), carousel=True)]
    dataset_key = inquirer.prompt(questions)["dataset"]

    dataset = datasets[dataset_key]
    actions = {
        "gcj": _source_gcj
    }

    actions[dataset["action"]](dataset)


def _source_gcj(dataset):
    df_source = pd.read_csv(DIR_DATA.joinpath(dataset["file_source"]))
    df_input = pd.read_csv(DIR_DATA.joinpath(dataset["file_input"]))

    rounds = np.unique(df_source.Round).tolist()
    categories = np.unique(df_source[df_source.Round.isin(rounds)].Category).tolist()
    tasks = np.unique(df_source[df_source.Round.isin(rounds) & df_source.Category.isin(categories)].Task).tolist()

    questions = [inquirer.List("round", "Which rounds?", rounds),
                 inquirer.List("category", "Which categories?", categories),
                 inquirer.List("task", "Which tasks?", tasks),
                 inquirer.Text("minimum", "Minimum Score?", "0")]
    answer = inquirer.prompt(questions)

    _source_extract(df_source, df_input, answer["round"], answer["category"], answer["task"], 0, int(answer["minimum"]))


@task(name="source")
def source_extract(c, round=None, category=None, task=None, solution=None):
    df_source = pd.read_csv(DIR_DATA.joinpath(FILE_SOURCE))
    df_input = pd.read_csv(FILE_INPUT.joinpath(FILE_INPUT))

    _source_extract(df_source, df_input, round, category, task, solution)


def _source_extract(df_source, df_input, round=None, category=None, task=None, solution=None, score=None):
    df_source = _source_filter(df_source, round, category, task, solution, score)
    df_input = _source_filter(df_input, round, category, task, solution, score).set_index(KEY_COLUMNS)

    if df_source.shape[0] > 0:
        groupby_getter = operator.attrgetter(*KEY_COLUMNS)
        for groupby_key, rows in itertools.groupby(df_source.itertuples(), groupby_getter):
            key = "-".join([str(it) for it in groupby_key])
            dir_problem = pathlib.Path(DIR_BUILD, key)
            for it in rows:
                dir_solution = pathlib.Path(dir_problem, it.Developer)

                dir_solution.mkdir(parents=True, exist_ok=True)

                content = re.sub("package .*;", "", it.FileContent)
                with pathlib.Path(dir_solution, it.File).open("w") as f:
                    f.write(content)

            with pathlib.Path(dir_problem, "input.txt").open("w") as f:
                f.write(df_input.loc[groupby_key].Input)

    else:
        print(f"No valid solution for given filters: "
              f"round={round}, category={category}, task={task}, solution={solution}")


def _source_filter(df, round, category, task, solution, score=None) -> pd.DataFrame:
    if round is not None:
        df = df[df.Round == round]
    if category is not None:
        df = df[df.Category == category]
    if task is not None:
        df = df[df.Task == task]
    if solution is not None:
        df = df[df.Solution == solution]
    if score is not None and "Score" in df.columns:
        df = df[df.Score >= score]
    return df


@task(name="update")
def source_update(c, round=None, category=None, task=None, solution=None):
    df = pd.read_csv(FILE_SOURCE)

    df_filtered = _source_filter(df, round, category, task, int(solution) if solution is not None else solution)

    for it in df_filtered.itertuples():
        file_solution = pathlib.Path(DIR_BUILD,
                                     f"{it.Round}-{it.Category}-{it.Task}-{it.Solution}/{it.Developer}/{it.File}")
        if file_solution.exists():
            with file_solution.open("r") as f:
                df.at[it.Index, "FileContent"] = f.read()

    df.to_csv(FILE_SOURCE, index=False)


@task(name="trigger")
def trigger_extract(c, round=None, category=None, task=None, solution=None):
    df = pd.read_csv(FILE_INPUT)
    df = _source_filter(df, round, category, task, solution)

    for it in df.itertuples():
        dir_problem = pathlib.Path(DIR_BUILD, f"{it.Round}-{it.Category}-{it.Task}-{it.Solution}")
        dir_problem.mkdir(parents=True, exist_ok=True)

        with pathlib.Path(dir_problem, "input.txt").open("w") as f:
            f.write(it.Input)


@task(name="run")
def run(c, round=None, category=None, task=None, solution=None, developer=None):
    if not (shutil.which("javac") and shutil.which("java")):
        print("No javac or java available")

    df = pd.read_csv(FILE_SOURCE).set_index(KEY_COLUMNS + ["Developer"])
    df = df.loc[round, category, task, int(solution), developer]
    if isinstance(df, pd.Series) and len(df) > 0:
        file = pathlib.Path(DIR_BUILD, f"{round}-{category}-{task}-{solution}/{developer}/{df.File}")
        if file.exists():
            subprocess.run(["javac", str(file.absolute())])
            name_artifact = f"{round}-{category}-{task}-{solution}"
            regex_cp = ".*PrintStream.*"
            arg_agent = f"-g gcj -a {name_artifact} -v {developer} -r {file.stem} -c {regex_cp}"
            arg_java_agent = f"-javaagent:{FILE_GRADIENT_AGENT}=\"{arg_agent}\""
            with subprocess.Popen(["java", arg_java_agent, "-cp", file.parent, file.stem],
                                  stdout=subprocess.PIPE, cwd=file.parent) as proc:
                print(proc.stdout.read())
        else:
            print("File not found")
    else:
        print("Solution not found")


@task
def clean(c):
    shutil.rmtree(DIR_BUILD, ignore_errors=True)


@task
def evaluate(c):
    """
    Runs all the project benchmarks and applies the given tag in the evaluation.
    """

    time_start = time.time()
    cmd_clean = f"./gradlew clean"
    cmd_code = f"./gradlew --rerun-tasks --no-build-cache codeAnalysis"
    cmd_assemble = f"./gradlew  --rerun-tasks --no-build-cache assemble"
    cmd_run = f"./gradlew run"

    with c.cd(str(DIR_THIS.absolute())):
        print(f"Cmd: {cmd_clean}")
        c.run(cmd_clean, warn=True)
        print(f"Cmd: {cmd_code}")
        c.run(cmd_code, warn=True)
        print(f"Cmd: {cmd_assemble}")
        c.run(cmd_assemble, warn=True)
        print(f"Cmd: {cmd_run}")
        c.run(cmd_run, warn=True)

    print(f"Finished in {(time.time() - time_start) / 60} minutes")
