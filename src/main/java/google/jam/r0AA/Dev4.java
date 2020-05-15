package google.jam.r0AA;

/**
 * @author bds
 */
public class Dev4 {
    public static int run(String _pattern, int _num) {
        boolean allPlus = true;
        int n = _pattern.length();
        for (int i = 0; i < _pattern.length(); i++) {
            if (_pattern.charAt(i) != '+') {
                allPlus = false;
                break;
            }
        }
        if (allPlus) return 0;
        char pc[] = _pattern.toCharArray();
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (pc[i] == '+') continue;
            if (i + _num > n) {
                result = -1;
                break;
            }
            for (int j = 0; j < _num; j++) {
                int curr = pc[i + j];
                pc[i + j] = (curr == '-') ? '+' : '-';
            }
            result++;
        }
        return result;
    }
}
