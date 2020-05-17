package at.jku.isse.clones.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aemon
 */
public class Dev5 {

    public static List<String> run(int _r, int _c, List<String> _patterns) {
        int R = _r, C = _c;

        char[][] mat = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = _patterns.get(i);
            for (int j = 0; j < C; j++) {
                mat[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            char prev = '-';
            for (int j = 0; j < C; j++) {
                if (prev != '-' && mat[i][j] == '?') {
                    mat[i][j] = prev;
                } else if (mat[i][j] != '?') prev = mat[i][j];
            }
            prev = '-';
            for (int j = C - 1; j >= 0; j--) {
                if (prev != '-' && mat[i][j] == '?') {
                    mat[i][j] = prev;
                } else if (mat[i][j] != '?') prev = mat[i][j];
            }
        }

        for (int i = 1; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == '?') mat[i][j] = mat[i - 1][j];
            }
        }
        for (int i = R - 1; i >= 0; i--) {
            for (int j = 0; j < C; j++) {
                if (mat[i][j] == '?') mat[i][j] = mat[i + 1][j];
            }
        }

        List<String> result = new ArrayList<>();
        for (char[] chars : mat) {
            result.add(new String(chars));
        }

        return result;
    }
}
