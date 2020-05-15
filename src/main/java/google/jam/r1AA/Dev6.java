package google.jam.r1AA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author 4castle
 */
public class Dev6 {
    public static List<String> run(int _r, int _c, List<String> _pattern) {
        char[][] cake = new char[_r][];
        for (int ri = 0; ri < _r; ri++) {
            cake[ri] = _pattern.get(ri).toCharArray();
        }
        cake = fillCake(cake);
        List<String> result = new ArrayList<>();
        for (int ri = 0; ri < _r; ri++) {
            result.add(new String(cake[ri]));
        }
        return result;
    }

    private static char[][] fillCake(char[][] cake) {
        final int rows = cake.length;
        final int cols = cake[0].length;
        List<Rect> rects = toRects(cake);

        while (true) {
            for (Rect r : rects) {
                while (canGoUp(r, rects, rows, cols)) {
                    r.rowFirst--;
                }
                while (canGoDown(r, rects, rows, cols)) {
                    r.rowLast++;
                }
                while (canGoLeft(r, rects, rows, cols)) {
                    r.colFirst--;
                }
                while (canGoRight(r, rects, rows, cols)) {
                    r.colLast++;
                }
            }
            if (isFilled(rects, rows, cols)) {
                break;
            }
            rects = toRects(cake);
            Collections.shuffle(rects);
        }

        return fromRects(rects, rows, cols);
    }

    private static boolean isFilled(List<Rect> rects, int rows, int cols) {
        char[][] cake = fromRects(rects, rows, cols);
        for (char[] row : cake) {
            for (char c : row) {
                if (c == '\0') return false;
            }
        }
        return true;
    }

    private static boolean canGoUp(Rect rect, List<Rect> rects, int rows, int cols) {
        if (rect.rowFirst == 0) {
            return false;
        }
        for (Rect r : rects) {
            if (r == rect) {
                continue;
            }
            if (r.colLast < rect.colFirst || r.colFirst > rect.colLast) {
                continue;
            }
            if (r.rowLast == rect.rowFirst - 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean canGoDown(Rect rect, List<Rect> rects, int rows, int cols) {
        if (rect.rowLast == rows - 1) {
            return false;
        }
        for (Rect r : rects) {
            if (r == rect) {
                continue;
            }
            if (r.colLast < rect.colFirst || r.colFirst > rect.colLast) {
                continue;
            }
            if (r.rowFirst == rect.rowLast + 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean canGoLeft(Rect rect, List<Rect> rects, int rows, int cols) {
        if (rect.colFirst == 0) {
            return false;
        }
        for (Rect r : rects) {
            if (r == rect) {
                continue;
            }
            if (r.rowLast < rect.rowFirst || r.rowFirst > rect.rowLast) {
                continue;
            }
            if (r.colLast == rect.colFirst - 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean canGoRight(Rect rect, List<Rect> rects, int rows, int cols) {
        if (rect.colLast == cols - 1) {
            return false;
        }
        for (Rect r : rects) {
            if (r == rect) {
                continue;
            }
            if (r.rowLast < rect.rowFirst || r.rowFirst > rect.rowLast) {
                continue;
            }
            if (r.colFirst == rect.colLast + 1) {
                return false;
            }
        }
        return true;
    }

    private static List<Rect> toRects(char[][] cake) {
        List<Rect> slices = new ArrayList<>();
        for (int i = 0; i < cake.length; i++) {
            for (int j = 0; j < cake[0].length; j++) {
                char letter = cake[i][j];
                if (letter == '?') {
                    continue;
                }
                Rect r = new Rect();
                r.rowFirst = i;
                r.rowLast = i;
                r.colFirst = j;
                r.colLast = j;
                r.letter = letter;
                slices.add(r);
            }
        }
        return slices;
    }

    private static char[][] fromRects(List<Rect> rects, int rows, int cols) {
        char[][] cake = new char[rows][];
        Arrays.setAll(cake, i -> new char[cols]);
        for (Rect r : rects) {
            for (int i = r.rowFirst; i <= r.rowLast; i++) {
                for (int j = r.colFirst; j <= r.colLast; j++) {
                    cake[i][j] = r.letter;
                }
            }
        }
        return cake;
    }

    private static class Rect {

        public int rowFirst;
        public int colFirst;
        public char letter;
        public int rowLast;
        public int colLast;

        public Rect copy() {
            Rect r = new Rect();
            r.rowFirst = this.rowFirst;
            r.rowLast = this.rowLast;
            r.colFirst = this.colFirst;
            r.colLast = this.colLast;
            r.letter = this.letter;
            return r;
        }
    }
}
