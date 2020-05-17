package at.jku.isse.clones.r0AC;

import java.util.PriorityQueue;

/**
 * @author abear888
 */
public class Dev4 {
    public static Result run(int _l, int _r) {
        int n = _l;
        int k = _r;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        pq.add(-1 * n);
        int a, b;
        for (int i = 0; i < k; i++) {
            int cur = pq.poll();
            if (cur % 2 == 0) {
                a = cur / 2 + 1;
                b = cur / 2;
            } else {
                a = (cur + 1) / 2;
                b = (cur + 1) / 2;
            }
            pq.add(a);
            pq.add(b);
            if (i == k - 1) {
                a *= -1;
                b *= -1;
                return new Result(Math.max(a, b), Math.min(a, b));
            }
        }

        throw new IllegalStateException();
    }
}
