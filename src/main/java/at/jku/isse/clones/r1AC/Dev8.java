package at.jku.isse.clones.r1AC;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

/**
 * @author m.radwan
 */
public class Dev8 {

    static int ad, ak, b, d;
    static int base_health;

    static class Node {
        int hd;
        int hk;

        int buffs;
        int debuffs;

        int dist;

        public Node(int hd, int hk, int buffs, int debuffs, int dist) {

            this.hd = hd;
            this.hk = hk;
            this.buffs = buffs;
            this.debuffs = debuffs;

            this.dist = dist;

        }

        public String toString() {
            return (hd + " " + hk + " " + buffs + " " + debuffs);
        }

        public boolean equals(Object o) {
            Node on = (Node) o;
            return on.hd == hd && on.hk == hk && on.buffs == buffs && on.debuffs == debuffs;
        }

        public int hashCode() {
            return (hd + " " + hk + " " + buffs + " " + debuffs).hashCode();
        }

        public boolean canWin() {
            int nextHk = hk - (ad + buffs * b);
            return nextHk <= 0;
        }

        public Node attack() {
            int nextHk = hk - (ad + buffs * b);
            int nextHd = hd - (Math.max(ak - debuffs * d, 0));
            if (nextHd <= 0)
                return null;
            return new Node(nextHd, nextHk, buffs, debuffs, dist + 1);
        }

        public Node cure() {
            int nextHk = hk;
            int nextHd = base_health - (Math.max(ak - debuffs * d, 0));
            if (nextHd <= 0)
                return null;
            return new Node(nextHd, nextHk, buffs, debuffs, dist + 1);

        }

        public Node buff() {
            if (b == 0)
                return null;
            int nextHk = hk;
            int nextHd = hd - (Math.max(ak - debuffs * d, 0));
            if (nextHd <= 0)
                return null;
            return new Node(nextHd, nextHk, buffs + 1, debuffs, dist + 1);
        }

        public Node debuff() {
            if (d == 0)
                return null;
            int nextHk = hk;
            int nextHd = hd - (Math.max(ak - (debuffs + 1) * d, 0));
            if (nextHd <= 0)
                return null;
            return new Node(nextHd, nextHk, buffs, debuffs + 1, dist + 1);
        }
    }

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {

        int hd = base_health = _hd;
        ad = _ad;
        int hk = _hk;
        ak = _ak;
        b = _b;
        d = _d;

        HashSet<Node> v = new HashSet<>();
        Queue<Node> q = new ArrayDeque<>();

        Node root = new Node(hd, hk, 0, 0, 0);
        q.add(root);
        v.add(root);

        boolean found = false;
        while (!q.isEmpty()) {
            Node current = q.poll();

            if (current.canWin()) {
                return current.dist + 1;
            }

            Node[] next = {current.attack(), current.cure(), current.buff(), current.debuff()};
            for (Node n : next) {
                if (n != null && !v.contains(n)) {
                    v.add(n);
                    q.add(n);
                }
            }
        }
        return -1;
    }

}
