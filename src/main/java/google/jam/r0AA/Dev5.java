package google.jam.r0AA;

/**
 * @author a.v2612
 */
public class Dev5 {

    public static int run(String _pattern, int _num) {

        int j = 0;
        int result = 0;
        char[] charArr = _pattern.toCharArray();
        while (j < charArr.length) {
            if (charArr[j] == '+') {
                j++;
                continue;
            } else {
                if (_pattern.substring(j).length() < _num) {
                    result = -1;
                    break;
                }
                for (int k = 0, l = j; k < _num; k++, l++) {
                    if (charArr[l] == '+')
                        charArr[l] = '-';
                    else
                        charArr[l] = '+';

                }
                result++;
            }
            j++;
        }
        return result;
    }
}
