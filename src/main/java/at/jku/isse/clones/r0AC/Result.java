package at.jku.isse.clones.r0AC;

import java.util.Objects;

public class Result {
    public int min;
    public int max;

    public Result(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return min == result.min &&
                max == result.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}
