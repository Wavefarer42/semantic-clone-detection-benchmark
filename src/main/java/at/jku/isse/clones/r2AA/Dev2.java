package at.jku.isse.clones.r2AA;

import java.util.Arrays;

/**
 * @author
 */
public class Dev2 {
    static class item implements Comparable<item> {

        public char c;
        public int freq;

        public item(char myc, int f) {
            c = myc;
            freq = f;
        }

        public int compareTo(item other) {
            return other.freq - this.freq;
        }
    }

    public static String run(int _n, int[] _colors) {

        int n = _n;
        int[] data = new int[6];
        boolean ok = true;
        for (int i = 0; i < 6; i++) {
            data[i] = _colors[i];
            if (2 * data[i] > n)
                ok = false;
        }

        if (!ok) {
            return "IMPOSSIBLE";
        } else {

            item[] list = new item[3];
            list[0] = new item('R', data[0]);
            list[1] = new item('Y', data[2]);
            list[2] = new item('B', data[4]);
            Arrays.sort(list);

            char[] res = new char[n];
            Arrays.fill(res, ' ');
            int cur = 0;
            boolean flag = false;
            for (int z = 0; z < 3; z++) {

                for (int i = 0; i < list[z].freq; i++) {

                    res[cur] = list[z].c;
                    cur = cur + 2;
                    if (!flag && cur >= n) {
                        cur = 1;
                        flag = true;
                    }
                }
            }


            return new String(res);
        }
    }
}

