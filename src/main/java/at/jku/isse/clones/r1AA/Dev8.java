package at.jku.isse.clones.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Akaiyo
 */
public class Dev8 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {
        int R = _r;
        int C = _c;


        char[][] field = new char[R][C];

        for (int r = 0; r < R; r++) {

            field[r] = _patterns.get(r).toCharArray();

        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (field[r][c] != '?') {
                    expand(field, r, c);
                }
            }
        }

        expand(field);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < _r; i++)
            result.add(String.valueOf(field[i]));

        return result;
}


    private static void expand(char[][] field, int r, int c) {
        char kid = field[r][c];


        for (int i = c + 1; i < field[r].length && field[r][i] == '?'; i++) {
            field[r][i] = kid;
        }

        for (int i = c - 1; i >= 0 && field[r][i] == '?'; i--) {
            field[r][i] = kid;
        }


    }

    private static void expand(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            if (field[i][0] == '?') {
                if (i > 0) {
                    field[i] = field[i - 1];
                }
            }
        }
        for (int i = field.length - 1; i >= 0; i--) {
            if (field[i][0] == '?') {
                field[i] = field[i + 1];
            }
        }
    }

}
