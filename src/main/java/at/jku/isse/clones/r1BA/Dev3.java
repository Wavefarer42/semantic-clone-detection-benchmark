package at.jku.isse.clones.r1BA;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author Acci
 */
public class Dev3 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        double distanceToTravel = _destination;
        int numberOfHorses = _positionCount;
        double slowestTimes = 0;
        for (int i = 0; i < numberOfHorses; ++i) {
            double horseDistance = _initialPosition[i];
            double horseSpeed = _speed[i];
            double difference = distanceToTravel - horseDistance;
            double timeToTravel = difference / horseSpeed;
            slowestTimes = (timeToTravel > slowestTimes) ? timeToTravel : slowestTimes;
        }
        double result = distanceToTravel / slowestTimes;
        NumberFormat formatter = new DecimalFormat("#0.000000");
        String res = formatter.format(result);
        return (float) result;
    }
}
