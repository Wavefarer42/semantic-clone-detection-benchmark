package at.jku.isse.clones.r1BA;

import java.util.Arrays;

/**
 * @author aamihala
 */
public class Dev9 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        Integer d = _destination;
        Integer n = _positionCount;

        int[] initialPositions = _initialPosition;
        int[] maximumSpeeds = _speed;

        double result = result(initialPositions, maximumSpeeds, d);

        return (float) result;
    }

    private static double result(int[] initialPositions, int[] maximumSpeeds, int d) {
        // for each horse, compute time needed to reach the destination if they were alone

        double[] times = new double[initialPositions.length];
        for (int i = 0; i < initialPositions.length; i++) {
            times[i] = (d - initialPositions[i]) / (double) maximumSpeeds[i];
        }

        Arrays.sort(times);

        double slowestTime = times[times.length - 1];

        double maxSpeed = d / slowestTime;

        return maxSpeed;
    }

}
