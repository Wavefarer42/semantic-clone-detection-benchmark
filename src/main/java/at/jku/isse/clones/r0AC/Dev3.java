package at.jku.isse.clones.r0AC;

import java.util.TreeMap;

/**
 * @author alei
 */
public class Dev3 {
    public static int[] run(int _l, int _r) {
        TreeMap<Long, Long> mp = new TreeMap<>();
        long n = _l;
        long k = _r;
        mp.put(n, 1l);
        long mini = -1;
        long maxi = -1;
        while (true) {
            long len = mp.lastKey();
            long cnt = mp.get(len);
            if (k <= cnt) {
                mini = (len - 1) / 2;
                maxi = len / 2;
                break;
            }
            k -= cnt;
            mp.remove(len--);
            long cur = 0;
            if (mp.containsKey(len / 2)) cur = mp.get(len / 2);
            mp.put(len / 2, cur + cnt);
            cur = 0;
            if (mp.containsKey((len + 1) / 2)) cur = mp.get((len + 1) / 2);
            mp.put((len + 1) / 2, cur + cnt);
        }

        return new int[]{(int) maxi, (int) mini};
    }
}
