package google.jam.r1AA;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AhmadMamdouh
 */
public class Dev6 {
    public static List<String> run(int _r, int _c, List<String> _pattern) {
        int n = _r;
        int m = _c;
        char[][] arr = new char[n][m];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = _pattern.get(i).toCharArray();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != '?') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (arr[i][k] == '?') {
                            arr[i][k] = arr[i][j];
                        } else {
                            break;
                        }
                    }
                    for (int k = j + 1; k < m; k++) {
                        if (arr[i][k] == '?') {
                            arr[i][k] = arr[i][j];
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            boolean containsChar = false;
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != '?') {
                    containsChar = true;
                }
            }
            if (containsChar) {
                for (int k = i - 1; k >= 0; k--) {
                    containsChar = false;
                    for (int j = 0; j < arr[i].length; j++) {
                        if (arr[k][j] != '?') {
                            containsChar = true;
                        }
                    }
                    if (containsChar) {
                        break;
                    } else {
                        for (int j = 0; j < arr[i].length; j++) {
                            arr[k][j] = arr[i][j];
                        }
                    }
                }
                for (int k = i + 1; k < n; k++) {
                    containsChar = false;
                    for (int j = 0; j < arr[i].length; j++) {
                        if (arr[k][j] != '?') {
                            containsChar = true;
                        }
                    }
                    if (containsChar) {
                        break;
                    } else {
                        for (int j = 0; j < arr[i].length; j++) {
                            arr[k][j] = arr[i][j];
                        }
                    }
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (char[] chars : arr) {
            result.add(new String(chars));
        }

        return result;
    }
}
