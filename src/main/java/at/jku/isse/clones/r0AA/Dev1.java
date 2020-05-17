package at.jku.isse.clones.r0AA;

/**
 * @author araver
 */
public class Dev1 {
    public static int run(String _pattern, int _num) {
        int j, k, l;

        int n, p, count;
        int[] a;

        a = new int[1000];
        for (j = 0; j < 100; j++) a[j] = 0;

        n = _pattern.length();
        p = _num;


        for (j = 0; j < n; j++) {
            a[j] = _pattern.charAt(j) == '-' ? 0 : 1;
        }

        count = 0;

        while (true) {
            k = findLastHappy(n, a);
            l = findFirstUnHappy(n, a);

            if (k == -1) {//all are Happy
                break;
            }
            //Greedy flip
            if (l + p <= n) {
                //flip the first unhappy and p-1 to the right of it.
                flip(l, p, a);
                //prettyPrint();
                count++;
            } else {
                count = -1;
                break;
            }
        }

        if (count > -1) return count;
        else return -1;
    }

    private static void flip(int l, int p, int[] a) {
        //flip [l ... l+p-1]
        int i;
        for (i = l; i < l + p; i++) {
            a[i] = 1 - a[i];
        }
    }

    private static int findLastHappy(int n, int[] a) {
        int i;
        for (i = n - 1; i >= 0 && a[i] == 1; i--) ;
        return i;
    }

    private static int findFirstUnHappy(int n, int[] a) {
        int i;
        for (i = 0; i < n && a[i] == 1; i++) ;
        return i;
    }
}
