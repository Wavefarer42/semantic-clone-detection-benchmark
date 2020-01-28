import itertools
import operator
import pathlib
import re
import shutil

import pandas as pd
from invoke import task

DIR_THIS = pathlib.Path(__file__).parent
DIR_BUILD = pathlib.Path(DIR_THIS, "build")
FILE_INPUT = pathlib.Path(DIR_THIS, "gcj2017-input.csv.gz")
FILE_SOURCE = pathlib.Path(DIR_THIS, "gcj2017-source.csv.gz")
KEY_COLUMNS = ["Round", "RoundCategory", "Task", "Solution"]

df_input = pd.read_csv(FILE_INPUT)
df_source = pd.read_csv(FILE_SOURCE)


@task(name="source", default=True)
def source_extract(c, round=None, category=None, task=None, solution=None, max=10, per_task=True, seed=14):
    assert max > 0

    df = pd.read_csv(FILE_SOURCE)
    df = _source_filter(df, round, category, task, solution)

    if df.shape[0] > 0:
        if per_task:
            df = (df.groupby(KEY_COLUMNS)
                  .apply(lambda x: x.sample(min(max, len(x)), random_state=seed)))
        else:
            df = df.sample(max, random_state=seed)

        groupby_getter = operator.attrgetter(*KEY_COLUMNS)
        for groupby_key, rows in itertools.groupby(df.itertuples(), groupby_getter):
            key = "-".join([str(it) for it in groupby_key])
            dir_problem = pathlib.Path(DIR_BUILD, key)
            for it in rows:
                dir_solution = pathlib.Path(dir_problem, it.DeveloperId)

                dir_solution.mkdir(parents=True, exist_ok=True)

                content = re.sub("package .*;", "", it.FileContent)
                with pathlib.Path(dir_solution, it.File).open("w") as f:
                    f.write(content)
    else:
        print(f"No valid solution for given filters: "
              f"round={round}, category={category}, task={task}, solution={solution}")


def _source_filter(df, round, category, task, solution) -> pd.DataFrame:
    if round is not None:
        df = df[df.Round == round]
    if category is not None:
        df = df[df.RoundCategory == category]
    if task is not None:
        df = df[df.Task == task]
    if solution is not None:
        df = df[df.Solution == solution]
    return df


@task(name="update")
def source_update(c, round=None, category=None, task=None, solution=None, developer=None):
    df = pd.read_csv(FILE_SOURCE).set_index(KEY_COLUMNS + ["DeveloperId"])

    dir_solution = pathlib.Path(DIR_BUILD, f"{round}-{category}-{task}-{solution}/{developer}")

    df_key = (round, category, task, int(solution), developer)
    if df_key in df.index:
        file_solution = pathlib.Path(dir_solution, df.loc[df_key].iloc[0].File)
        if file_solution.exists():
            with file_solution.open("r") as f:
                df.at[df_key, "FileContent"] = f.read()

            df.reset_index().to_csv(FILE_SOURCE, index=False)
        else:
            print("Solution cannot be updated because the new solution file does not exist.")
    else:
        print("Solution cannot be updated because the target entry does not exist.")


@task(name="trigger")
def trigger_extract(c, round=None, category=None, task=None, solution=None):
    df = pd.read_csv(FILE_INPUT)
    df = _source_filter(df, round, category, task, solution)

    for it in df.itertuples():
        dir_problem = pathlib.Path(DIR_BUILD, f"{it.Round}-{it.RoundCategory}-{it.Task}-{it.Solution}")
        dir_problem.mkdir(parents=True, exist_ok=True)

        with pathlib.Path(dir_problem, "input.txt").open("w") as f:
            f.write(it.Input)


@task(name="run")
def run(c, round=None, category=None, task=None, solution=None, developer=None):
    pass


@task
def clean(c):
    shutil.rmtree(DIR_BUILD, ignore_errors=True)
