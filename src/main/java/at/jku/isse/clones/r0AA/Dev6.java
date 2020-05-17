package at.jku.isse.clones.r0AA;

/**
 * @author 3mara
 */
public class Dev6 {
    public static int run(String _pattern, int _num) {
        int result = 0;
        int sizeFlipper = _num;
        char[] array = _pattern.toCharArray();
        for (int j = 0; j <= array.length - sizeFlipper; j++) {
            if (array[j] == '-') {
                result++;
                for (int k = j; k < j + sizeFlipper; k++) {
                    array[k] = array[k] == '+' ? '-' : '+';
                }
            }
        }
        for (int j = array.length - sizeFlipper; j < array.length; j++) {
            if (array[j] == '-')
                result = -1;
        }

        return result;
    }
}
