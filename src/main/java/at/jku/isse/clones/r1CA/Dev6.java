package at.jku.isse.clones.r1CA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Derek.jiang
 */
public class Dev6 {

    public static double run(int _n, int _k, int[] _r, int[] _h) {

        int n = _n;
        int k = _k;
        int[][] pancake = new int[n][2];
        for (int i = 0; i < n; i++) {
            pancake[i][0] = _r[i];
            pancake[i][1] = _h[i];
        }
        double ans = 0;
        for (int i = 0; i < n; i++) {
            double curr = calcSide(pancake[i]) + calcArea(pancake[i][0]);
            List<Double> arr = new ArrayList<>();
            for (int j = 0; j < n; j++)
                if (j != i) {
                    if (pancake[j][0] <= pancake[i][0]) {
                        arr.add(calcSide(pancake[j]));
                    }
                }
            if (arr.size() < k - 1) {
                continue;
            }
            arr.sort((a, b) -> -Double.compare(a, b));
            for (int j = 0; j < k - 1; j++) {
                curr += arr.get(j);
            }
            if (curr > ans) {
                ans = curr;
            }
        }
        return ans;
    }

    private static double calcSide(int[] pancake) {
        double r = (double) pancake[0];
        double h = (double) pancake[1];
        return 2.0 * r * h * Math.PI;
    }

    private static double calcArea(int r) {
        return Math.PI * (double) r * (double) r;
    }
}
