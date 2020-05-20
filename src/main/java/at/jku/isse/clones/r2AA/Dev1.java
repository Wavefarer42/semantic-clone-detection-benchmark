package at.jku.isse.clones.r2AA;

import java.util.Arrays;

/**
 * @author adm1n123
 */
public class Dev1 {
    static class Letter implements Comparable<Letter> {
        String c;
        int cnt;

        public Letter(String c, int cnt) {
            super();
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Letter o) {
            return Integer.compare(o.cnt, cnt);
        }
    }

    public static String run(int _n, int[] _colors) {
        String[] val;
        int[] next;

        int n = _n;
        String[] cs = new String[]{"R", "O", "Y", "G", "B", "V"};
        Letter[] lets = new Letter[cs.length];
        for (int i = 0; i < lets.length; i++) {
            lets[i] = new Letter(cs[i], _colors[i]);
        }
        Arrays.sort(lets);
        Letter[] a = new Letter[3];
        int ap = 0;
        for (int i = 0; i < lets.length; i++) {
            if (lets[i].cnt != 0) {
                a[ap++] = lets[i];
            }
        }
        while (ap < 3) {
            a[ap++] = new Letter("Z", 0);
        }
        if (a[0].cnt > a[1].cnt + a[2].cnt) {
            return "IMPOSSIBLE";
        }

        int head = 0;
        final int MAX_SIZE = 2000;
        next = new int[MAX_SIZE];
        val = new String[MAX_SIZE];

        head = 0;
        int cntE = 0;
        for (int i = 0; i < a[0].cnt; i++) {
            next[++cntE] = head;
            head = cntE;
            val[cntE] = a[0].c;
        }
        int cur = head;
        for (int type = 1; type < 3; type++) {
            for (int i = 0; i < a[type].cnt; i++) {
                int nextNext = next[cur];
                next[cur] = ++cntE;
                next[cntE] = nextNext;
                val[cntE] = a[type].c;
                cur = nextNext;
                if (cur == 0) {
                    cur = head;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (int e = head; e != 0; e = next[e]) {
            result.append(val[e]);
        }
        return result.toString();
    }
}

