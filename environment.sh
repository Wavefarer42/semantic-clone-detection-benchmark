#!/bin/bash

name=${PWD##*/}
dir_env="./env-${name}"
condash=$(which conda)

echo "Creating conda environment: ${name}"
eval "$(conda shell.bash hook)"
conda create -y --prefix ${dir_env}
conda activate ${dir_env}
conda install -y --override-channels -c main -c conda-forge  -c pytorch -c tallic \
    python=3.6 invoke=1.4 inquirer=2.6 pandas=0.25 selenium=3.141 beautifulsoup4=4.9
conda env export > environment.yml
exit 0