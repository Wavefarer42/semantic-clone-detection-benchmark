package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ababwa
 */
public class Dev8 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {

        char[][] junk = new char[_r][];
        for (int k = 0; k < _r; k++) {
            junk[k] = _patterns.get(k).toCharArray();
        }

        ArrayList<Character> done = new ArrayList<>();
        for (int ci = 0; ci < _c; ci++) {
            for (int ri = 0; ri < _r; ri++) {
                char s = junk[ri][ci];
                if (s != '?' && !done.contains(s)) {

                    int top = ri;
                    int bottom = ri;
                    int left = ci;
                    int right = ci;

                    while (true) {
                        if (top == 0)
                            break;
                        boolean stop = false;
                        for (int k = left; k <= right; k++) {
                            if (junk[top - 1][k] != '?') {
                                stop = true;
                                break;
                            }
                        }
                        if (stop)
                            break;
                        top--;
                        for (int k = left; k <= right; k++) {
                            junk[top][k] = s;
                        }
                    }

                    while (true) {
                        if (bottom == _r - 1)
                            break;
                        boolean stop = false;
                        for (int k = left; k <= right; k++) {
                            if (junk[bottom + 1][k] != '?') {
                                stop = true;
                                break;
                            }
                        }
                        if (stop)
                            break;
                        bottom++;
                        for (int k = left; k <= right; k++) {
                            junk[bottom][k] = s;
                        }
                    }

                    while (true) {
                        if (left == 0)
                            break;
                        boolean stop = false;
                        for (int k = top; k <= bottom; k++) {
                            if (junk[k][left - 1] != '?') {
                                stop = true;
                                break;
                            }
                        }
                        if (stop)
                            break;
                        left--;
                        for (int k = top; k <= bottom; k++) {
                            junk[k][left] = s;
                        }
                    }

                    while (true) {
                        if (right == _c - 1)
                            break;
                        boolean stop = false;
                        for (int k = top; k <= bottom; k++) {
                            if (junk[k][right + 1] != '?') {
                                stop = true;
                                break;
                            }
                        }
                        if (stop)
                            break;
                        right++;
                        for (int k = top; k <= bottom; k++) {
                            junk[k][right] = s;
                        }
                    }
                    done.add(s);
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (int k = 0; k < _r; k++) {
            result.add(new String(junk[k]));
        }

        return result;
    }
}
