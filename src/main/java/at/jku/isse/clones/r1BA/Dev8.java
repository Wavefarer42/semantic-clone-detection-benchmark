package at.jku.isse.clones.r1BA;

import java.util.Locale;

/**
 * @author acheng3751
 */
public class Dev8 {
    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        double distance = _destination;
        double numHorses = _positionCount;

        double slowest_time = 0;
        for (int i = 0; i < numHorses; i++) {
            double location = _initialPosition[i];
            double speed = _speed[i];
            double arrival_time = (distance - location) / speed;
            slowest_time = (arrival_time > slowest_time) ? arrival_time : slowest_time;
        }

        double annie_speed = distance / slowest_time;
        System.out.println(annie_speed);

        return (float) annie_speed;
    }
}
