package at.jku.isse.clones.math;

/**
 * factorial iterative
 */
public class Dev0 {
    public static long run(int _n) {
        assert _n >= 0;

        long product = 1;

        for (int i = 1; i <= _n; i++) {
            product *= i;
        }
        return product;
    }
}
