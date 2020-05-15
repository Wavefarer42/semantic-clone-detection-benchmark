package google.jam.r0AA;

/**
 * @author 0Be
 */
public class Dev5 {

    public static int run(String _pattern, int _num) {
        char[] faces = _pattern.toCharArray();
        int n = _num;

        int length = faces.length;

        int count = 0;

        for (int j = length - 1; j >= n - 1; j--) {
            if (faces[j] == '-') {
                count++;
                for (int k = j; k > j - n; k--) {
                    if (faces[k] == '+') {
                        faces[k] = '-';
                    } else {
                        faces[k] = '+';
                    }
                }
            }
        }

        for (int j = 0; j < length; j++) {
            if (faces[j] == '-') {
                break;
            }
        }

        return count;
    }
}
