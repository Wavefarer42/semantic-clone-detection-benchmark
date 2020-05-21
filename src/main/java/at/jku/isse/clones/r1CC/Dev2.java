package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author aditsu
 */
public class Dev2 {
    public static double run(int _n, int _k, double _u, double[] _ps) {
        final int n = _n;
        //		final int k =
//        sc.nextInt();
        double u = _u;
        final double[] p = new double[n];
        for (int i = 0; i < n; ++i) {
            p[i] = _ps[i];
        }
        Arrays.sort(p);
        while (u > 1e-14) {
            int i = 1;
            while (i < n && p[i] == p[i - 1]) {
                i++;
            }
            double next = i == n ? 1 : p[i];
            double amt = Math.min(u, (next - p[0]) * i);
            for (int j = 0; j < i; ++j) {
                p[j] += amt / i;
            }
            u -= amt;
        }
        double r = 1;
        for (int i = 0; i < n; ++i) {
            r *= p[i];
        }
        return r;
    }
}
