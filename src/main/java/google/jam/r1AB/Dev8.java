package google.jam.r1AB;

import java.util.ArrayList;
import java.util.Collections;

public class Dev8 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int n = _ingredientsCount, p = _packagesCount;
        int[] rs = _ingredientQuantities;
        int[][] qs = new int[n][p];
        ArrayList<Event> es = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                qs[i][j] = _packages[i][j] * 100;
                int a = 90 * rs[i], b = 110 * rs[i];
                int min = (qs[i][j] + b - 1) / b;
                int max = qs[i][j] / a;
                min = Math.max(min, 1);
                if (min <= max) {
                    es.add(new Event(min, i, 1));
                    es.add(new Event(max, i, -1));
                }
                //					System.out.println(i + ": " + min + "-" + max + "(" + a + "," + b + ")");
            }
        }
        Collections.sort(es);
        int[] curCnts = new int[n];
        int[] usedCnts = new int[n];
        int ans = 0;
        for (Event e : es) {
            //				System.out.println(e.time + " " + e.i + " " + e.delta);
            if (e.delta == -1) {
                if (usedCnts[e.i] > 0)
                    usedCnts[e.i]--;
                else
                    curCnts[e.i]--;
            } else {
                curCnts[e.i]++;
                boolean valid = true;
                for (int i = 0; i < n; i++) {
                    if (curCnts[i] == 0)
                        valid = false;
                }
                if (valid) {
                    for (int i = 0; i < n; i++) {
                        curCnts[i]--;
                        usedCnts[i]++;
                    }
                    ans++;
                    //						System.out.println("make");
                }
            }
        }

        return ans;

    }

    static class Event implements Comparable<Event> {
        int time, i, delta;

        public Event(int time, int i, int delta) {
            this.time = time;
            this.i = i;
            this.delta = delta;
        }

        @Override
        public int compareTo(Event o) {
            if (time == o.time)
                return -Integer.compare(delta, o.delta);
            return Integer.compare(time, o.time);
        }
    }
}
