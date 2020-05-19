package at.jku.isse.clones.r1BA;

/**
 * @author A.R.K
 */
public class Dev6 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        int D = _destination;
        int N = _positionCount;
        double maxTime = 0;
        for (int j = 0; j < N; j++) {
            int K = _initialPosition[j];
            int S = _speed[j];
            double time = (D - K) / (double) S;
            if ((D != K) && maxTime < time)
                maxTime = time;
        }
        double speed = D / maxTime;
        return (float) speed;
    }
}
