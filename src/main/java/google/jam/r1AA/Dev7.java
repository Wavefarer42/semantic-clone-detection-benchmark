package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author a2stnk
 */
public class Dev7 {
    public static List<String> run(int _r, int _c, List<String> _patterns) {
        char[][] cake = new char[_r][];
        for (int i = 0; i < _r; i++) {
            cake[i] = _patterns.get(i).toCharArray();
        }

        int sy = -1;
        for (int i = 0; i < _r; i++) {
            int sx = -1;
            for (int j = 0; j < _c; j++) {
                if (cake[i][j] == '?' && sx >= 0) {
                    cake[i][j] = cake[i][j - 1];
                } else if (cake[i][j] != '?' && sx < 0) {
                    sx = j;
                }
            }
            if (sx >= 0) {
                if (sy < 0)
                    sy = i;
                for (int j = 0; j < sx; j++)
                    cake[i][j] = cake[i][sx];
            } else if (i > 0) {
                cake[i] = cake[i - 1].clone();
            }
        }
        for (int i = 0; i < sy; i++)
            cake[i] = cake[sy].clone();


        List<String> result = new ArrayList<>();
        for (int i = 0; i < _r; i++)
            result.add(String.valueOf(cake[i]));

        return result;
    }
}
