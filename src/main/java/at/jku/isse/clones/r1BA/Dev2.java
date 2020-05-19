package at.jku.isse.clones.r1BA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 4castle
 */
public class Dev2 {
    private static class Horse {
        public final double distance;
        public final double speed;

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 53 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
            hash = 53 * hash + (int) (Double.doubleToLongBits(this.speed) ^ (Double.doubleToLongBits(this.speed) >>> 32));
            return hash;
        }

        public Horse(double distance, double speed) {
            this.distance = distance;
            this.speed = speed;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Horse other = (Horse) obj;
            if (Double.doubleToLongBits(this.distance) != Double.doubleToLongBits(other.distance)) {
                return false;
            }
            if (Double.doubleToLongBits(this.speed) != Double.doubleToLongBits(other.speed)) {
                return false;
            }
            return true;
        }

        public double getDistance() {
            return distance;
        }

        public double getSpeed() {
            return speed;
        }
    }

    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        int D = _destination;
        int N = _positionCount;
        List<Horse> horses = new ArrayList<>();
        for (int ni = 0; ni < N; ni++) {
            horses.add(new Horse(_initialPosition[ni], _speed[ni]));
        }
        horses.sort(Comparator.comparingDouble(Horse::getDistance));
        horses = horses.subList(0, indexOfSlowHorse(horses) + 1);
        double maxSpeed = D / timeOfFirstHorse(horses, D);

        return (float) maxSpeed;
    }

    private static int indexOfSlowHorse(List<Horse> horses) {
        Horse slow = horses.get(0);
        int slowIndex = 0;
        for (int i = 1; i < horses.size(); i++) {
            Horse h = horses.get(i);
            if (h.speed < slow.speed) {
                slow = h;
                slowIndex = i;
            } else {
                break;
            }
        }
        return slowIndex;
    }

    private static double timeOfFirstHorse(List<Horse> horses, int totalDistance) {
        Horse first = horses.get(0);
        double timeFirstToFinish = (totalDistance - first.distance) / first.speed;
        if (horses.size() == 1) {
            return timeFirstToFinish;
        } else {
            Horse second = horses.get(1);
            double speedDiff = first.speed - second.speed;
            double distanceBetween = second.distance - first.distance;
            double timeToCatchUp = distanceBetween / speedDiff;
            double timeSecondToFinish = timeOfFirstHorse(horses.subList(1, horses.size()), totalDistance);
            if (timeToCatchUp > timeSecondToFinish) {
                return timeFirstToFinish;
            } else {
                double secondDistanceLeft = totalDistance - (timeToCatchUp * second.speed) - second.distance;
                double secondTimeLeft = secondDistanceLeft / second.speed;
                return timeToCatchUp + secondTimeLeft;
            }
        }
    }
}
