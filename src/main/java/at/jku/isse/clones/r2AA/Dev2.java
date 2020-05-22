package at.jku.isse.clones.r2AA;

import static java.lang.Math.min;

/**
 * @author eatmore
 */
public class Dev2 {
    public static int run(int _n, int _p, int[] _g) {
        int n = _n;
        int p = _p;
        int cnts[] = new int[p];
        for (int i = 0; i < n; i++) {
            ++cnts[_g[i] % p];
        }
        int ans = cnts[0];
        switch (p) {
            case 2:
                ans += get(cnts, 1, 2);
                break;
            case 3:
                ans += get(cnts, 1, 1, 2, 1);
                ans += get(cnts, 1, 3);
                ans += get(cnts, 2, 3);
                break;
            case 4:
                ans += get(cnts, 1, 1, 3, 1);
                ans += get(cnts, 2, 2);
                ans += get(cnts, 2, 1, 1, 2);
                ans += get(cnts, 2, 1, 3, 2);
                ans += get(cnts, 1, 4);
                ans += get(cnts, 3, 4);
                break;
        }
        for (int i = 1; i < p; i++) {
            if (cnts[i] != 0) {
                ++ans;
                break;
            }
        }
        return ans;
    }

    static int get(int cnts[], int i, int v) {
        int add = cnts[i] / v;
        cnts[i] -= add * v;
        return add;
    }

    static int get(int cnts[], int i, int v, int j, int w) {
        int add = min(cnts[i] / v, cnts[j] / w);
        cnts[i] -= add * v;
        cnts[j] -= add * w;
        return add;
    }
}
