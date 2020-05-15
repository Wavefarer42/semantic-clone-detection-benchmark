package at.jku.isse.math;

/**
 * factorial recursive
 */
public class Dev1 {
    public static long run(int _n) {
        assert _n >= 0;

        if (_n <= 1) {
            return 1;
        }

        return run(_n - 1) * _n;
    }
}
