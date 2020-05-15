package google.jam.r0AC;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author alexrcoleman
 */
public class Dev3 {
    public static Result run(int _l, int _r) {
        int n = _l, k = _r;
        PriorityQueue<Location> pq = new PriorityQueue<>();
        pq.offer(create(n));
        while (k > 1) {
            //				System.out.println(pq);
            Location l = pq.poll();
            Location a = create(l.l), b = create(l.r);
            if (a != null) {
                a.i += l.i;
                pq.offer(a);
            }
            if (b != null) {
                b.i += l.i + l.l + 1;
                pq.offer(b);
            }
            k--;
        }
        Location ans = pq.poll();
        return new Result(Math.max(ans.l, ans.r), Math.min(ans.l, ans.r));
    }


    public static Location create(int n) {
        if (n == 0)
            return null;
        n--;
        return new Location(0, n / 2, (n+1) / 2);
    }

    static class Location implements Comparable<Location> {
        int l, r, i;

        public Location(int i, int l, int r) {
            this.i = i;
            this.l = l;
            this.r = r;
        }
        @Override
        public int compareTo(Location o) {
            int comp = -Integer.compare(Math.min(l,r), Math.min(o.l,o.r));
            if(comp == 0)
                comp = -Integer.compare(Math.max(l,r), Math.max(o.l,o.r));
            if(comp == 0)
                comp = Integer.compare(i, o.i);
            return comp;

        }
        @Override
        public String toString() {
            return "[" + l + "," + r + "," + i + "]";
        }
    }
}
