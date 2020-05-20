package at.jku.isse.clones.r1CA;

import java.util.Arrays;

/**
 * @author ferr
 */
public class Dev7 {

    public static double run(int _n, int _k, int[] _r, int[] _h) {
        int n = _n, k = _k;
        Pancake[] pkc = new Pancake[n];
        for (int i = 0; i < n; ++i) {
            pkc[i] = new Pancake(_r[i], _h[i]);
        }
        Arrays.sort(pkc);
        int maxRId = 0;
        for (int i = 0; i < n; ++i) {
            if (pkc[i].R > pkc[maxRId].R) {
                maxRId = i;
            }
        }
        double ans = calc(pkc, k);
        Pancake tmp = pkc[maxRId];
        pkc[maxRId] = pkc[k - 1];
        pkc[k - 1] = tmp;
        double ans2 = calc(pkc, k);
        ans = Math.max(ans, ans2);

        return ans;
    }

    private static class Pancake implements Comparable<Pancake> {
        public final int R;
        public final int H;

        private Pancake(int r, int h) {
            R = r;
            H = h;
        }

        @Override
        public int compareTo(Pancake o) {
            return -Long.compare(1L * R * H, 1L * o.R * o.H);
        }

        @Override
        public String toString() {
            return "Pancake{" +
                    "R=" + R +
                    ", H=" + H +
                    '}';
        }
    }

    private static double calc(Pancake[] pkc, int qty) {
        double ans = 0;
        double maxR = 0;
        for (int i = 0; i < qty; ++i) {
            ans += Math.PI * 2 * pkc[i].R * pkc[i].H;
            maxR = Math.max(maxR, pkc[i].R);
        }
        ans += Math.PI * maxR * maxR;
        return ans;
    }
}
