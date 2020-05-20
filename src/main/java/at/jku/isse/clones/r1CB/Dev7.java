package at.jku.isse.clones.r1CB;

/**
 * @author gojira
 */
public class Dev7 {
    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        final int X = 24 * 60;
        final int Y = X / 2;
        final int IMPOSSIBLE = Integer.MAX_VALUE - 1;

        int n = _ac;
        int m = _aj;
        boolean[] dadBusy = new boolean[X];
        boolean[] momBusy = new boolean[X];
        for (int i = 0; i < n; i++) {
            int a = _c[i];
            int b = _d[i];
            for (int j = a; j < b; j++) {
                dadBusy[j] = true;
            }
        }
        for (int i = 0; i < m; i++) {
            int a = _j[i];
            int b = _k[i];
            for (int j = a; j < b; j++) {
                momBusy[j] = true;
            }
        }

        boolean two = false;
        for (int dadStart = 0; dadStart < X; dadStart++) {
            boolean good = true;
            for (int i = 0; i < Y; i++) {
                if (dadBusy[(dadStart + i) % X]) {
                    good = false;
                }
            }
            for (int i = Y; i < 2 * Y; i++) {
                if (momBusy[(dadStart + i) % X]) {
                    good = false;
                }
            }
            if (good) {
                two = true;
                //                    System.out.println(dadStart);
            }
        }
        //            System.out.println("Answer: " + t + " " + (two ? 2 : 4));
        //
        //            // who started at midnight, who is active now, what time is it, how much time dad spent
        //            int[][][][] minimumExchanges = new int[2][2][X + 1][Y + 1];
        //            for(int i = 0; i < 2; i++) {
        //                for(int j = 0; j < 2; j++) {
        //                    for(int k = 0; k <= X; k++) {
        //                        for(int l = 0; l <= Y; l++) {
        //                            minimumExchanges[i][j][k][l] = IMPOSSIBLE;
        //                        }
        //                    }
        //                }
        //            }
        //            minimumExchanges[0][0][0][0] = minimumExchanges[1][1][0][0] = 0;
        //            for(int k = 0; k < X; k++) {
        //                for(int i = 0; i < 2; i++) {
        //                    for(int j = 0; j < 2; j++) {
        //                        for(int l = 0; l <= Y; l++) {
        //                            if (minimumExchanges[i][j][k][l] == IMPOSSIBLE) continue;
        //                            if (j == 0 && !dadBusy[k] || j == 1 && !momBusy[k]) {
        //                                int deltaL = (j == 0 ? 1 : 0);
        //                                if (l + deltaL <= Y) {
        //                                    minimumExchanges[i][j][k + 1][l + deltaL] = Math.min(minimumExchanges[i][j][k + 1][l + deltaL], minimumExchanges[i][j][k][l]);
        //                                }
        //                            }
        //                            int deltaL = (j == 0 ? 0 : 1);
        //                            if (l + deltaL <= Y) {
        //                                minimumExchanges[i][1 - j][k + 1][l + deltaL] = Math.min(minimumExchanges[i][1 - j][k + 1][l + deltaL], minimumExchanges[i][j][k][l] + 1);
        //                            }
        //                        }
        //                    }
        //                }
        //            }
        //            System.out.println(minimumExchanges[0][0][580][580]);
        //            System.out.println(minimumExchanges[0][1][581][580]);
        //            System.out.println(minimumExchanges[0][1][582][580]);
        //            System.out.println(minimumExchanges[0][1][580 + Y][580]);
        //
        //            int ans = minimumExchanges[0][0][X][Y];
        //            ans = Math.min(ans, minimumExchanges[1][1][X][Y]);
        //            ans = Math.min(ans, minimumExchanges[0][1][X][Y] + 1);
        //            ans = Math.min(ans, minimumExchanges[1][0][X][Y] + 1);

        return (two ? 2 : 4);
    }
}
