package google.jam.r0AC;

import java.util.PriorityQueue;

/**
 * @author
 */
public class Dev9 {
    public static Result run(int _l, int _r) {
            long N = _l;
            long K = _r;
            long[] res = enter(N, K);
            return new Result((int) res[0], (int) res[1]);
    }

    private static long[] enter(long N, long K) {
        return enterHelper(N, N, 1, 0, K, 1);
    }

    private static long[] enterHelper(long max, long min, long cmax, long cmin, long k, long p) {
        //System.out.println(max + " " + min + " " + cmax + " " + cmin + " " + k + " " + p);
        if (p < k) {
            long max2 = max / 2;
            long min2 = (min - 1) / 2;
            long cmax2;
            long cmin2;
            if (max == min) {
                if (max % 2 == 0) {
                    cmin2 = cmax2 = cmax + cmin;
                }
                else {
                    cmax2 = 2 * (cmax + cmin);
                    cmin2 = 0;
                }
            }
            else {
                if (max % 2 == 0) {
                    cmax2 = cmax;
                    cmin2 = cmax + 2 * cmin;
                }
                else {
                    cmin2 = cmin;
                    cmax2 = 2 * cmax + cmin;
                }
            }
            return enterHelper(max2, min2, cmax2, cmin2, k - p, p * 2);
        }
        else {
            long m = k > cmax? min: max;
            return new long[] { m / 2, (m - 1) / 2 };
        }
    }

    private static long[] enter2(long N, long K) {
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        pq.add(-N);
        for (int i = 0; i < K - 1; i++) {
            long n = -pq.poll();
            long a = n / 2;
            long b = (n - 1) / 2;
            pq.add(-a);
            pq.add(-b);
        }
        long n = -pq.poll();
        long a = n / 2;
        long b = (n - 1) / 2;
        return new long[] { a, b };
    }
}
