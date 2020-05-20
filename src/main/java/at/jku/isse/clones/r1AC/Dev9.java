package at.jku.isse.clones.r1AC;

import java.io.PrintWriter;
import java.util.*;

/**
 * @author mikhailOK
 */
public class Dev9 {

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {
        Set<State> visited = new HashSet<>();
        List<State> queue = new ArrayList<>();
        List<State> next = new ArrayList<>();
        State initial = new State(_hd, _ad, _hk, _ak);
        queue.add(initial);
        visited.add(initial);
        int rounds = 1;
        while (!queue.isEmpty()) {
            for (State state : queue) {
                if (state.haveWon()) {
                    return rounds;
                }
            }
            for (State state : queue) {
                for (State to : new State[]{state.attack(), state.heal(_hd), state.buff(_b), state.debuff(_d)}) {
                    if (to == null) {
                        continue;
                    }
                    if (visited.add(to)) {
                        next.add(to);
                    }
                }
            }
            queue.clear();
            List<State> t = queue;
            queue = next;
            next = t;
            rounds++;
        }
        return -1;
    }

    static class State {
        int hd;
        int ad;
        int hk;
        int ak;

        public State(int hd, int ad, int hk, int ak) {
            this.hd = hd;
            this.ad = ad;
            this.hk = hk;
            this.ak = ak;
        }

        boolean haveWon() {
            return ad >= hk;
        }

        State buff(int buff) {
            if (ak >= hd) {
                return null;
            }
            return new State(hd - ak, ad + buff, hk, ak);
        }

        State debuff(int debuff) {
            int newAk = Math.max(0, ak - debuff);
            if (newAk >= hd) {
                return null;
            }
            return new State(hd - newAk, ad, hk, newAk);
        }

        State heal(int hp) {
            if (ak >= hp) {
                return null;
            }
            return new State(hp - ak, ad, hk, ak);
        }

        State attack() {
            if (ak >= hd) {
                return null;
            }
            return new State(hd - ak, ad, hk - ad, ak);
        }


        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            if (hd != state.hd) return false;
            if (ad != state.ad) return false;
            if (hk != state.hk) return false;
            return ak == state.ak;
        }


        public int hashCode() {
            int result = hd;
            result = 31 * result + ad;
            result = 31 * result + hk;
            result = 31 * result + ak;
            return result;
        }


        public String toString() {
            return "State{" +
                    "hd=" + hd +
                    ", ad=" + ad +
                    ", hk=" + hk +
                    ", ak=" + ak +
                    '}';
        }

    }
}

