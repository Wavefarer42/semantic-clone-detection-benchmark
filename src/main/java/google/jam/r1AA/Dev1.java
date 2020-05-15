package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexrcoleman
 */
public class Dev1 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {
        int R = _r, C = _c;
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = _patterns.get(i).toCharArray();
        }
        for (int i = 0; i < R; i++) {
            char cur = '?';
            for (int j = 0; j < C; j++) {
                if (grid[i][j] != '?')
                    cur = grid[i][j];
                grid[i][j] = cur;
            }
        }
        for (int i = 0; i < R; i++) {
            char cur = '?';
            for (int j = C - 1; j >= 0; j--) {
                if (grid[i][j] != '?')
                    cur = grid[i][j];
                grid[i][j] = cur;
            }
        }
        for (int j = 0; j < C; j++) {
            char cur = '?';
            for (int i = 0; i < R; i++) {
                if (grid[i][j] != '?')
                    cur = grid[i][j];
                grid[i][j] = cur;
            }
        }
        for (int j = 0; j < C; j++) {
            char cur = '?';
            for (int i = R - 1; i >= 0; i--) {
                if (grid[i][j] != '?')
                    cur = grid[i][j];
                grid[i][j] = cur;
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            result.add(String.valueOf(grid[i]));
        }

        return result;
    }

}
