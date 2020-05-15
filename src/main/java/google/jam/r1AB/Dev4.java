package google.jam.r1AB;

import java.util.Arrays;

/**
 * @author a2stnk
 */
public class Dev4 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {
        int N = _ingredientsCount, P = _packagesCount;
        int[] R = _ingredientQuantities;
        int[][] Q = _packages;

        for (int i = 0; i < N; i++)
            Arrays.sort(Q[i]);

        int ans = 0;
        int[] used = new int[N];
        boolean nomore = false;
        int num = 1;
        while (!nomore) {
            int[] use = new int[N];

            boolean ok = true;
            for (int i = 0; i < N; i++) {
                int j = used[i];
                boolean found = false;
                for (; j < P; j++) {
                    if (9 * (long) R[i] * num <= 10 * Q[i][j]) {
                        if (10 * Q[i][j] <= 11 * (long) R[i] * num)
                            found = true;
                        break;
                    } else {
                        used[i] = j + 1;
                    }
                }
                if (found) {
                    use[i] = j + 1;
                } else {
                    ok = false;
                    if (j == P)
                        nomore = true;
                    break;
                }
            }

            if (ok) {
                ans++;
                used = use;
            } else {
                num++;
            }
        }

        return ans;
    }
}
