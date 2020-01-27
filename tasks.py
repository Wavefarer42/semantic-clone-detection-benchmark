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

df_input = pd.read_csv(FILE_INPUT)
df_source = pd.read_csv(FILE_SOURCE)


@task(default=True)
def extract(c, round=None, category=None, task=None, solution=None, max=10, per_task=True, seed=14):
    assert max > 0
    groupby_cols = ["Round", "RoundCategory", "Task", "Solution"]

    df = pd.read_csv(FILE_SOURCE)
    df = _filter_sources(df, round, category, task, solution)

    if df.shape[0] > 0:
        if per_task:
            df = (df.groupby(groupby_cols)
                  .apply(lambda x: x.sample(min(max, len(x)), random_state=seed)))
        else:
            df = df.sample(max, random_state=seed)

        groupby_getter = operator.attrgetter(*groupby_cols)
        for groupby_key, rows in itertools.groupby(df.itertuples(), groupby_getter):
            key = "-".join([str(it) for it in groupby_key])
            dir_problem = pathlib.Path(DIR_BUILD, key)
            for it in rows:
                dir_solution = pathlib.Path(dir_problem, it.DeveloperId)

                dir_solution.mkdir(parents=True, exist_ok=True)

                content = re.sub("package .*;", "", it.FileContent)
                with pathlib.Path(dir_solution, it.File).open("w") as files:
                    files.write(content)
    else:
        print(f"No valid solution for given filters: "
              f"round={round}, category={category}, task={task}, solution={solution}")


def _filter_sources(df, round, category, task, solution) -> pd.DataFrame:
    if round is not None:
        df = df[df.Round == round]
    if category is not None:
        df = df[df.RoundCategory == category]
    if task is not None:
        df = df[df.Task == task]
    if solution is not None:
        df = df[df.Solution == solution]
    return df


@task
def clean(c):
    shutil.rmtree(DIR_BUILD, ignore_errors=True)
