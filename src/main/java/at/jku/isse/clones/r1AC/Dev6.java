package at.jku.isse.clones.r1AC;

/**
 * @author jchen314
 */
public class Dev6 {

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {

        long Hd = _hd;
        long Ad = _ad;
        long Hk = _hk;
        long Ak = _ak;
        long B = _b;
        long D = _d;

        if (Ad >= Hk) {
            return 1;
        }

        //            if (Ad < Hk && Ak + D >= Hd) {
        //                System.out.println("IMPOSSIBLE");
        //                continue;
        //            }
        //            if (Ad + B < Hk && 2 * Ad < Hk && 2 * Ak - 3 * D >= Hd) {
        //                System.out.println("IMPOSSIBLE");
        //                continue;
        //            }

        long minABTurns = Long.MAX_VALUE;
        if (B > 0) {
            double approxBuff = (Math.sqrt(B * Hk) - Ad) / B;
            long low = Math.max(0, (long) Math.floor(approxBuff - 5));
            for (long buffTurns = low; buffTurns <= low + 10; buffTurns++) {
                long attackPower = Ad + buffTurns * B;
                long attackTurns = (Hk + attackPower - 1) / attackPower;
                minABTurns = Math.min(minABTurns, attackTurns + buffTurns);
            }
        } else {
            minABTurns = (Hk + Ad - 1) / Ad;
        }

        long minTotalTurns = Long.MAX_VALUE;
        long maxDebuffTurns = D == 0 ? 0 : (Ak + D - 1) / D;
        debuff:
        for (int debuffTurns = 0; debuffTurns <= maxDebuffTurns; debuffTurns++) {
            int healTurns = 0;
            long attack = Ak;
            long currHealth = Hd;
            for (int i = 0; i < debuffTurns; i++) {
                if (attack - D >= currHealth) {
                    healTurns++;
                    currHealth = Hd - attack;
                    if (attack - D >= currHealth) {
                        continue debuff;
                    }
                }
                attack = Math.max(0, attack - D);
                currHealth -= attack;
            }
            for (int i = 0; i < minABTurns - 1; i++) {
                if (attack >= currHealth) {
                    healTurns++;
                    currHealth = Hd - attack;
                    if (attack >= currHealth) {
                        continue debuff;
                    }
                }
                currHealth -= attack;
            }
            minTotalTurns = Math.min(minTotalTurns, healTurns + debuffTurns + minABTurns);
            // if (attack > 0) {
            // long turnsToNextHeal = (currHealth + attack - 1) / attack;
            // if (turnsToNextHeal >= minABTurns) {
            // minTotalTurns = healTurns + debuffTurns + minABTurns;
            // } else {
            // long remainingABTurns = minABTurns - turnsToNextHeal + 1;
            // long turnsBetweenHeals = (Hd - 1) / attack - 1;
            // if (turnsBetweenHeals == 0) {
            // continue debuff;
            // }
            // long numHeals = (remainingABTurns - 2) / turnsBetweenHeals;
            // minTotalTurns = healTurns + debuffTurns + 1 + numHeals + minABTurns;
            // }
            // } else {
            // minTotalTurns = healTurns + debuffTurns + minABTurns;
            // }
        }
        if (minTotalTurns == Long.MAX_VALUE) {
            return -1;
        } else {
            return (int) minTotalTurns;
        }
    }

}
