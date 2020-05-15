package google.jam.r1AB;

import java.util.Arrays;

public class Dev7 {
    public static class Pair implements Comparable<Pair> {

        int low;
        int hi;

        public Pair(int low, int hi) {
            this.low = low;
            this.hi = hi;
        }

        public int compareTo(Pair other) {
            if (this.low == other.low) {
                return this.hi - other.hi;
            } else {
                return this.low - other.low;
            }
        }
    }

    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {

        int n = _ingredientsCount;
        int p = _packagesCount;
        int[] recipe = _ingredientQuantities;
        Pair[][] arr = new Pair[n][p];
        int[] point = new int[n];
        for (int nn = 0; nn < n; nn++) {
            for (int pp = 0; pp < p; pp++) {
                int lowB = 9 * recipe[nn];
                int hiB = 11 * recipe[nn];
                int sam = 10 * _packages[nn][pp];
                int max = sam / lowB;
                int min = sam / hiB;
                if (sam % hiB != 0) min++;
                arr[nn][pp] = new Pair(min, max);
            }
        }
        for (int qqqq = 0; qqqq < n; qqqq++) {
            Arrays.sort(arr[qqqq]);
        }
        for (int i = 0; i < n; i++) {
            point[i] = 0;
        }
        int served = 0;
        boolean dank = true;
        while (dank) {
            int att = 0;
            for (int sh = 0; sh < n; sh++) {
                int w = arr[sh][point[sh]].low;
                if (w > att) att = w;
            }
            boolean gucci = true;
            for (int kek = 0; kek < n; kek++) {
                if (arr[kek][point[kek]].hi < att) {
                    point[kek]++;
                    if (point[kek] == p) dank = false;
                    gucci = false;
                }
            }
            if (gucci) {
                served++;
                for (int memes = 0; memes < n; memes++) {
                    point[memes]++;
                    if (point[memes] == p) dank = false;
                }
            }
        }
        return served;
    }
}
