import os
import pathlib

replacement = {
    "at": "a",
    "jku": "b",
    "isse": "c",
}

dir_in = pathlib.Path("../src")
dir_out = pathlib.Path("out")


def clean_file(file_in: pathlib.Path, file_out: pathlib.Path):
    content_in = file_in.read_text()
    content_out = content_in.replace("at.jku.isse", "a.b.c")
    file_out.parent.mkdir(parents=True, exist_ok=True)
    file_out.write_text(content_out)


for dir_name, sub_dirs, file_list in os.walk(dir_in):
    path_cur_in = pathlib.Path(dir_name)
    path_new_out = pathlib.Path(dir_out, *path_cur_in.parts[1:])
    for file_name in file_list:
        clean_file(pathlib.Path(path_cur_in, file_name), pathlib.Path(path_new_out, file_name))