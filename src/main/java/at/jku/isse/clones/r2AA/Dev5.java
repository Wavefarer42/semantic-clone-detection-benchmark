package at.jku.isse.clones.r2AA;

/**
 * @author qwerty787788
 */
public class Dev5 {
    public static int run(int _n, int _p, int[] _g) {
        int n = _n;
        int p = _p;
        int[] cnt = new int[p];
        for (int i = 0; i < n; i++) {
            cnt[_g[i] % p]++;
        }
        int res = cnt[0];
        if (p == 2) {
            res += (cnt[1] + 1) / 2;
        } else if (p == 3) {
            int m = Math.min(cnt[1], cnt[2]);
            res += m;
            cnt[1] -= m;
            cnt[2] -= m;
            int v = Math.max(cnt[1], cnt[2]);
            res += (2 + v) / 3;
        } else if (p == 4) {
            int m = Math.min(cnt[1], cnt[3]);
            res += m;
            cnt[1] -= m;
            cnt[3] -= m;
            res += cnt[2] / 2;
            cnt[2] %= 2;
            m = Math.max(cnt[1], cnt[3]);
            if (cnt[2] == 0) {
                res += (m + 3) / 4;
            } else {
                if (m > 2) {
                    res += (m + 5) / 4;
                } else {
                    res++;
                }
            }
        }
//        out.println(res);
//        System.err.println((t + 1) + "/" + tc + " done");
        return res;
    }
}
