package google.jam.r0AA;

/**
 * @author 85T
 */
public class Dev8 {
    public static int run(String _pattern, int _num) {
        int sizeSurface = _num;
        char[] s = _pattern.toCharArray();
        int nFlip = flipper(s, sizeSurface);
        return nFlip;
    }

    static public int flipper(char[] s, int sizeSurface) {
        return flipper(s, sizeSurface, 0);
    }

    static public int flipper(char[] s, int sizeSurface, int numChar) {
        if (numChar < s.length - sizeSurface + 1) {
            if (s[numChar] == '+') {
                int next0 = flipper(s, sizeSurface, numChar + 1);
                return next0;
            } else {
                int next1 = 1 + flipper(flip(s, sizeSurface, numChar), sizeSurface, numChar + 1);
                return next1 == 0 ? -1 : next1;
            }
        } else { // last call
            boolean isHappy = true;
            for (int i = 0; numChar + i < s.length; i++)
                isHappy = isHappy && s[numChar + i] == '+';
            if (isHappy)
                return 0;
            else
                return -1;
        }
    }

    static public char[] flip(char[] s, int sizeSurface, int numChar) {
        for (int i = 0; i < sizeSurface; i++)
            s[numChar + i] = s[numChar + i] == '+' ? '-' : '+';
        return s;
    }
}