package at.jku.isse.clones.r1AC;

import java.util.*;

/**
 * @author Me.
 */
public class Dev9 {

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {
        int dragonMax = _hd;
        int dragonAttack = _ad;
        int knightMax = _hk;
        int knightAttack = _ak;
        int buff = _b;
        int debuff = _d;

        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.add(new State(dragonMax, knightMax, dragonAttack, knightAttack, 0, new ArrayList<>()));

        Set<State> visited = new HashSet<>();

        while(pq.size() > 0) {
            State s = pq.poll();

            if (visited.contains(s)) continue;
            visited.add(s);

            //System.err.println(s);
            if (s.kH <= 0) return s.moves;

            // Attack
            int nKH = s.kH - s.dA;
            int ndH = s.dH;
            if (nKH > 0)
                ndH -= s.kA;
            if (ndH > 0) {
                List<String> actions = new ArrayList<>(s.actions);
                actions.add("Attack");
                pq.add(new State(ndH, nKH, s.dA, s.kA, s.moves + 1, actions));
            }

            // Buff
            int ndA = s.dA + buff;
            ndH = s.dH - s.kA;
            if (ndH > 0) {
                List<String> actions = new ArrayList<>(s.actions);
                actions.add("Buff");
                pq.add(new State(ndH, s.kH, ndA, s.kA, s.moves+1, actions));
            }

            // Cure
            ndH = dragonMax - s.kA;
            if (ndH > 0) {
                List<String> actions = new ArrayList<>(s.actions);
                actions.add("Cure");
                pq.add(new State(ndH, s.kH, s.dA, s.kA, s.moves+1, actions));
            }

            // Debuff
            int nkA = Math.max(0, s.kA - debuff);
            ndH = s.dH - nkA;
            if (ndH > 0) {
                List<String> actions = new ArrayList<>(s.actions);
                actions.add("Debuff");
                pq.add(new State(ndH, s.kH, s.dA, nkA, s.moves+1, actions));
            }
        }

        return -1;
    }

    static class State implements Comparable<State> {
        int dH;
        int kH;
        int dA;
        int kA;
        int moves;

        List<String> actions;


        State(int dH, int kH, int dA, int kA, int moves, List<String> actions) {
            this.dH = dH;
            this.kH = kH;
            this.dA = dA;
            this.kA = kA;
            this.moves = moves;
            this.actions = actions;
        }

        @Override
        public int compareTo(State o) {
            int myDist = moves; //+ (kH+dA-1) / dA;
            int oDist = o.moves; //+ (o.kH + o.dA-1) / o.dA;
            return myDist - oDist;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof State) {
                State s = (State) o;
                return dH == s.dH && kH == s.kH && dA == s.dA && kA == s.kA;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return dH * 1000000 + kH * 10000 + dA * 100 + kA;
        }

        @Override
        public String toString() {
            return "HP: " + dH + " vs " + kH + " ATK: " + dA + " vs " + kA + ":" + actions;
        }
    }

}

