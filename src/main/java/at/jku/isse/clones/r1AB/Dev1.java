package at.jku.isse.clones.r1AB;

/**
 * @author 0phi
 */
public class Dev1 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int numberOfIngredients = _ingredientsCount;
        int numberOfPackages = _packagesCount;

        int[] quantityOfIngredientsNeeded = _ingredientQuantities;

        int[][] ingredientsToPackageWeight = _packages;


        // Update max count
        for (int ingredient = 0; ingredient < numberOfIngredients; ingredient++) {
            for (int pkg = 0; pkg < numberOfPackages; pkg++) {

                if (ingredientsToPackageWeight[ingredient][pkg] % quantityOfIngredientsNeeded[ingredient]
                        != 0) {

                    int temp =
                            ingredientsToPackageWeight[ingredient][pkg] / quantityOfIngredientsNeeded[ingredient];

                    if (((
                            ingredientsToPackageWeight[ingredient][pkg] - (quantityOfIngredientsNeeded[ingredient]
                                    * (temp + 1) * 0.9)) >= 0) && ((
                            ingredientsToPackageWeight[ingredient][pkg] - (quantityOfIngredientsNeeded[ingredient]
                                    * (temp + 1) * 1.1)) <= 0)) {
                        ingredientsToPackageWeight[ingredient][pkg] = temp + 1;
                    } else if (((
                            ingredientsToPackageWeight[ingredient][pkg] - (quantityOfIngredientsNeeded[ingredient]
                                    * (temp) * 0.9)) >= 0) && ((
                            ingredientsToPackageWeight[ingredient][pkg] - (quantityOfIngredientsNeeded[ingredient]
                                    * (temp) * 1.1)) <= 0)) {
                        ingredientsToPackageWeight[ingredient][pkg] = temp;
                    } else {
                        ingredientsToPackageWeight[ingredient][pkg] = -2;
                    }
                } else {
                    ingredientsToPackageWeight[ingredient][pkg] =
                            ingredientsToPackageWeight[ingredient][pkg] / quantityOfIngredientsNeeded[ingredient];
                }
            }
        }

        int total = 0;
        for (int pkg = 0; pkg < numberOfPackages; pkg++) {
            if (ingredientsToPackageWeight[0][pkg] != -2 &&
                    ifAllPresent(numberOfIngredients, numberOfPackages, ingredientsToPackageWeight[0][pkg],
                            ingredientsToPackageWeight)) {
                total++;

                resetAll(numberOfIngredients, numberOfPackages, ingredientsToPackageWeight[0][pkg],
                        ingredientsToPackageWeight);
                ingredientsToPackageWeight[0][pkg] = -1;
            }
        }

        return total;
    }

    private static boolean resetAll(int rows, int columns, int value, int[][] content) {
        for (int r = 1; r < rows; r++) {
            int res = isPresent(r, columns, value, content);

            content[r][res] = -1;
        }

        return true;
    }

    private static boolean ifAllPresent(int rows, int columns, int value, int[][] content) {
        for (int r = 1; r < rows; r++) {
            int res = isPresent(r, columns, value, content);

            if (res == -3) {
                return false;
            }
        }

        return true;
    }


    private static int isPresent(int row, int columns, int value, int[][] content) {
        for (int col = 0; col < columns; col++) {
            if (content[row][col] == value) {
                return col;
            }
        }

        return -3;
    }
}
