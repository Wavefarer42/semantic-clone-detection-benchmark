import pathlib

import pandas as pd

DIR_THIS = pathlib.Path(__file__).parent
FILE_INPUT = pathlib.Path(DIR_THIS, "gcj2017-input.csv.gz")
FILE_SOURCE = pathlib.Path(DIR_THIS, "gcj2017-source.csv.gz")

df_input = pd.read_csv(FILE_INPUT)
df_source = pd.read_csv(FILE_SOURCE)
