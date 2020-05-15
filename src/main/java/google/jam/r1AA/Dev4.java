package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ASotelo
 */
public class Dev4 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {
        int R = _r;
        int C = _c;
        char[][] cake = new char[R][C];
        for (int i = 0; i < R; i++) {
            cake[i] = _patterns.get(i).toCharArray();
        }
        solveSlow(R, C, cake);
        List<String> result = new ArrayList<>();
        for (char[] row : cake) {
            result.add(String.valueOf(row));
        }
        return result;
    }

    static void solveSlow(int R, int C, char[][] cake) {

        char[] letters;
        int[] rowsPerLetter;
        int[] colsPerLetter;

        String strLetters = "";
        Map<Character, int[]> mapPositions = new TreeMap<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (cake[r][c] != '?') {
                    mapPositions.put(cake[r][c], new int[]{r, c});
                    strLetters += cake[r][c];
                }
            }
        }
        letters = strLetters.toCharArray();
        rowsPerLetter = new int[letters.length];
        colsPerLetter = new int[letters.length];
        for (int z = 0; z < letters.length; z++) {
            int position[] = mapPositions.get(letters[z]);
            rowsPerLetter[z] = position[0];
            colsPerLetter[z] = position[1];
        }
        test(0, letters, R, C, cake, rowsPerLetter, colsPerLetter);
    }

    static boolean test(int k, char[] letters, int R, int C, char[][] cake, int[] rowsPerLetter, int[] colsPerLetter) {
        if (k == letters.length) {
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (cake[r][c] == '?') return false;
                }
            }
            return true;
        }
        int rc = rowsPerLetter[k], cc = colsPerLetter[k];
        if (cake[rc][cc] != letters[k]) throw new IllegalStateException();
        for (int r1 = 0; r1 <= rc; r1++) {
            for (int c1 = 0; c1 <= cc; c1++) {
                for (int r2 = rc; r2 < R; r2++) {
                    loop:
                    for (int c2 = cc; c2 < C; c2++) {
                        for (int r = r1; r <= r2; r++) {
                            for (int c = c1; c <= c2; c++) {
                                if (cake[r][c] != '?' && cake[r][c] != cake[rc][cc]) continue loop;
                            }
                        }
                        for (int r = r1; r <= r2; r++) {
                            for (int c = c1; c <= c2; c++) {
                                cake[r][c] = letters[k];
                            }
                        }
                        if (test(k + 1, letters, R, C, cake, rowsPerLetter, colsPerLetter)) return true;
                        for (int r = r1; r <= r2; r++) {
                            for (int c = c1; c <= c2; c++) {
                                cake[r][c] = '?';
                            }
                        }
                        cake[rc][cc] = letters[k];
                    }
                }
            }
        }
        return false;
    }
}
