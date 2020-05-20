package at.jku.isse.clones.r1CA;

/**
 * @author gojira
 */
public class Dev8 {

    public static double run(int _n, int _k, int[] _r, int[] _h) {

        int n = _n;
        int k = _k;
        int[] r = new int[n];
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = _r[i];
            h[i] = _h[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (r[j] < r[j + 1]) {
                    int x = r[j];
                    r[j] = r[j + 1];
                    r[j + 1] = x;
                    x = h[j];
                    h[j] = h[j + 1];
                    h[j + 1] = x;
                }
            }
        }
        double[][] ans = new double[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                ans[i + 1][j] = Math.max(ans[i + 1][j], ans[i][j]);
                if (j == k) continue;
                ans[i + 1][j + 1] = Math.max(ans[i + 1][j + 1], ans[i][j] + 2 * Math.PI * r[i] * h[i] + (j == 0 ? Math.PI * r[i] * r[i] : 0));
            }
        }

        return ans[n][k];
    }
}
