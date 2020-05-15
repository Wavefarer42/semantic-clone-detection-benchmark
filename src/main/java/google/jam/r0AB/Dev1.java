package google.jam.r0AB;

/**
 * @author Alibi
 */
public class Dev1 {

    public static long run(long _num) {
        return Long.parseLong(solve(String.valueOf(_num)));
    }

    private static String solve(String input) {
        int len = input.length();
        byte[] digits = new byte[len];
        for (int i = 0; i < len; i++) {
            digits[i] = Byte.parseByte("" + input.charAt(i));
        }
        for (int i = len - 1; i > 0; i--) {
            if (digits[i] < digits[i - 1]) {
                digits[i - 1]--;
                for (int j = i; j < len; j++) {
                    digits[j] = 9;
                }
            }
        }

        StringBuffer result = new StringBuffer();
        boolean leadingZeroes = true;
        for (int i = 0; i < len; i++) {
            if (digits[i] > 0) {
                leadingZeroes = false;
            }
            if (!leadingZeroes) {
                result.append(Byte.toString(digits[i]));
            }
        }
        return result.toString();
    }
}
