package at.jku.isse.clones.r1AB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Me.
 */
public class Dev3 {
    static class Point implements Comparable<Point> {
        int val;
        boolean start;
        int r;

        Point(int val, boolean start, int r) {
            this.val = val;
            this.start = start;
            this.r = r;
        }

        public int compareTo(Point o) {
            if (val > o.val) return 1;
            if (val < o.val) return -1;
            if (start && !o.start) return -1;
            if (!start && o.start) return 1;
            return r - o.r;
        }

        public String toString() {
            return r + " at " + val + " " + (start ? "start" : "end");
        }
    }

    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        List<Point> l = new ArrayList<>();
        int N = _ingredientsCount;
        int P = _packagesCount;
        int[] costs = _ingredientQuantities;
        int[][] q = _packages;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < P; j++) {
                getQuantity(l, q[i][j], costs[i], i);
            }
        }


        int tot = 0;
        int[] count = new int[N];
        int[] added = new int[N];
        Collections.sort(l);
        for (Point p : l) {
            //System.err.println(p);
            if (p.start) {
                count[p.r]++;
                //System.err.println(Arrays.toString(count));
                boolean match = true;
                for (int i = 0; i < N; i++) {
                    if (count[i] <= 0) match = false;
                }
                if (match) {
                    tot++;
                    for (int i = 0; i < N; i++) {
                        count[i]--;
                        added[i]++;
                    }
                }
            } else {
                if (added[p.r] > 0) {
                    added[p.r]--;
                } else {
                    count[p.r]--;
                }
            }
        }

        return tot;
    }

    static void getQuantity(List<Point> l, double n, double cost, int r) {
        double q = n / cost;
        int hi = (int) Math.floor(q / 0.9);
        int low = (int) Math.ceil(q / 1.1);
        if (low <= hi) {
            //System.err.println(n + " " + cost + ": " + low + " - " + hi);
            l.add(new Point(low, true, r));
            l.add(new Point(hi, false, r));
        }
    }
}
