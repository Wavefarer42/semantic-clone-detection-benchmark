package at.jku.isse.clones.r1CA;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.PI;

/**
 * @author alex700
 */
public class Dev1 {

    static class Pancake {
        long r, h;
        final int i;

        public Pancake(long r, long h, int i) {
            this.r = r;
            this.h = h;
            this.i = i;
        }
    }


    public static double run(int _n, int _k, int[] _r, int[] _h) {

        int n = _n;
        int k = _k - 1;
        Pancake[] a = new Pancake[n];
        Arrays.setAll(a, i -> new Pancake(_r[i], _h[i], i));

        return Arrays.stream(a)
                .map(f ->
                        2 * Arrays.stream(a)
                                .filter(x -> x.i != f.i && x.r <= f.r)
                                .sorted(Comparator.comparingLong(aa -> -aa.h * aa.r))
                                .limit(k)
                                .mapToLong(aa -> aa.h * aa.r)
                                .sum()
                                + (2 * f.h + f.r) * f.r
                ).mapToLong(x -> x).max().getAsLong() * PI;
    }
}
