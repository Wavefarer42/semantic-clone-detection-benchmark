package at.jku.isse.clones.r1CB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alex700
 */
public class Dev2 {
    static class Seg implements Comparable<Seg> {
        int start, finish, tp;

        public Seg(int start, int finish, int tp) {
            this.start = start;
            this.finish = finish;
            this.tp = tp;
        }

        int time() {
            return finish - start;
        }

        @Override
        public int compareTo(Seg o) {
            return Integer.compare(start, o.start);
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,%d)", start, finish, tp);
        }
    }


    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        int n = _ac;
        int m = _aj;
        List<Seg> list = new ArrayList<>();
        int sum0 = 0;
        int sum1 = 0;
        for (int i = 0; i < n; i++) {
            final Seg seg = new Seg(_c[i], _d[i], 0);
            list.add(seg);
            sum0 += seg.time();
        }
        for (int i = 0; i < m; i++) {
            final Seg seg = new Seg(_j[i], _k[i], 1);
            list.add(seg);
            sum1 += seg.time();
        }
        sum0 = 12 * 60 - sum0;
        sum1 = 12 * 60 - sum1;
        Collections.sort(list);
        List<Integer> spl0 = new ArrayList<>();
        List<Integer> spl1 = new ArrayList<>();
        int good = 0;
        for (int i = 0; i < n + m - 1; i++) {
            Seg seg1 = list.get(i);
            Seg seg2 = list.get(i + 1);
            if (seg1.tp == seg2.tp) {
                if (seg1.tp == 0) {
                    spl0.add(seg2.start - seg1.finish);
                } else {
                    spl1.add(seg2.start - seg1.finish);
                }
            } else {
                good++;
            }
        }
        if (list.get(0).tp == list.get(list.size() - 1).tp) {
            final int border = list.get(0).start + 24 * 60 - list.get(list.size() - 1).finish;
            if (list.get(0).tp == 0) {
                spl0.add(border);
            } else {
                spl1.add(border);
            }
        } else {
            good++;
        }

        Collections.sort(spl0);
        Collections.sort(spl1);
        int s0 = 0, s1 = 0;
        int ans0 = spl0.size();
        for (int i = 0; i < spl0.size(); i++) {
            s0 += spl0.get(i);
            if (s0 > sum0) {
                ans0 = i;
                break;
            }
        }
        int ans1 = spl1.size();
        for (int i = 0; i < spl1.size(); i++) {
            s1 += spl1.get(i);
            if (s1 > sum1) {
                ans1 = i;
                break;
            }
        }
        return good + (list.size() - good - ans0 - ans1) * 2;
    }
}
