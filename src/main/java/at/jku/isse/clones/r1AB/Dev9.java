package at.jku.isse.clones.r1AB;

import java.util.Arrays;

/**
 * @author pashka
 */
public class Dev9 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int n = _ingredientsCount;
        int p = _packagesCount;
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = _ingredientQuantities[i];
        }
        int[][] q = new int[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                q[i][j] = _packages[i][j];
            }
            Arrays.sort(q[i]);
        }
        int res = 0;
        int[] c = new int[n];
        while (true) {
            int lower = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                // q[i][j] <= r[i] * x * 1.1
                lower = Math.max(lower, (q[i][c[i]] * 10 + r[i] * 11 - 1) / (r[i] * 11));
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                // q[i][j] >= r[i] * x * .9
                if (q[i][c[i]] * 10 / (r[i] * 9) < lower) {
                    c[i]++;
                    ok = false;
                }
            }
            if (ok) {
                res++;
                for (int i = 0; i < n; i++) {
                    c[i]++;
                }
            }
            ok = true;
            for (int i = 0; i < n; i++) {
                if (c[i] == p) ok = false;
            }
            if (!ok) break;
        }
        return res;
    }
}