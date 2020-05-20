package at.jku.isse.clones.r1AC;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * @author anton.akhi
 */
public class Dev2 {
    static int cureHealth, b, d;

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {
        int hd = _hd;
        int ad = _ad;
        int hk = _hk;
        int ak = _ak;
        int b = _b;
        int d = _d;
        Queue<State> q = new ArrayDeque<>();
        State s = new State(hd, ad, hk, ak, 0);
        q.add(s);
        HashMap<String, State> hm = new HashMap<>();
        hm.put(s.getString(), s);
        while (!q.isEmpty()) {
            s = q.poll();
            if (s.hk <= s.ad) {
                return s.steps + 1;
            }
            if (s.hd > s.ak) {
                // attack
                State sn = new State(s.hd - s.ak, s.ad, s.hk - s.ad, s.ak, s.steps + 1);
                if (!hm.containsKey(sn.getString())) {
                    hm.put(sn.getString(), sn);
                    q.add(sn);
                }
                // buff
                sn = new State(s.hd - s.ak, s.ad + b, s.hk , s.ak, s.steps + 1);
                if (!hm.containsKey(sn.getString())) {
                    hm.put(sn.getString(), sn);
                    q.add(sn);
                }
            }
            // heal
            State sn = new State(hd - s.ak, s.ad, s.hk, s.ak, s.steps + 1);
            if (sn.hd > 0 && !hm.containsKey(sn.getString())) {
                hm.put(sn.getString(), sn);
                q.add(sn);
            }
            // debuff
            sn = new State(s.hd - Math.max(0, s.ak - d), s.ad, s.hk, Math.max(0, s.ak - d), s.steps + 1);
            if (sn.hd > 0 && !hm.containsKey(sn.getString())) {
                hm.put(sn.getString(), sn);
                q.add(sn);
            }
        }
        return -1;
    }

    static class State {
        public State(int hd2, int ad2, int hk2, int ak2, int i) {
            hd = hd2;
            ad = ad2;
            hk = hk2;
            ak = ak2;
            steps = i;
        }

        int hd, ad, hk, ak;
        int steps;

        public String getString() {
            return hd + ":" + ad + ":" + hk + ":" + ak;
        }
    }
}
