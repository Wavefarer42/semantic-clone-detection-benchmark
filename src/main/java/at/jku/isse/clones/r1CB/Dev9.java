package at.jku.isse.clones.r1CB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author intrepidcoder
 */
public class Dev9 {
    private static class Inv implements Comparable<Inv> {
        int s;
        int e;
        boolean c;

        Inv(int a, int b, boolean c) {
            s = a;
            e = b;
            this.c = c;
        }

        @Override
        public int compareTo(Inv o) {
            return Integer.compare(s, o.s);
        }
    }

    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        int ac = _ac;
        int aj = _aj;
        ArrayList<Inv> busy = new ArrayList<Inv>();
        int cTime = 0;

        for (int i = 0; i < ac; i++) {
//            tokenizer = new StringTokenizer(input.readLine());
            int x = _c[i];
            int y = _d[i];
            busy.add(new Inv(x, y, true));
            cTime += y - x;
        }

        for (int i = 0; i < aj; i++) {
//            tokenizer = new StringTokenizer(input.readLine());
            int x = _j[i];
            int y = _k[i];
            busy.add(new Inv(x, y, false));
        }

        Collections.sort(busy);
        // ArrayList<Inv> empty = new ArrayList<Inv>();
        ArrayList<Integer> cEasy = new ArrayList<Integer>();
        ArrayList<Integer> jEasy = new ArrayList<Integer>();
        // ArrayList<Inv> neutral = new ArrayList<Inv>();
        int neutralTime = 0;
        int neutralCount = 0;

        Comparator<Inv> longFirst = new Comparator<Inv>() {
            @Override
            public int compare(Inv a, Inv b) {
                return Integer.compare(a.e - a.s, b.e - b.s);
            }
        };

        for (int i = 0; i < busy.size(); i++) {
            Inv next = busy.get((i + 1) % busy.size());
            Inv prev = busy.get(i);

            int gap = next.s - prev.e;
            if (gap < 0) {
                gap += 1440;
            }
            // System.err.println(gap.e - gap.s);
            if (next.c ^ prev.c) {
                // neutral.add(gap);
                neutralTime += gap;
                neutralCount++;
            } else if (prev.c) {
                if (gap > 0) cEasy.add(gap);
            } else {
                if (gap > 0) jEasy.add(gap);
            }
        }

        // System.err.println("Case " + testCase);
        // System.err.println(busy.size());
        // System.err.println(cEasy.size() + jEasy.size() + neutralCount);

        Collections.sort(cEasy, Collections.reverseOrder());
        Collections.sort(jEasy);
        // Collections.sort(neutral, longFirst);

        int swaps = 2 * cEasy.size() + neutralCount;

        while (cTime < 12 * 60 && !cEasy.isEmpty()) {
            int next = cEasy.remove(cEasy.size() - 1);
            cTime += next;
            if (cTime <= 12 * 60) {
                swaps -= 2;
            }
        }
        // System.err.println(cEasy);
        // System.err.println(cTime);

        if (cTime < 12 * 60) {
            cTime += neutralTime;
        }

        while (cTime < 12 * 60 && !jEasy.isEmpty()) {
            int next = jEasy.remove(jEasy.size() - 1);
            cTime += next;
            swaps += 2;
        }

        return swaps;
    }
}
