package at.jku.isse.clones.r0AB;

/**
 * @author 4castle
 */
public class Dev7 {
    public static long run(long _num) {
        long N = _num;
        long largestTidy = findLargestTidyNumberBelow(N);
        return largestTidy;
    }

    private static long findLargestTidyNumberBelow(long N) {
        long n = N;
        while (true) {
            long placeOfUntidy = (long) Math.pow(10, findUntidy(n));
            if (placeOfUntidy == 1) {
                return n;
            }
            n = (n / placeOfUntidy) * placeOfUntidy - 1;
        }
    }

    private static int findUntidy(long num) {
        long digit, prevDigit = 9;
        int place = 0;
        int highestPlace = 0;
        while (num > 0) {
            digit = num % 10;
            if (digit == 0 || digit > prevDigit) {
                highestPlace = place;
            }
            num /= 10;
            prevDigit = digit;
            place++;
        }
        return highestPlace;
    }
}
