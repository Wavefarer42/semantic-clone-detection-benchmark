package google.jam.r0AC;

import java.util.PriorityQueue;

/**
 * @author 0Be
 */
public class Dev5 {
    public static Result run(int _l, int _r) {

        StringBuilder ans = new StringBuilder();

        int n = _l;
        int k = _r;

        PriorityQueue<Range> queue = new PriorityQueue<>();

        long count = 0;
        queue.add(new Range(1, n));

        while (count < k - 1) {
            Range tmp = queue.poll();
            long mid = (long) Math.ceil((tmp.end - tmp.start + 1) / 2.0);
            if (tmp.start <= tmp.start + mid - 2) {
                queue.add(new Range(tmp.start, tmp.start + mid - 2));
            }
            if (tmp.start + mid <= tmp.end) {
                queue.add(new Range(tmp.start + mid, tmp.end));
            }
            count++;
        }

        Range last = queue.poll();
        long maxMax = (last.end - last.start) / 2;
        long maxMin = (last.end - last.start + 1) / 2;

        return new Result((int) maxMin, (int) maxMax);
    }

    static class Range implements Comparable<Range> {

        public long start;
        public long end;

        public Range(long s, long e) {
            start = s;
            end = e;
        }

        @Override
        public int compareTo(Range r) {

            if (end - start > r.end - r.start)
                return -1;
            if (end - start < r.end - r.start)
                return 1;
            if (start <= r.start)
                return -1;
            return 1;
        }
    }
}
