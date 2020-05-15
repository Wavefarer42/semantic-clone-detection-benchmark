package at.jku.isse.clones;

import at.jku.isse.gradient.annotations.GradientModel;

@GradientModel
public class Fibonacci {

    public static long iterative(int n) {
        assert n >= 0;

        int previousPrevious, previous = 0, current = 1;

        for (int i = 1; i < n; i++) {
            previousPrevious = previous;
            previous = current;
            current = previous + previousPrevious;
        }

        return current;
    }

    public static long recursive(int n) {
        assert n >= 0;

        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return recursive(n - 1) + recursive(n - 2);
    }
}
