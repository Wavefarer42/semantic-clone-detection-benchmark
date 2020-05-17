package at.jku.isse.clones.r0AA;

/**
 * @author Cratus
 */
public class Dev2 {
    public static int run(String _pattern, int _num) {
        String pc = _pattern;
        int K = _num;
        int flips = 0;
        boolean impossible = false;

        char[] in = pc.toCharArray();
        for (int j = 0; j < in.length; j++) {
            char next = in[j];
            if (next == '-') {
                for (int l = j; l < j + K; l++) {
                    if (l >= in.length) {
                        impossible = true;
                        break;
                    } else {
                        if (in[l] == '-')
                            in[l] = '+';
                        else
                            in[l] = '-';
                    }
                }
                flips++;
            }
            if (impossible)
                break;
        }

        if (impossible)
            return -1;
        else
            return flips;
    }
}
