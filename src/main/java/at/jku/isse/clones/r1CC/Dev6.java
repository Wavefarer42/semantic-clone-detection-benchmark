package at.jku.isse.clones.r1CC;

/**
 * @author DarLam
 */
public class Dev6 {
    public static double run(int _n, int _k, double _u, double[] _ps) {
        int n = _n;
        double[] p = _ps;
        double u = _u;

        double l = 0.0;
        double r = 1.0;
        while (true) {
            double m = (l + r) / 2;
            if (m == l || m == r) {
                break;
            }

            double sum = 0;
            for (int i = 0; i < p.length; i++) {
                if (p[i] < m) {
                    sum += m - p[i];
                }
            }

            if (sum <= u) {
                l = m;
            } else {
                r = m;
            }
        }
        double answer = 1.0;
        for (int i = 0; i < n; i++) {
            answer *= Math.max(p[i], l);
        }
        return answer;
    }

}
