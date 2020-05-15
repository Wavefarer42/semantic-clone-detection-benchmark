package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 0phi
 */
public class Dev5 {

    public static List<String> run(int _r, int _c, List<String> _patterns) {

        int rows = _r;

        int columns = _c;

        char[][] cakeDesign = new char[_r][];
        for (int i = 0; i < cakeDesign.length; i++) {
            cakeDesign[i] = _patterns.get(i).toCharArray();
        }

        List<Character> markedChars = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int col = 0; col < columns; col++) {

                if (cakeDesign[r][col] != '?' && !markedChars.contains(cakeDesign[r][col])) {
                    markedChars.add(cakeDesign[r][col]);
                    int min = col;
                    int max = col;

                    for (int x = col + 1; x < columns; x++) {
                        if (cakeDesign[r][x] == '?') {
                            cakeDesign[r][x] = cakeDesign[r][col];
                            max = x;
                        } else {
                            break;
                        }
                    }

                    for (int y = col - 1; y > -1; y--) {
                        if (cakeDesign[r][y] == '?') {
                            cakeDesign[r][y] = cakeDesign[r][col];
                            min = y;
                        } else {
                            break;
                        }
                    }

                    for (int beforeRow = r - 1; beforeRow > -1; beforeRow--) {
                        boolean isAllUnfilled = true;
                        for (int c = min; c <= max; c++) {
                            if (cakeDesign[beforeRow][c] != '?') {
                                isAllUnfilled = false;
                                break;
                            }
                        }

                        if (isAllUnfilled) {
                            for (int c = min; c <= max; c++) {
                                cakeDesign[beforeRow][c] = cakeDesign[r][col];
                            }
                        } else {
                            break;
                        }
                    }

                    for (int afterRow = r + 1; afterRow < rows; afterRow++) {
                        boolean isAllUnfilled = true;
                        for (int c = min; c <= max; c++) {
                            if (cakeDesign[afterRow][c] != '?') {
                                isAllUnfilled = false;
                                break;
                            }
                        }

                        if (isAllUnfilled) {
                            for (int c = min; c <= max; c++) {
                                cakeDesign[afterRow][c] = cakeDesign[r][col];
                            }
                        } else {
                            break;
                        }
                    }

                }
            }
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < cakeDesign.length; i++) {
            result.add(new String(cakeDesign[i]));
        }

        return result;
    }
}
