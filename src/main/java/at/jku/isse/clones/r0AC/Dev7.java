package at.jku.isse.clones.r0AC;

/**
 * @author 4castle
 */
public class Dev7 {
    public static Result run(int _l, int _r) {
        long n = _l;
        long k = _r;

        double val = calc(n, k);

        long x = Math.round(val);
        long y = (long) Math.floor(val);
        return new Result((int) x, (int) y);
    }

    private static double calc(long N, long K) {
        return (double) (N - K) / (double) (1L << (log2Floor(K) + 1));
    }

    private static long log2Floor(long x) {
        return 63 - Long.numberOfLeadingZeros(x);
    }
}
