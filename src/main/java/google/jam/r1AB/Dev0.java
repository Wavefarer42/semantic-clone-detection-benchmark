package google.jam.r1AB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author bit24
 */
public class Dev0 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        ArrayList<ArrayList<Integer>> palette = new ArrayList<>();
        for (int i = 0; i < _packages.length; i++) {
            ArrayList<Integer> l = new ArrayList<>();
            for (int i1 = 0; i1 < _packages[i].length; i1++) {
                l.add(_packages[i][i1]);
            }
            palette.add(l);
        }

        return solveLine(_ingredientsCount, _packagesCount, palette, _ingredientQuantities);
    }

    private static int solveLine(int ingredients, int packages, ArrayList<ArrayList<Integer>> palette, int[] serving) {
        int kits = 0;
        //Delete Packages that won't fit at all
        for (int i = 0; i < ingredients; i++) {
            ArrayList<Integer> newPackages = new ArrayList<>();
            for (int j = 0; j < packages; j++) {
                Integer size = palette.get(i).get(j);
                int bestFitServings = Math.round(((float) size) / serving[i]);
                float originalSize = bestFitServings * serving[i];
                float factor = size / originalSize;

                if (factor <= 1.1f && factor >= 0.9f) {
                    newPackages.add(size);
                }
            }
            palette.get(i).clear();
            palette.get(i).addAll(newPackages);
        }

        //Sort Packages
        for (int i = 0; i < ingredients; i++) {
            Collections.sort(palette.get(i));
        }

        int[] currentPackage = new int[ingredients];
        int[] maxPackage = new int[ingredients];

        for (int i = 0; i < ingredients; i++) {
            maxPackage[i] = palette.get(i).size();
        }

        for (int i = 0; i < maxPackage[0]; i++) {
            //check if all ingredients are available
            boolean allAvailable = true;
            for (int x = 0; x < ingredients; x++) {
                if (currentPackage[x] >= maxPackage[x])
                    allAvailable = false;
            }

            if (!allAvailable)
                break;

            int currentWeight = palette.get(0).get(i);
            int optimalServings = Math.round(((float) palette.get(0).get(i)) / serving[0]);
            int minimalServings = optimalServings;
            int maximalServings = optimalServings;

            while (((float) currentWeight) / ((minimalServings - 1) * serving[0]) <= 1.1f && minimalServings > 1) {
                minimalServings--;
            }

            while (((float) currentWeight) / ((maximalServings + 1) * serving[0]) >= 0.9f) {
                maximalServings++;
            }

            int[] currentServing = new int[ingredients];
            currentServing[0] = i;

            boolean canServe = true;
            for (int j = 1; j < ingredients; j++) {
                boolean canServeIngredient = false;
                int servedPackage = currentPackage[j];
                for (; servedPackage < palette.get(j).size(); servedPackage++) {

                    int possibleWeight = palette.get(j).get(servedPackage);
                    int possibleOptimalServings = Math.round(((float) palette.get(j).get(servedPackage)) / serving[j]);
                    int possibleMinimalServings = possibleOptimalServings;
                    int possibleMaximalServings = possibleOptimalServings;


                    while (((float) currentWeight) / ((minimalServings - 1) * serving[0]) <= 1.1f && minimalServings > 1) {
                        minimalServings--;
                    }

                    while (((float) currentWeight) / ((maximalServings + 1) * serving[0]) >= 0.9f) {
                        maximalServings++;
                    }


                    while (((float) possibleWeight) / ((possibleMinimalServings - 1) * serving[j]) <= 1.1f && possibleMinimalServings > 1) {
                        possibleMinimalServings--;
                    }

                    while (((float) possibleWeight) / ((possibleMaximalServings + 1) * serving[j]) >= 0.9f) {
                        possibleMaximalServings++;
                    }

                    if (possibleMinimalServings <= maximalServings && possibleMaximalServings >= minimalServings) {
                        canServeIngredient = true;
                        currentServing[j] = servedPackage;
                        if (possibleMinimalServings > minimalServings) {
                            minimalServings = possibleMinimalServings;
                        }
                        if (possibleMaximalServings < maximalServings) {
                            maximalServings = possibleMaximalServings;
                        }
                        break;
                    }
                }
                if (!canServeIngredient) {
                    canServe = false;
                    break;
                }
            }
            if (canServe) {
                kits++;
                for (int x = 0; x < ingredients; x++) {
                    currentPackage[x] = currentServing[x] + 1;
                }
            }
        }

        return kits;
    }
}

