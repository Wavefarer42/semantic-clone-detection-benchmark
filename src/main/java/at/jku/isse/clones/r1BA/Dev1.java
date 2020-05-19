package at.jku.isse.clones.r1BA;

import java.util.Arrays;

/**
 * @author 1ed
 */
public class Dev1 {
    static class Horse {
        int start, speed;

        Horse(int start, int speed) {
            this.start = start;
            this.speed = speed;
        }

        static int byDist(Horse h1, Horse h2) {
            return h2.start - h1.start;
        }

        public String toString() {
            return start + " " + speed;
        }
    }

    public static float run(int _destination, int _positionCount, int[] _initialPosition, int[] _speed) {

        int D = _destination, N = _positionCount;

        Horse[] h = new Horse[N];
        for (int i = 0; i < N; i++) {
            h[i] = new Horse(_initialPosition[i], _speed[i]);
        }

        Arrays.sort(h, Horse::byDist);
        double time = -1.0;
        for (int i = 0; i < h.length; ++i) {
            double newT = 1.0 * (D - h[i].start) / h[i].speed;
            if (newT > time) time = newT;
        }
        return (float) (D / time);
    }
}
