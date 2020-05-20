package at.jku.isse.clones.r1AC;

/**
 * @author melders
 */
public class Dev4 {

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {
        int Hd = _hd;
        int Ad = _ad;
        int Hk = _hk;
        int Ak = _ak;
        int B = _b;
        int D = _d;

        int maxTimesToDebuff = 0;
        int maxTimesToBuff = 0;

        if (B != 0) {
            maxTimesToBuff = (Hk - Ad + B - 1) / B;
        }

        if (D != 0) {
            maxTimesToDebuff = (Ak + D - 1) / D;
        }

        int bestScore = Integer.MAX_VALUE;
        for (int debuffs = 0; debuffs <= maxTimesToDebuff; debuffs++) {
            for (int buffs = 0; buffs <= maxTimesToBuff; buffs++) {
                int score = 0;
                int dragonHealth = Hd;
                int knightHealth = Hk;
                int dragonAttack = Ad;
                int knightAttack = Ak;
                boolean gameOver = false;
                boolean victory = false;

                boolean curedLastTurn = false;
                int debuffsLeft = debuffs;
                // Do debuffs
                while (debuffsLeft > 0 && !gameOver) {
                    if ((knightAttack - D) >= dragonHealth) {
                        if (curedLastTurn) {
                            gameOver = true;
                        } else {
                            // Cure
                            dragonHealth = Hd - knightAttack;
                            curedLastTurn = true;
                            score++;
                        }
                    } else {
                        // Debuff
                        curedLastTurn = false;
                        debuffsLeft--;
                        knightAttack -= D;
                        if (knightAttack < 0) {
                            knightAttack = 0;
                        }
                        dragonHealth -= knightAttack;
                        score++;
                    }
                }

                int buffsLeft = buffs;
                // Do buffs
                while (buffsLeft > 0 && !gameOver) {
                    if (knightAttack >= dragonHealth) {
                        if (curedLastTurn) {
                            gameOver = true;
                        } else {
                            // Cure
                            dragonHealth = Hd - knightAttack;
                            curedLastTurn = true;
                            score++;
                        }
                    } else {
                        // Buff
                        curedLastTurn = false;
                        buffsLeft--;
                        dragonAttack += B;
                        dragonHealth -= knightAttack;
                        score++;
                    }
                }

                // Attack
                while (!gameOver && !victory) {
                    if (knightAttack >= dragonHealth && knightHealth > dragonAttack) {
                        if (curedLastTurn) {
                            gameOver = true;
                        } else {
                            // Cure
                            dragonHealth = Hd - knightAttack;
                            curedLastTurn = true;
                            score++;
                        }
                    } else {
                        // Attack
                        curedLastTurn = false;
                        knightHealth -= dragonAttack;
                        score++;
                        if (knightHealth > 0) {
                            dragonHealth -= knightAttack;
                        } else {
                            victory = true;
                        }
                    }
                }

                if (victory) {
                    bestScore = bestScore > score ? score : bestScore;
                }
            }
        }

        return (bestScore == Integer.MAX_VALUE ? -1 : bestScore);
    }
}
