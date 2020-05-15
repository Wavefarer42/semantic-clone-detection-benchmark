package at.jku.isse.clones;

import at.jku.isse.gradient.annotations.GradientModel;

@GradientModel
public class Factorial {

    public static long iterative(int n) {
        assert n >= 0;

        long product = 1;

        for (int i = 1; i <= n; i++) {
            product *= i;
        }
        return product;
    }

    public static long recursive(int n) {
        assert n >= 0;

        if (n <= 1) {
            return 1;
        }

        return recursive(n - 1) * n;
    }
}
