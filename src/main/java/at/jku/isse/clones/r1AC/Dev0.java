package at.jku.isse.clones.r1AC;

import java.util.ArrayDeque;

/**
 * @author Aemon
 */
public class Dev0 {
    private static final int inf = 999999999;

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {

        int Hd = _hd, Ad = _ad,
                Hk = _hk, Ak = _ak,
                B = _b, D = _d;

        boolean[][][][] memo = new boolean[Hd + 1][Hk + 1][101][101];


        memo[Hd][Hk][0][0] = true;

        int min = inf;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(Hd);
        q.add(Hk);
        q.add(0);
        q.add(0);
        q.add(0);

        int att1 = 0, att2 = 0;
        while (!q.isEmpty()) {

            int dragon = q.pollFirst(),
                    knight = q.pollFirst(),
                    buff = q.pollFirst(),
                    debuff = q.pollFirst(),
                    dist = q.pollFirst();

            if (dragon <= 0) {
                continue;
            }

            att1 = buff * B + Ad;
            att2 = -debuff * D + Ak;
            if (att2 < 0) att2 = 0;

            //heal;
            if (!memo[Math.max(0, Hd - att2)][knight][buff][debuff]) {
                memo[Math.max(0, Hd - att2)][knight][buff][debuff] = true;
                q.add(Math.max(0, Hd - att2));
                q.add(knight);
                q.add(buff);
                q.add(debuff);
                q.add(dist + 1);
            }

            //buff
            if (buff < 100 && !memo[Math.max(0, dragon - att2)][knight][buff + 1][debuff]) {
                memo[Math.max(0, dragon - att2)][knight][buff + 1][debuff] = true;
                q.add(Math.max(0, dragon - att2));
                q.add(knight);
                q.add(buff + 1);
                q.add(debuff);
                q.add(dist + 1);
            }

            //att
            if (knight - att1 <= 0) {
                min = dist + 1;
                break;
            } else if (!memo[Math.max(dragon - att2, 0)][knight - att1][buff][debuff]) {
                memo[Math.max(dragon - att2, 0)][knight - att1][buff][debuff] = true;
                q.add(Math.max(dragon - att2, 0));
                q.add(knight - att1);
                q.add(buff);
                q.add(debuff);
                q.add(dist + 1);
            }

            att2 = -(debuff + 1) * D + Ak;
            att2 = Math.max(att2, 0);
            if (debuff >= 100) continue;
            if (!memo[Math.max(dragon - att2, 0)][knight][buff][debuff + 1]) {
                memo[Math.max(dragon - att2, 0)][knight][buff][debuff + 1] = true;
                q.add(Math.max(dragon - att2, 0));
                q.add(knight);
                q.add(buff);
                q.add(debuff + 1);
                q.add(dist + 1);
            }

        }

        return min == inf ? -1 : min;
    }
}
