package at.jku.isse.clones.r1CB;

/**
 * @author Derek.jiang
 */
public class Dev5 {
    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        int ac = _ac;
        int aj = _aj;
        int[][] cd = new int[ac][2];
        int[][] kj = new int[aj][2];
        for (int i = 0; i < ac; i++) {
            cd[i][0] = _c[i];
            cd[i][1] = _d[i];
        }

        for (int i = 0; i < aj; i++) {
            kj[i][0] = _j[i];
            kj[i][1] = _k[i];
        }

        if (ac < 2 && aj < 2) {
            return 2;
        }
        if (ac == 2) {
            return judge(cd);
        } else {
            return judge(kj);
        }
    }

    private static int judge(int[][] cd) {
        int s1 = Math.min(cd[0][0], cd[1][0]);
        int e1 = Math.min(cd[0][1], cd[1][1]);
        int s2 = Math.max(cd[0][0], cd[1][0]);
        int e2 = Math.max(cd[0][1], cd[1][1]);
        if (e2 - s1 <= 720) {
            return 2;
        }
        if (e1 + 1440 - s2 <= 720) {
            return 2;
        }
        return 4;
    }

}
