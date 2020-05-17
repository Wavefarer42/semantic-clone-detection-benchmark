package at.jku.isse.clones.r0AA;

/**
 * @author ChadiEM
 */
public class Dev3 {
    public static int run(String _pattern, int _num) {
        int flipCount = 0;

        char[] sequence = _pattern.toCharArray();
        int flipSize = _num;

        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] == '-') {
                if (i + flipSize <= sequence.length) {
                    flip(sequence, i, flipSize);
                    flipCount++;
                } else {
                    return -1;
                }
            }
        }

        return flipCount;
    }

    private static void flip(char[] sequence, int startIndex, int flipSize) {
        for (int i = 0; i < flipSize; i++) {
            sequence[startIndex + i] = flip(sequence[startIndex + i]);
        }
    }

    private static char flip(char c) {
        if (c == '-') {
            return '+';
        } else {
            return '-';
        }
    }
}
