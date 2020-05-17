package at.jku.isse.clones.r0AB;


/**
 * @author 0Be
 */
public class Dev5 {
    public static long run(long _num) {

        String input = String.valueOf(_num);

        char[] digits = input.toCharArray();
        int length = digits.length;

        StringBuilder currAns = new StringBuilder();

        for (int j = length - 1; j > 0; j--) {
            if (digits[j] < digits[j - 1]) {
                if (digits[j] == '0') {
                    digits[j] = '9';
                } else {
                    digits[j]--;
                }
                if (digits[j - 1] == '0') {
                    digits[j - 1] = '9';
                } else {
                    digits[j - 1]--;
                }
            }
        }
        for (int j = 0; j < length - 1; j++) {
            if (digits[j] > digits[j + 1]) {
                digits[j + 1] = '9';
            }
            currAns.append(digits[j]);
        }
        currAns.append(digits[length - 1]);

        return Long.parseLong(currAns.toString());
    }
}
