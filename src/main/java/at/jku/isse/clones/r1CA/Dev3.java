package at.jku.isse.clones.r1CA;

import java.util.Arrays;
import java.util.Comparator;

public class Dev3 {
    private static class A {
        int r;
        int h;
    }

    public static double run(int _n, int _k, int[] _r, int[] _h) {
        int n = _n;
        int k = _k;
        A[] a = new A[n];
        for (int i = 0; i < n; i++) {
            a[i] = new A();
            a[i].r = _r[i];
            a[i].h = _h[i];
        }
        double res = 0;
        for (int i = 0; i < n; i++) {
            A[] b = Arrays.copyOf(a, n);
            {
                A tmp = b[i];
                b[i] = b[n - 1];
                b[n - 1] = tmp;
            }
            Arrays.sort(b, 0, n - 1, new Comparator<A>() {
                @Override
                public int compare(A o1, A o2) {
                    return -Long.compare(o1.h * (long) o1.r, o2.h * (long) o2.r);
                }
            });
            double tmp = b[n - 1].r * (long) b[n - 1].r + 2 * b[n - 1].h * (long) b[n - 1].r;
            int c = k - 1;
            for (int j = 0; j < n - 1 && c > 0; j++) {
                if (b[j].r <= b[n - 1].r) {
                    tmp += 2 * b[j].h * (long) b[j].r;
                    c--;
                }
            }
            tmp *= Math.PI;
            res = Math.max(res, tmp);
        }
        return res;
    }
}
