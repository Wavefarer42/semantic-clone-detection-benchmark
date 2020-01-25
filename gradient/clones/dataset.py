import pathlib

import pandas as pd



def extract_sources(dir_output: str):
    dir_output = pathlib.Path(dir_output)
    df_meta = pd.DataFrame(FILE_META)
    df_source = pd.DataFrame(FILE_SOURCE)

