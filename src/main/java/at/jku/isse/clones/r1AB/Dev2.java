package at.jku.isse.clones.r1AB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * @author ckcz123
 */
public class Dev2 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {
        int n = _ingredientsCount, p = _packagesCount;
        int[] origin = new int[n];
        for (int i = 0; i < n; i++) origin[i] = _ingredientQuantities[i];
        int maxorigin = IntStream.of(origin).max().getAsInt();

        ArrayList<Integer>[] ingredients = new ArrayList[n];
        int maxindredient = 0;
        for (int i = 0; i < n; i++) {
            ingredients[i] = new ArrayList<>();
            for (int j = 0; j < p; j++)
                ingredients[i].add(_packages[i][j]);
            Collections.sort(ingredients[i]);
            maxindredient = Math.max(maxindredient, ingredients[i].get(p - 1));
        }

        int res = 0;
        for (int size = 1; maxorigin * size <= maxindredient / .9; ) {
            int[] need = new int[n];
            for (int i = 0; i < n; i++) need[i] = origin[i] * size;
            int[] use = new int[n];
            boolean able = true;
            for (int i = 0; i < n; i++) {
                for (int x : ingredients[i]) {
                    if (x >= need[i] * .9 && x <= need[i] * 1.1) {
                        use[i] = x;
                        break;
                    }
                }
                if (use[i] == 0) {
                    able = false;
                    break;
                }
            }
            if (!able) {
                size++;
                continue;
            }
            res++;
            for (int i = 0; i < n; i++) {
                ingredients[i].remove(Integer.valueOf(use[i]));
            }
        }
        return res;
    }
}
