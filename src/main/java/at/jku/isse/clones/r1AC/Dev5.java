package at.jku.isse.clones.r1AC;

/**
 * @author hs484
 */
public class Dev5 {
    static final int INF = 1001001001;

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {
        int ans = INF;
        for (int b = 0; b <= 100; b++) {
            for (int d = 0; d <= 100; d++) {
                Sim sim = new Sim(_hd, _ad, _hk, _ak, _b, _d);
                int here = sim.run(b, d);
                if (ans > here) {
                    ans = here;
                }
            }
        }

        if (ans == INF)
            return -1;
        else
            return ans;
    }


    static class Sim {
        final long initial_Hd;
        long Hd;
        long Ad;
        long Hk;
        long Ak;
        long B;
        long D;

        public Sim(int hd, int ad, int hk, int ak, int b, int d) {
            initial_Hd = hd;
            Hd = hd;
            Ad = ad;
            Hk = hk;
            Ak = ak;
            B = b;
            D = d;
        }

        int run(int b, int d) {
            int turn = 0;
            long pb = -1;
            long pd = -1;
            long pHk = -1;
            long pHd = -1;
            for (; pb != b || pd != d || pHk != Hk || pHd != Hd; ) {
                pb = b;
                pd = d;
                pHk = Hk;
                pHd = Hd;
                ++turn;
                if (d > 0) {
                    if (Ak - D >= Hd) {
                        Hd = initial_Hd;
                    } else {
                        d--;
                        Ak -= D;
                        if (Ak < 0) Ak = 0;
                    }
                } else if (b > 0) {
                    if (Ak >= Hd) {
                        Hd = initial_Hd;
                    } else {
                        b--;
                        Ad += B;
                    }
                } else {
                    if (Hk <= Ad) {
                        Hk -= Ad;
                    } else if (Ak >= Hd) {
                        Hd = initial_Hd;
                    } else {
                        Hk -= Ad;
                    }
                }


                if (Hk > 0) {
                    Hd -= Ak;
                } else {
                    return turn;
                }
                if (Hd <= 0) {
                    return INF;
                }
            }
            return INF;
        }
    }

}
