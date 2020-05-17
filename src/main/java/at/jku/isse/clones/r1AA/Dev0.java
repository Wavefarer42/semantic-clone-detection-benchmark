package at.jku.isse.clones.r1AA;


import java.util.ArrayList;
import java.util.List;

/**
 * @author anton.akhi
 */
public class Dev0 {

    public static List<String> run(int _r, int _c, List<String> _patterns) {
        int n = _r;
        int m = _c;
        char[][] a = new char[n][];
        for (int i = 0; i < a.length; i++) {
            a[i] = _patterns.get(i).toCharArray();
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == '?' && j > 0) {
                    a[i][j] = a[i][j - 1];
                }
            }
            for (int j = a[i].length - 1; j >= 0; j--) {
                if (j + 1 < a[i].length && a[i][j] == '?') {
                    a[i][j] = a[i][j + 1];
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] == '?' && i > 0 && a[i - 1][0] != '?') {
                for (int j = 0; j < a[i].length; j++) {
                    a[i][j] = a[i - 1][j];
                }
            }
        }
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i][0] == '?' && i + 1 < a.length && a[i + 1][0] != '?') {
                for (int j = 0; j < a[i].length; j++) {
                    a[i][j] = a[i + 1][j];
                }
            }
        }

        List<String> patterns = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            patterns.add(String.valueOf(a[i]));
        }

        return patterns;
    }
}
