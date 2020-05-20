package at.jku.isse.clones.r1AC;

/**
 * @author alexrcoleman
 */
public class Dev1 {
    static int cureHealth, b, d;

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {
        int hd = _hd, ad = _ad, hk = _hk, ak = _ak;
        cureHealth = hd;
        b = _b;
        d = _d;

        ans = inf;
        debuff(hd, ad, hk, ak, 0);

//        out.printf("Case #%d: ", curT);
        if (ans == inf)
            return -1;
//            out.print("IMPOSSIBLE");
        else
            return ans;
//            out.print(ans);

//        out.println();
//        if (inType != STDIO)
//            System.out.println(curT + "/" + maxT);
    }

    static int inf = (int) 1e8;
    static int ans;

    static void debuff(int hd, int ad, int hk, int ak, int turn) {
        for (int debuffAmt = 0; debuffAmt <= 300; debuffAmt++) {
            buff(hd, ad, hk, ak, turn);
            if (hd - Math.max(0, (ak - d)) <= 0) {
                // cure
                turn++;
                //				if(turn > ans) return;
                hd = cureHealth;
                hd -= ak;
                if (hd <= 0) break;
            }

            turn++;
            //			if(turn > ans) return;
            ak -= d;
            if (ak < 0) ak = 0;

            hd -= ak;
            if (hd <= 0) break;
        }
    }

    static void buff(int hd, int ad, int hk, int ak, int turn) {
        for (int buffAmt = 0; buffAmt <= 300; buffAmt++) {
            attack(hd, ad, hk, ak, turn);
            if (hd - ak <= 0) {
                // cure
                turn++;
                //				if(turn > ans) return;
                hd = cureHealth;
                hd -= ak;
                if (hd <= 0) break;
            }

            turn++;
            //			if(turn > ans) return;
            ad += b;

            hd -= ak;
            if (hd <= 0) break;
        }
    }

    static void attack(int hd, int ad, int hk, int ak, int turn) {
        for (int attackAmt = 0; attackAmt <= 300; attackAmt++) {
            if (hd - ak <= 0 && hk - ad > 0) {
                // cure
                turn++;
                if (turn > ans) return;
                hd = cureHealth;
                hd -= ak;
                if (hd <= 0) break;
            }
            //			if(turn > ans) return;
            turn++;
            //			if(turn > ans) return;
            hk -= ad;
            if (hk <= 0) {
                ans = Math.min(ans, turn);
                //				System.out.println("Won with " + hd + "," + ad + "," + hk + "," + ak + "," + turn);
                break;
            }
            hd -= ak;
            if (hd <= 0) break;
        }
    }
}
