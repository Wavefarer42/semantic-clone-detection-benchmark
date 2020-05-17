package at.jku.isse.clones.r1AB;

import java.util.Arrays;

public class Dev2 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int n = _ingredientsCount;
        int p = _packagesCount;
        int r[] = _ingredientQuantities;
        int q[][] = _packages;
        int count = 0;

        for (int i = 0; i < n; i++) {
            Arrays.sort(q[i]);
        }

        OUTER:
        for (int j = 0; j < p; j++) {
            for (int serv = 0; serv < n; serv++) {
                boolean flag = true;
                int num = (int) Math.floor((double) q[serv][j] / (double) r[serv]);
                for (int i = 0; i < n; i++) {
                    double val = q[i][j];//r[i]*num;
                    double end = 1.1D * (double) (r[i] * num);
                    double start = 0.9D * (double) (r[i] * num);
                    if (Double.compare(val, end) > 0 || Double.compare(start, val) > 0) {
                        flag = false;
                        break;
                    }
                }
                System.out.println();
                num++;
                if (flag == false) {
                    flag = true;
                    for (int i = 0; i < n; i++) {
                        double val = q[i][j];//r[i]*num;
                        double end = 1.1D * (double) (r[i] * num);
                        double start = 0.9D * (double) (r[i] * num);
                        if (Double.compare(val, end) > 0 || Double.compare(start, val) > 0) {
                            flag = false;
                            break;
                        }
                    }
                }

                System.out.println();
                if (flag) {
                    count++;
                    continue OUTER;
                }
            }

        }

        return count;
    }
}
