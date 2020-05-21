package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author alex700
 */
public class Dev3 {
    public static double run(int _n, int _k, double _u, double[] _ps) {
        int n = _n;
        int k = _k;
        if (n == k) {
            double u = _u;
            double[] p = new double[n];
            Arrays.setAll(p, i -> _ps[i]);
            double all = Arrays.stream(p).sum() + u;
            Arrays.sort(p);
            double ans = 1;
            for (int i = p.length - 1; i >= 0; --i) {
                if (p[i] * (i + 1) <= all) {
                    double avg = all / (i + 1);
                    for (int j = 0; j < i + 1; j++) {
                        ans *= avg;
                    }
                    break;
                } else {
                    ans *= p[i];
                    all -= p[i];
                }
            }
            return ans;
        } else {
//            in.nextDouble();
            return -1;
        }
    }
}
