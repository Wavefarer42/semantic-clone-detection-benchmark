package at.jku.isse.clones.math;

/**
 * fibonacci iterative
 */
public class Dev2 {
    public static long run(int _n) {
        assert _n >= 0;

        int previousPrevious, previous = 0, current = 1;

        for (int i = 1; i < _n; i++) {
            previousPrevious = previous;
            previous = current;
            current = previous + previousPrevious;
        }

        return current;
    }
}
