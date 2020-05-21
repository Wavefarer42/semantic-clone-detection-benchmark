package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author AndreySiunov
 */
public class Dev5 {
    public static double run(int _n, int _k, double _u, double[] _ps) {

        int n = _n;
        int k = _k;
        int u = r(_u);
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = r(_ps[i]);
        }
        if (n != k) {
            return -1;
        }
        Arrays.sort(p);
        while (u > 0) {
            int cr = p[0];
            for (int i = 0; i < n && p[i] == cr && u > 0; i++) {
                p[i]++;
                u--;
            }
        }
        double res = 1.0;
        for (int i = 0; i < n; i++) {
            res *= p[i] / 10000.0;
        }

        return res;
    }
    private static int r(double r) {
        double d = r;
        //        if ((int) (d * 10000 + 0.01) != (int) (d * 10000)){
        //            return 0;
        //        }
        return (int) (d * 10000 + 0.01);
    }
}
