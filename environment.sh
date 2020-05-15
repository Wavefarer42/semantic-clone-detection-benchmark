#!/bin/bash

name=${PWD##*/}
dir_env="./env-${name}"
condash=$(which conda)

echo "Creating conda environment: ${name}"
eval "$(conda shell.bash hook)"
conda create -y --prefix ${dir_env}
conda activate ${dir_env}
conda install -y --override-channels -c main -c conda-forge  -c pytorch -c tallic \
    python=3.6 invoke=1.3 pandas=0.25
conda env export > environment.yml
exit 0