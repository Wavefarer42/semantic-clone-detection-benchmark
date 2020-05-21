package at.jku.isse.clones.r1CC;

import java.util.*;

/**
 * @author Derek.Jiang
 */
public class Dev8 {
    public static double run(int _n, int _k, double _u, double[] _ps) {

        int n = _n;
        int k = _k;
        double u = _u;
        double[] p = new double[n];
        for (int i = 0; i < n; i++) {
            p[i] = _ps[i];
        }
        Arrays.sort(p);
        double limit = -1;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || (p[i + 1] - p[i]) * (i + 1) > u) {
                limit = u / (i + 1) + p[i];
                break;
            }
            else {
                u -= (p[i + 1] - p[i]) * (i + 1);
            }
        }
        double poss = 1;
        for (int i = 0; i < n; i++) {
            if (p[i] < limit) poss *= limit;
            else poss *= p[i];
        }
        return poss;
    }
}
