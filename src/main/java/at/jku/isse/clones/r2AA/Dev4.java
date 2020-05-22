package at.jku.isse.clones.r2AA;

import java.util.Arrays;

/**
 * @author pashka
 */
public class Dev4 {
    public static int run(int _n, int _p, int[] _g) {

        int n = _n;
        int p = _p;
        int[] c = new int[4];
        for (int i = 0; i < n; i++) {
            c[_g[i] % p]++;
        }
        int[][][] d = new int[c[1] + 1][c[2] + 1][c[3] + 1];
        for (int[][] ints : d) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, Integer.MIN_VALUE);
            }
        }
        d[c[1]][c[2]][c[3]] = c[0];
        int res = Integer.MIN_VALUE;
        for (int i = c[1]; i >= 0; i--) {
            for (int j = c[2]; j >= 0; j--) {
                for (int k = c[3]; k >= 0; k--) {
                    if (d[i][j][k] >= 0) {
                        if (i == 0 && j == 0 && k == 0) {
                            res = Math.max(res, d[i][j][k]);
                        } else {
                            res = Math.max(res, d[i][j][k] + 1);
                        }
                        for (int ii = i; ii >= 0; ii--) {
                            int s1 = (i - ii);
                            if (s1 > 3 * p) break;
                            for (int jj = j; jj >= 0; jj--) {
                                int s2 = s1 + 2 * (j - jj);
                                if (s2 > 3 * p) break;
                                for (int kk = k; kk >= 0; kk--) {
                                    int s3 = s2 + 3 * (k - kk);
                                    if (s3 > 3 * p) break;
                                    if (s3 > 0 && s3 % p == 0) {
                                        d[ii][jj][kk] = Math.max(d[ii][jj][kk], d[i][j][k] + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

}
