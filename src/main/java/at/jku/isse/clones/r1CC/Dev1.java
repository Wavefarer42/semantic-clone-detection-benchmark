package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author a3636tako
 */
public class Dev1 {
    public static double run(int _n, int _k, double _u, double[] _ps) {
        int N = _n;
        int K = _k;
        double U = _u;
        double[] P = new double[51];
        for (int i = 0; i < N; i++) {
            P[i] = _ps[i];
        }
        Arrays.sort(P, 0, N);
        P[N] = 1.0;
        for (int i = 0; i < N; i++) {
            double diff = P[i + 1] - P[i];
            if ((i + 1) * diff <= U) {
                U -= (i + 1) * diff;
                for (int j = 0; j <= i; j++) {
                    P[j] = P[i + 1];
                }
            } else {
                diff = U / (i + 1);
                for (int j = 0; j <= i; j++) {
                    P[j] += diff;
                }
                break;
            }
        }
        double ans = 1;
        for (int i = 0; i < N; i++) {
            ans *= P[i];
        }
        return ans;
    }
}
