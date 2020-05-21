package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author 2rf
 */
public class Dev0 {
    public static double run(int _n, int _k, double _u, double[] _ps) {

        int n = _n;
        int k = _k;
        double money = _u;
        double[] a = new double[n + 2];
        for (int i = 0; i < n; i++) {
            a[i] = _ps[i];
        }
        a[n] = 1;
        a[n + 1] = 100;

        Arrays.sort(a);

//        System.err.println(Arrays.toString(a));

        for (int ptr = 0;; ptr++) {
            double needOne = a[ptr + 1] - a[ptr];
            double needAll = needOne * (ptr + 1);
            //			System.err.println(needOne + " " + needAll + " " + money);
            if (needAll >= money) {
                for (int i = 0; i <= ptr; i++) {
                    a[i] += money / (ptr + 1);
                }
                break;
            } else {
                for (int i = 0; i <= ptr; i++) {
                    a[i] = a[ptr + 1];
                }
                money -= needAll;
            }
//            System.err.println(Arrays.toString(a));
        }

        double ret = 1;
        for (double x : a) {
            ret *= x;
        }

        return ret / 100;
    }
}
