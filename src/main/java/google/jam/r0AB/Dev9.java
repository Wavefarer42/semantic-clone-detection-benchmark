package google.jam.r0AB;

/**
 * @author 2001zhaozhao
 */
public class Dev9 {
    public static long run(long _num) {
        //the number
        long number = _num;
        int[] digits = new int[20];

        //convert number to digits
        int numdigits = 0;
        while (number > 0) {
            digits[numdigits] = (int) (number % 10);
            number /= 10;
            numdigits++;
        }
        //this numdigits variable ends up at the right number of digits
        for (int i = 0; i < numdigits - 1 && !isTidy(digits); i++) {
            if (digits[i] < digits[i + 1]) {
                //wrong
                //subtract number to last 9 number
                digits[i + 1]--;
                for (int j = 0; j <= i; j++) {
                    digits[j] = 9;
                }
            }
        }
        long result = 0;
        //ocnvert back into number
        long mult = 1;
        for (int i = 0; i < 20; i++) {
            result += digits[i] * mult;
            mult *= 10;
        }

        return result;
    }

    static boolean isTidy(int[] number) {
        long lastdigit = 9;
        for (int i = 0; i < 20; i++) {
            if (number[i] > lastdigit) return false;
            lastdigit = number[i];
        }
        return true;
    }

}
