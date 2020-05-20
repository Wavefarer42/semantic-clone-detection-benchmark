package at.jku.isse.clones.r1CA;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author 2rf
 */
public class Dev0 {
    static class Pie implements Comparable<Pie> {
        int r;
        int h;

        public Pie(int r, int h) {
            this.r = r;
            this.h = h;
        }

        @Override
        public int compareTo(Pie o) {
            return Integer.compare(r, o.r);
        }

    }

    public static double run(int _n, int _k, int[] _r, int[] _h) {
        int n = _n;
        int k = _k;
        Pie[] a = new Pie[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Pie(_r[i], _h[i]);
        }

        Arrays.sort(a);
        PriorityQueue<Double> pq = new PriorityQueue<>();

        double ret = 0;

        for (Pie p : a) {

            double cur = Math.PI * p.r * p.r + 2 * Math.PI * p.r * p.h;

            if (pq.size() >= k - 1) {

                double[] zzz = new double[k - 1];
                for (int i = 0; i < k - 1; i++) {
                    zzz[i] = pq.poll();
                    cur -= zzz[i];
                }

                ret = Math.max(ret, cur);

                for (int i = 0; i < k - 1; i++) {
                    pq.add(zzz[i]);
                }

            }

            pq.add(-2 * Math.PI * p.r * p.h);

        }


        return ret;
    }
}
