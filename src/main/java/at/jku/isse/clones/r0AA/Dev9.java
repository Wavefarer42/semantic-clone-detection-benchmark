package at.jku.isse.clones.r0AA;

/**
 * @author 8163264128
 */
public class Dev9 {
    public static int run(String _pattern, int _num) {
        return flips(diff(_pattern), _num);
    }

    private static boolean toBoolean(char c) {
        return c == '+';
    }

    private static boolean[] diff(String S) {
        boolean[] d = new boolean[S.length()];
        d[0] = toBoolean(S.charAt(0));
        for (int i = 1; i < S.length(); i++) {
            d[i] = S.charAt(i - 1) == S.charAt(i);
        }
        return d;
    }

    private static int flips(boolean[] d, int K) {
        int f = 0;
        boolean c = true;
        for (int i = 0; i < d.length; i++) {
            c ^= !d[i];
            if (c) continue;
            if (i + K > d.length) return -1;
            f++;
            c = true;
            if (i + K != d.length)
                d[i + K] = !d[i + K];
        }
        return f;
    }
}
