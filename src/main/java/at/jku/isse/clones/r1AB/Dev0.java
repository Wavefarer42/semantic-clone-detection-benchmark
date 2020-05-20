package at.jku.isse.clones.r1AB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author anton.akhi
 */
public class Dev0 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int n = _ingredientsCount;
        int p = _packagesCount;
        int[] c = new int[n];
        for (int i = 0; i < c.length; i++) {
            c[i] = _ingredientQuantities[i];
        }
        int[][] a = new int[n][p];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = _packages[i][j];
            }
            Arrays.sort(a[i]);
        }
        int[] pointer = new int[n];
        int ans = 0;
        loop: while (true) {
            for (int i = 0; i < pointer.length; i++) {
                if (pointer[i] >= a[i].length) {
                    break loop;
                }
            }
            int low = 0;
            int high = Integer.MAX_VALUE / 2;
            for (int i = 0; i < pointer.length; i++) {
                int lowi = (a[i][pointer[i]] * 100 + 110 * c[i] - 1) / (110 * c[i]);
                int highi = (a[i][pointer[i]] * 100) / (90 * c[i]);
                low = Math.max(low, lowi);
                high = Math.min(high, highi);
            }
            if (low <= high) {
                ans++;
                for (int i = 0; i < pointer.length; i++) {
                    pointer[i]++;
                }
            } else {
                for (int i = 0; i < pointer.length; i++) {
                    int lowi = (a[i][pointer[i]] * 100 + 110 * c[i] - 1) / (110 * c[i]);
                    int highi = (a[i][pointer[i]] * 100) / (90 * c[i]);
                    if (highi < low) {
                        pointer[i]++;
                    }
                }
            }
        }

        return ans;
    }

}

