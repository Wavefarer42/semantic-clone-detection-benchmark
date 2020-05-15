package at.jku.isse.math;


/**
 * fibonacci recursive
 */
public class Dev3 {
    public static long run(int _n) {
        assert _n >= 0;

        if (_n == 0) {
            return 0;
        } else if (_n == 1) {
            return 1;
        }

        return run(_n - 1) + run(_n - 2);
    }
}
