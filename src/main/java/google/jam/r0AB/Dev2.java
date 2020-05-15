package google.jam.r0AB;

/**
 * @author Cratus
 */
public class Dev2 {

    public static long run(long _num) {
        return Long.parseLong(solve(String.valueOf(_num)));
    }

    static String solve(String num) {
        char[] n = num.toCharArray();
        String out = String.valueOf(n);
        int sameStart = -1;
        for (int j = 0; j < n.length - 1; j++) {
            int current = Integer.parseInt("" + n[j]);
            int next = Integer.parseInt("" + n[j + 1]);
            if (current > next) {
                int startPoint = j;
                if (sameStart != -1)
                    startPoint = sameStart;

                n[startPoint] = (char) ((current - 1) + '0');
                for (int k = startPoint + 1; k < n.length; k++) {
                    n[k] = '9';
                }
                //strip leading zeroes
                int l = 0;
                while (n[l] == '0')
                    l++;
                out = String.valueOf(n, l, n.length - l);

                break;
            } else if (current == next) {
                if (sameStart == -1)
                    sameStart = j;
            } else {
                sameStart = -1;
            }
        }
        return out;
    }
}
