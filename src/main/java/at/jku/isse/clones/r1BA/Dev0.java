package at.jku.isse.clones.r1BA;

/**
 * @author 0Be
 */
public class Dev0 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {
        int d = _destination;
        int n = _positionCount;

        double max = 0;

        for (int j = 0; j < n; j++) {
            int dj = d - _initialPosition[j];
            int sj = _speed[j];
            double tj = (1.0 * dj / sj);
            if (tj > max)
                max = tj;
        }

        double caseans = 1.0 * d / max;

        return (float) caseans;
    }
}
