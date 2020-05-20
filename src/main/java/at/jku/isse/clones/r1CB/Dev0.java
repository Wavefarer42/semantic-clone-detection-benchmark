package at.jku.isse.clones.r1CB;

import java.util.Arrays;

/**
 * @author 2rf
 */
public class Dev0 {

    static final int DAY = 1440;
    static final int HALF = DAY / 2;

    static final int INF = Integer.MAX_VALUE / 3;

    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {
        int n1 = _ac;
        int n2 = _aj;
        boolean[][] cant = new boolean[2][DAY];

        for (int i = 0; i < n1; i++) {
            int low = _c[i];
            int high = _d[i];
            for (int j = low; j < high; j++) {
                cant[0][j] = true;
            }
        }

        for (int i = 0; i < n2; i++) {
            int low = _j[i];
            int high = _k[i];
            for (int j = low; j < high; j++) {
                cant[1][j] = true;
            }
        }

        int[][][][] dp = new int[2][2][HALF + 1][HALF + 1];
        // first, last, time0, time1
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k <= HALF; k++) {
                    Arrays.fill(dp[i][j][k], INF);
                }
            }
        }

        if (!cant[0][0]) {
            dp[0][0][1][0] = 0;
        }
        if (!cant[1][0]) {
            dp[1][1][0][1] = 0;
        }

        int ret = INF;

        for (int t0 = 0; t0 <= HALF; t0++) {
            for (int t1 = 0; t1 <= HALF; t1++) {

                for (int fst = 0; fst < 2; fst++) {
                    for (int lst = 0; lst < 2; lst++) {

                        int now = dp[fst][lst][t0][t1];
                        if (now == INF) {
                            continue;
                        }

                        //						System.err.println(fst + " " + lst + " " + t0 + " " + t1 + " --> " + now);


                        int nowTime = t0 + t1;

                        if (nowTime == DAY) {
                            if (fst != lst) {
                                now++;
                            }
                            ret = Math.min(ret, now);
                            continue;
                        }

                        do {
                            int whoNow = 0;
                            if (t0 == HALF || cant[whoNow][nowTime]) {
                                break;
                            }
                            int delta = whoNow != lst ? 1 : 0;
                            dp[fst][whoNow][t0 + 1][t1] = Math.min(dp[fst][whoNow][t0 + 1][t1], now + delta);
                        } while (false);

                        do {
                            int whoNow = 1;
                            if (t1 == HALF || cant[whoNow][nowTime]) {
                                break;
                            }
                            int delta = whoNow != lst ? 1 : 0;
                            dp[fst][whoNow][t0][t1 + 1] = Math.min(dp[fst][whoNow][t0][t1 + 1], now + delta);
                        } while (false);
                    }
                }

            }
        }

        if (ret == INF) {
            throw new AssertionError();
        }
        return ret;
    }
}
