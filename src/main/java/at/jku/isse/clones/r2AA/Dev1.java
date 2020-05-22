package at.jku.isse.clones.r2AA;

/**
 * @author darnley
 */
public class Dev1 {
    public static int run(int _n, int _p, int[] _g) {
        int n = _n;
        int p = _p;


        int[] a = new int[4];
        for (int i = 0; i < n; i++) {
            a[_g[i] % p]++;
        }
        int[][][][] m = new int[a[1] + 1][a[2] + 1][a[3] + 1][p];
        for (int i = 0; i <= a[1]; i++) {
            for (int j = 0; j <= a[2]; j++) {
                for (int k = 0; k <= a[3]; k++) {
                    for (int t = 0; t < p; t++) {
                        int w = (t == 0) ? 1 : 0;
                        if (i > 0) m[i][j][k][t] = Math.max(m[i][j][k][t], w + m[i - 1][j][k][(t + 1) % p]);
                        if (j > 0) m[i][j][k][t] = Math.max(m[i][j][k][t], w + m[i][j - 1][k][(t + 2) % p]);
                        if (k > 0) m[i][j][k][t] = Math.max(m[i][j][k][t], w + m[i][j][k - 1][(t + 3) % p]);
                    }
                }
            }
        }
        return a[0] + m[a[1]][a[2]][a[3]][0];
    }

}
