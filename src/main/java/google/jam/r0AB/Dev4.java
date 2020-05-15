package google.jam.r0AB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author bds
 */
public class Dev4 {
    public static long run(long _num) {
        long num = _num;
        if (num < 10) return num;
        List<Integer> lstDigits = new ArrayList<>();
        while (num > 0) {
            lstDigits.add((int) (num % 10));
            num /= 10;
        }
        Collections.reverse(lstDigits);
        int n = lstDigits.size();
        int digits[] = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = lstDigits.get(i);
        }

        while (true) {
            int curr = 0;
            boolean found = false;
            while (curr < n - 1) {
                if (digits[curr] > digits[curr + 1]) {
                    found = true;
                    break;
                }
                curr++;
            }
            if (!found) break;

            digits[curr]--;
            while (curr > 0 && digits[curr] == 0) {
                curr--;
                digits[curr]--;
            }
            curr++;
            while (curr < n) {
                digits[curr++] = 9;
            }
        }


        long result = digits[0];
        for (int i = 1; i < n; i++) {
            result = result * 10 + digits[i];
        }
        return result;
    }
}
