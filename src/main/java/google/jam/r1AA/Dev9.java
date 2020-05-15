package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abear888
 */
public class Dev9 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {

        char[][] grid = new char[_r][_c];
        boolean[] empty = new boolean[_r]; // whether row is empty
        int[] firstChar = new int[_r]; // position of first char
        for (int i = 0; i < _r; i++) {
            empty[i] = true;
            firstChar[i] = -1;
        }
        int nonEmpty = 1000000; //first non-empty row
        for (int i = 0; i < _r; i++) {
            String line = _patterns.get(i);
            for (int j = 0; j < _c; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] != '?') {
                    empty[i] = false;
                    if (firstChar[i] == -1) {
                        firstChar[i] = j;
                    }
                    if (nonEmpty == 1000000) {
                        nonEmpty = i;
                    }
                }
            }
        }
        for (int i = nonEmpty; i < _r; i++) {
            if (empty[i]) {
                for (int j = 0; j < _c; j++) {
                    grid[i][j] = grid[i - 1][j];
                }
            } else {
                for (int j = 0; j < firstChar[i]; j++) {
                    grid[i][j] = grid[i][firstChar[i]];
                }
                char curChar = grid[i][firstChar[i]];
                for (int j = firstChar[i]; j < _c; j++) {
                    if (grid[i][j] == '?') {
                        grid[i][j] = curChar;
                    } else {
                        curChar = grid[i][j];
                    }
                }
            }
        }
        for (int i = 0; i < nonEmpty; i++) {
            for (int j = 0; j < _c; j++) {
                grid[i][j] = grid[nonEmpty][j];
            }
        }


        List<String> result = new ArrayList<>();
        for (int i = 0; i < _r; i++) {
            result.add(new String(grid[i]));
        }

        return result;
    }
}
