package at.jku.isse.clones.r1AA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author adovzhenko
 */
public class Dev4 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {
        char[][] cake = new char[_r][];
        for (int i = 0; i < _patterns.size(); i++) {
            cake[i] = _patterns.get(i).toCharArray();
        }

        setInitials(cake, _r, _c);

        List<String> result = new ArrayList<>();
        for (char[] chars : cake) {
            result.add(new String(chars));
        }
        return result;
    }

    protected static void setInitials(char[][] cake, int R, int C) {
        Map<Integer, List<Character>> columnMultipleOccurrences = new HashMap<>();
        Map<Integer, List<Character>> rowMultipleOccurrences = new HashMap<>();
        for (int i = 0; i < R; i++) {
            rowMultipleOccurrences.put(i, new ArrayList<>());
            int[] occurrences = new int[28];
            for (int j = 0; j < C; j++) {
                if (cake[i][j] != '?') {
                    occurrences[cake[i][j] - 'A']++;
                }
            }
            for (int k = 0; k < 28; k++) {
                if (occurrences[k] > 1) {
                    rowMultipleOccurrences.get(i).add((char) (k + 'A'));
                }
            }
        }
        for (int j = 0; j < C; j++) {
            columnMultipleOccurrences.put(j, new ArrayList<>());
            int[] occurrences = new int[28];
            for (int i = 0; i < R; i++) {
                if (cake[i][j] != '?') {
                    occurrences[cake[i][j] - 'A']++;
                }
            }
            for (int k = 0; k < 28; k++) {
                if (occurrences[k] > 1) {
                    columnMultipleOccurrences.get(j).add((char) (k + 'A'));
                }
            }
        }

        for (int i = 0; i < R; i++) {
            char lastLetter = '?';
            int lastCol = -1;
            for (int j = 0; j < C; j++) {
                if (cake[i][j] != '?') {
                    lastLetter = cake[i][j];
                    lastCol = j;
                } else if (lastCol != -1 && !columnMultipleOccurrences.get(lastCol).contains(lastLetter)) {
                    cake[i][j] = lastLetter;
                }
            }
            for (int j = C - 1; j >= 0; j--) {
                if (cake[i][j] != '?') {
                    lastLetter = cake[i][j];
                    lastCol = j;
                } else if (lastCol != -1 && !columnMultipleOccurrences.get(lastCol).contains(lastLetter)) {
                    cake[i][j] = lastLetter;
                }
            }
        }

        for (int j = 0; j < C; j++) {
            char lastLetter = '?';
            int lastRow = -1;
            for (int i = 0; i < R; i++) {
                if (cake[i][j] != '?') {
                    lastLetter = cake[i][j];
                    lastRow = i;
                } else if (lastRow != -1 && !rowMultipleOccurrences.get(lastRow).contains(lastLetter)) {
                    cake[i][j] = lastLetter;
                }
            }
            for (int i = R - 1; i >= 0; i--) {
                if (cake[i][j] != '?') {
                    lastLetter = cake[i][j];
                    lastRow = i;
                } else if (lastRow != -1 && !rowMultipleOccurrences.get(lastRow).contains(lastLetter)) {
                    cake[i][j] = lastLetter;
                }
            }

        }

    }
}
