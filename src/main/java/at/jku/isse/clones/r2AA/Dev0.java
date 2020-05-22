package at.jku.isse.clones.r2AA;

/**
 * @author 2rf
 */
public class Dev0 {
    public static int run(int _n, int _p, int[] _g) {

        int n = _n;
        int p = _p;
        int[] a = new int[4];
        for (int i = 0; i < n; i++) {
            int x = _g[i];
            a[x % p]++;
        }

        int[][][] dp = new int[a[1] + 1][a[2] + 1][a[3] + 1];
        dp[0][0][0] = a[0];
        for (int i = 0; i <= a[1]; i++) {
            for (int j = 0; j <= a[2]; j++) {
                for (int k = 0; k <= a[3]; k++) {
                    int delta = (i + 2 * j + 3 * k) % p == 0 ? 1 : 0;
                    if (i < a[1]) {
                        dp[i + 1][j][k] = Math.max(dp[i + 1][j][k], dp[i][j][k]
                                + delta);
                    }
                    if (j < a[2]) {
                        dp[i][j + 1][k] = Math.max(dp[i][j + 1][k], dp[i][j][k]
                                + delta);
                    }
                    if (k < a[3]) {
                        dp[i][j][k + 1] = Math.max(dp[i][j][k + 1], dp[i][j][k]
                                + delta);
                    }
                }
            }
        }

        return dp[a[1]][a[2]][a[3]];
    }
}
