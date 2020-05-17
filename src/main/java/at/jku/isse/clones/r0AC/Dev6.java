package at.jku.isse.clones.r0AC;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 3mara
 */
public class Dev6 {
    static class Pair {
        private final int first;
        private final int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static Result run(int _l, int _r) {

        String line;
        int ls = 0;
        int rs = 0;
        int stallsNumber = _l;
        int personsNumber = _r;
        if (stallsNumber % 2 != 0) {
            ls = stallsNumber / 2;
            rs = stallsNumber / 2;
        } else {
            ls = stallsNumber / 2 - 1;
            rs = stallsNumber - ls - 1;
        }
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>() {

            public int compare(Pair x, Pair y) {
                if (Math.min(x.first, x.second) > Math.min(y.first, y.second))
                    return -1;
                else if (Math.min(x.first, x.second) == Math.min(y.first, y.second))
                    if (Math.max(x.first, x.second) > Math.max(y.first, y.second))
                        return -1;
                    else
                        return 1;
                else
                    return 1;
            }
        });
        queue.add(new Pair(ls, rs));
        for (int p = 1; p < personsNumber; p++) {
            Pair pair = queue.remove();
            int newls = (pair.first - 1) / 2;
            queue.add(new Pair(newls, pair.first - newls - 1));
            newls = (pair.second - 1) / 2;
            queue.add(new Pair(newls, pair.second - newls - 1));
        }
        return new Result(
                Math.max(queue.peek().first, queue.peek().second),
                Math.min(queue.peek().first, queue.peek().second)
        );
    }
}
