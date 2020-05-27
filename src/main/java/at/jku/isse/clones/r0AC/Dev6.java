package at.jku.isse.clones.r0AC;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author bohuss
 */
public class Dev6 {

    public static int[] run(int _l, int _r) {
        long N = _l;
        long K = _r - 1;


        PriorityQueue<Long> q = new PriorityQueue<Long>(Collections.reverseOrder());
        q.add(N);

        while (K-- > 0) {
            long x = q.poll();
            //System.out.println(x.max);
            if (x % 2 == 1) {
                q.add(x / 2);
                q.add(x / 2);
            } else {
                q.add(x / 2);
                q.add(x / 2 - 1);
            }
        }
        long x = q.peek();
        if (x % 2 == 1)
            return new int[]{(int) x / 2, (int) x / 2};
        return new int[]{(int) x / 2, (int) (x / 2 - 1)};
    }
}
