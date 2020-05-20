package at.jku.isse.clones.r1CA;

import java.util.Arrays;

import static java.lang.Math.PI;

/**
 * @author HellFalcon
 */
public class Dev9 {

    public static double run(int _n, int _k, int[] _r, int[] _h) {

        int n = _n;
        int k = _k;
        int[] r = _r;
        int[] h = _h;

        double best = 0.0;
        boolean[] used = new boolean[n];
        main:
        for (int i = 0; i < n; i++) {
            int ri = r[i];
            double c = PI * ri * ri + 2 * PI * ri * h[i];
            Arrays.fill(used, false);
            used[i] = true;
            for (int j = 1; j < k; j++) {
                double maxac = 0.0;
                int maxaci = -1;
                for (int q = 0; q < n; q++) {
                    double ac = 2 * PI * r[q] * h[q];
                    if (maxac < ac && !used[q] && r[q] <= ri) {
                        maxac = ac;
                        maxaci = q;
                    }
                }
                if (maxaci < 0)
                    continue main;
                used[maxaci] = true;
                c += maxac;
            }
            if (c > best)
                best = c;
        }
        return best;

    }

}
