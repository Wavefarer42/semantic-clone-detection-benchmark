package at.jku.isse.clones.r1BA;

/**
 * @author 8163264128
 */
public class Dev5 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        final int D = _destination;
        final int N = _positionCount;
        double maxTime = 0;
        for (int j = 0; j < N; j++) {
            final int K = _initialPosition[j];
            final int S = _speed[j];
            double t = (double) (D - K) / S;
            maxTime = Math.max(maxTime, t);
        }

        return (float) (D / maxTime);
    }
}
