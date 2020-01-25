#!/bin/bash

echo "Creating conda environment"

conda create -n gradient-dataset-clones -c conda-forge python=3.6 -y
conda activate gradient-dataset-clones
conda install -c conda-forge invoke=1.3 pandas=0.25
exit 0