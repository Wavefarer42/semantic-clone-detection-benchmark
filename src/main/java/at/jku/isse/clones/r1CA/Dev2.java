package at.jku.isse.clones.r1CA;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author andrewzta
 */
public class Dev2 {

    static class P {
        int i;
        long r;
        long h;

        public P(int i, long r, long h) {
            this.i = i;
            this.r = r;
            this.h = h;
        }

        public String toString() {
            return "r = " + r + ", h = " + h;
        }
    }

    public static double run(int _n, int _k, int[] _r, int[] _h) {
        int n = _n;
        int k = _k;
        double ans = 0;

        P[] p = new P[n];
        for (int i = 0; i < n; i++) {
            p[i] = new P(i, _r[i], _h[i]);
        }

        Arrays.sort(p, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.r < o2.r || o1.r == o2.r && o1.i < o2.i ? -1 : o1.i == o2.i ? 0 : 1;
            }
        });

        for (int i = k - 1; i < n; i++) {
            P[] q = new P[i];
            for (int j = 0; j < i; j++) {
                q[j] = p[j];
            }

            Arrays.sort(q, new Comparator<P>() {
                @Override
                public int compare(P o1, P o2) {
                    return o1.r * o1.h > o2.r * o2.h || o1.r * o1.h == o2.r * o2.h && o1.i < o2.i ? -1 : o1.i == o2.i ? 0 : 1;
                }
            });

            double val = 0;
            for (int j = 0; j < k - 1; j++) {
                val += 2 * Math.PI * q[j].r * q[j].h;
            }
            val += 2 * Math.PI * p[i].r * p[i].h + Math.PI * p[i].r * p[i].r;

            if (val > ans) {
                ans = val;
            }

        }

        return ans;
    }
}
