package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author andrewzta
 */
public class Dev4 {

    public static double run(int _n, int _k, double _u, double[] _ps) {
        double EPS = 1e-9;

        int n = _n;
        int k = _k;

        double u = _u;
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = _ps[i];
        }

        if (n != k) {
            return -1;
        }

        while (u > EPS) {
            Arrays.sort(p);

            int t = n;
            for (int i = 0; i < n - 1; i++) {
                if (p[i] < p[i + 1] - EPS) {
                    t = i + 1;
                    break;
                }
            }

            double z;
            if (t == n) {
                z = 1;
            } else {
                z = p[t];
            }

            double dv = z - p[0];
            if (dv * t > u) {
                dv = u / t;
            }

            for (int i = 0; i < t; i++) {
                p[i] += dv;
            }
            u -= dv * t;
        }

        double ans = 1;
        for (int i = 0; i < n; i++) {
            ans *= p[i];
        }

        return ans;
    }
}
