package google.jam.r0AB;

/**
 * @author 8163264128
 */
public class Dev6 {

    public static long run(long _num) {
        int[] n = toArray(_num);
        int[] m = new int[18];
        tidyMaxHelper(m, n, m.length - 1, 0);
        return fromArray(m);
    }

    private static int[] toArray(long N) {
        int[] n = new int[18];
        for (int j = 0; N > 0; j++, N /= 10) n[j] = (int)(N % 10);
        return n;
    }

    private static long fromArray(int[] n) {
        long N = 0;
        for (int i = n.length - 1; i >= 0; i--)
            N = 10 * N + n[i];
        return N;
    }

    private static boolean atMost(int[] m, int[] n) {
        int i = m.length - 1;
        while (m[i] == n[i]) {
            if (i == 0) return true;
            i--;
        }
        return m[i] < n[i];
    }

    private static boolean tidyMaxHelper(int[] m, int[] n, int i, int k) {
        if (i < 0) return true;
        for (int j = 9; j >= k; j--) {
            m[i] = j;
            if (atMost(m, n)) {
                if (tidyMaxHelper(m, n, i - 1, j))
                    return true;
            }
        }
        m[i] = 0;
        return false;
    }
}
