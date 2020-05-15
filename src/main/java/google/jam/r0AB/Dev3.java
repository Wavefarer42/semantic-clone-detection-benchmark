package google.jam.r0AB;

import java.util.Arrays;

/**
 * @author ChadiEm
 */
public class Dev3 {
    public static long run(long _num) {

        char[] input = String.valueOf(_num).toCharArray();
        char[] results = solve(input);

        return Long.parseLong(String.valueOf(results));
    }

    private static char[] solve(char[] inputNumber) {
        char[] outputNumber = Arrays.copyOf(inputNumber, inputNumber.length);

        if (outputNumber.length == 1) {
            return outputNumber;
        }

        for (int i = outputNumber.length - 1; i >= 1; i--) {
            int curNumber = Integer.parseInt(Character.toString(outputNumber[i]));
            int prevNumber = Integer.parseInt(Character.toString(outputNumber[i - 1]));

            if (curNumber < prevNumber) {
                outputNumber[i - 1] = Integer.toString(prevNumber - 1).charAt(0);
                for (int j = i; j < outputNumber.length; j++) {
                    outputNumber[j] = '9';
                }
            }
        }

        return outputNumber;
    }
}
