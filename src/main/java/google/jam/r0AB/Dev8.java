package google.jam.r0AB;

/**
 * @author a.v2612
 */
public class Dev8 {
    public static long run(long _num) {
        return LastTidy(Long.toString(_num));
    }

    static long LastTidy(String n) {
        char[] num = n.toCharArray();
        if (Tidy(Long.parseLong(n))) {
            return Long.parseLong(n);
        } else {
            int a = Integer.parseInt("" + num[0]);
            for (int i = 1; i < num.length; i++) {
                if (Integer.parseInt("" + num[i]) > a) {
                    a = Integer.parseInt("" + num[i]);
                } else {
                    num[i - 1] = ("" + (Integer.parseInt("" + num[i - 1]) - 1)).charAt(0);
                    for (int j = i; j < num.length; j++) {
                        num[j] = '9';
                    }
                    break;
                }
            }
        }
        return LastTidy(new String(num));
    }


    static boolean Tidy(long a) {
        boolean result = true;
        long lastMax = 10;
        while (a > 0) {
            long x = a % 10;
            if (x > lastMax) {
                result = false;
                break;
            } else {
                lastMax = x;
                a = a / 10;
            }

        }
        return result;
    }
}
