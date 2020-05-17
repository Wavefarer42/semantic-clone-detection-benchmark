package at.jku.isse.clones.r0AA;

/**
 * @author Alibi
 */
public class Dev0 {

    private static boolean[] pattern;
    private static int width;

    public static int run(String _pattern, int _num) {
        int len = _pattern.length();
        Dev0.pattern = new boolean[len];
        for (int i = 0; i < len; i++) {
            Dev0.pattern[i] = _pattern.charAt(i) == '+';
        }
        width = _num;
        int left = 0;
        int right = len - width;
        int flips = 0;
        while (true) {
            if (right < left) {
                break;
            }
            if (!Dev0.pattern[left]) {
                flips++;
                flip(left);
            }
            if (right == left) {
                break;
            }
            if (!Dev0.pattern[right + width - 1]) {
                flips++;
                flip(right);
            }
            right--;
            left++;
        }
        boolean success = true;
        for (int i = 0; i < len; i++) {
            if (!Dev0.pattern[i]) {
                success = false;
                break;
            }
        }
        if (success) {
            return flips;
        } else {
            return -1;
        }
    }

    private static void flip(int position) {
        for (int i = position; i < position + width; i++) {
            pattern[i] = !pattern[i];
        }

    }
}