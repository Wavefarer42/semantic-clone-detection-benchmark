package at.jku.isse.clones.r1CB;

import java.util.*;

/**
 * @author ferr
 */
public class Dev6 {
    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        int Ac = _ac, Aj = _aj;
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < Ac; ++i) {
            intervals.add(new Interval(_c[i], _d[i], 0));
        }
        for (int i = 0; i < Aj; ++i) {
            intervals.add(new Interval(_j[i], _k[i], 1));
        }
        Collections.sort(intervals);
        int ans = calc(intervals);

        return ans;
    }

    static int calc(List<Interval> intervals) {
        int swaps = 0;
        int cur = -1;
        Optional<Interval> prev = Optional.empty();
        Optional<Interval> first = Optional.empty();
        int[] forFree = {0, 0};
        int[] sum = {0, 0};
        PriorityQueue<Integer>[] forSwap = new PriorityQueue[]{new PriorityQueue<>(), new PriorityQueue<>()};
        for (Interval interval : intervals) {
            sum[interval.who] += interval.stop - interval.start;

            if (cur == -1) {
                cur = interval.who;
            }

            if (interval.who == cur) {
                if (prev.isPresent()) {
                    forSwap[1 - cur].add(-(interval.start - prev.get().stop));
                    sum[cur] += interval.start - prev.get().stop;
                }
            } else {
                swaps++;
                cur = interval.who;
                if (prev.isPresent()) {
                    forFree[cur] += interval.start - prev.get().stop;
                    sum[1 - cur] += interval.start - prev.get().stop;
                }
            }
            prev = Optional.of(interval);
            if (!first.isPresent()) {
                first = Optional.of(interval);
            }
        }
        int overlap = 1440 - prev.get().stop + first.get().start;
        if (prev.get().who == first.get().who) {
            forSwap[1 - first.get().who].add(-overlap);
            sum[first.get().who] += overlap;
        } else {
            swaps++;
            forFree[first.get().who] += overlap;
            sum[prev.get().who] += overlap;
        }
        if (sum[0] + sum[1] != 1440)
            throw new RuntimeException("fck");
        int id = sum[0] < sum[1] ? 0 : 1;
        if (sum[id] < 720) {
            int d = Math.min(720 - sum[id], forFree[id]);
            sum[id] += d;
            sum[1 - id] -= d;
        }
        while (sum[id] < 720) {
            int p = -forSwap[id].poll();
            int d = Math.min(720 - sum[id], p);
            sum[id] += d;
            sum[1 - id] -= d;
            swaps += 2;
        }
        if (sum[0] != 720 || sum[1] != 720)
            throw new RuntimeException("fck2");
        return swaps;
    }

    private static class Interval implements Comparable<Interval> {
        public final int start;
        public final int stop;
        public final int who;

        private Interval(int start, int stop, int who) {
            this.start = start;
            this.stop = stop;
            this.who = who;
        }

        @Override
        public int compareTo(Interval o) {
            return Integer.compare(start, o.start);
        }
    }

}
