package at.jku.isse.clones.r1CB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author adistu
 */
public class Dev1 {
    private static class Point implements Comparable<Point> {
        int time;
        int parent;
        int stop;

        public Point(int time, int parent, int stop) {
            this.time = time;
            this.parent = parent;
            this.stop = stop;
        }

        @Override
        public int compareTo(Point o) {
            return time == o.time ? o.stop - stop : time - o.time;
        }
    }


    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        final int na = _ac;
        final int nb = _aj;

        final List<Point> l = new ArrayList<>();
        for (int i = 0; i < na; ++i) {
            l.add(new Point(_c[i], 0, 0));
            l.add(new Point(_d[i], 0, 1));
        }
        for (int i = 0; i < nb; ++i) {
            l.add(new Point(_j[i], 1, 0));
            l.add(new Point(_k[i], 1, 1));
        }
        Collections.sort(l);
        final Point p0 = l.get(0);
        l.add(new Point(p0.time + 1440, p0.parent, p0.stop));
        final List<Integer> gapA = new ArrayList<>();
        final List<Integer> gapB = new ArrayList<>();
        int spare = 0;
        int[] t = new int[2];
        int r = 0;
        for (int i = 0; i < l.size() - 1; ++i) {
            final Point p = l.get(i);
            final Point p1 = l.get(i + 1);
            final int dif = p1.time - p.time;
            if (p.parent == p1.parent) {
                t[p.parent] += dif;
                if (p.stop == 1) {
                    (p.parent == 0 ? gapA : gapB).add(dif);
                }
            } else {
                r++;
                spare += dif;
            }
        }
        Collections.sort(gapA);
        Collections.sort(gapB);
        while (t[0] + spare < 720) {
            t[0] += gapB.remove(gapB.size() - 1);
            r += 2;
        }
        while (t[1] + spare < 720) {
            t[1] += gapA.remove(gapA.size() - 1);
            r += 2;
        }

        return r;
    }
}
