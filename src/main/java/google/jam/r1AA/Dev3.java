package google.jam.r1AA;

import java.util.Arrays;
import java.util.List;

/**
 * @author AlSaeed
 */
public class Dev3 {

    public static List<String> run(int _r, int _c, List<String> _patterns) {
        int R = _r, C = _c;
        String[] G = new String[R];

        for (int i = 0; i < R; i++) {
            G[i] = _patterns.get(i);
        }
        String solution = solve(R, G);

        return Arrays.asList(solution.split("\n"));
    }

    static boolean isEmpty(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) != '?')
                return false;
        return true;
    }

    static String solve(String s) {
        StringBuilder bldr = new StringBuilder();
        char c = ' ';
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) != '?') {
                c = s.charAt(i);
                break;
            }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '?')
                c = s.charAt(i);
            bldr.append(c);
        }
        return bldr.toString();
    }

    static String solve(int R, String[] G) {
        StringBuilder bldr = new StringBuilder();
        String sol = "";
        for (int i = 0; i < R; i++)
            if (!isEmpty(G[i])) {
                sol = solve(G[i]);
                break;
            }
        for (int i = 0; i < R; i++) {
            if (!isEmpty(G[i]))
                sol = solve(G[i]);
            bldr.append(sol + "\n");
        }
        return bldr.toString();
    }
}
