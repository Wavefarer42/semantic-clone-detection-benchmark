package at.jku.isse.clones.r1BA;

/**
 * @author 87f34933
 */
public class Dev4 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {
        int d = _destination;
        int n = _positionCount;

        double annie = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            double horseToD = d - _initialPosition[i];
            double horseAtD = horseToD / _speed[i];

            double annieNew = d / horseAtD;
            annie = Double.min(annie, annieNew);
        }

        return (float) annie;
    }
}
