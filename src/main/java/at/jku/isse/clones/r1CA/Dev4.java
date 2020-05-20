package at.jku.isse.clones.r1CA;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Dart_am
 */
public class Dev4 {

    public static double run(int _n, int _k, int[] _r, int[] _h){
        int n = _n;
        int k = _k;
        int[] r = _r;
        int[] h = _h;


        Pancake[] p = new Pancake[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Pancake(i, r[i], h[i]);
        }
        Arrays.sort(p, new Comparator<Pancake>() {
            @Override
            public int compare(Pancake o1, Pancake o2) {
                if (o1.r < o2.r) {
                    return -1;
                }
                if (o1.r > o2.r) {
                    return 1;
                }
                return 0;
            }
        });
        TreeSet<Pancake> a = new TreeSet<>(new Comparator<Pancake>() {
            @Override
            public int compare(Pancake o1, Pancake o2) {
                if (o1.sideSquare < o2.sideSquare) {
                    return -1;
                }
                if (o1.sideSquare > o2.sideSquare) {
                    return 1;
                }
                if (o1.id < o2.id) {
                    return -1;
                }
                if (o1.id > o2.id) {
                    return 1;
                }
                return 0;
            }
        });
        double answer = 0;
        double sideSum = 0;
        for (int i = 0; i < n; i++) {
            a.add(p[i]);
            sideSum += p[i].sideSquare;
            if (a.size() == k) {
                int ansr = p[i].r;
                double s = Math.PI * ansr * ansr + sideSum;
                answer = Math.max(answer, s);

                Pancake last = a.pollFirst();
                sideSum -= last.sideSquare;
            }
        }
        return answer;
    }

    private static class Pancake {
        public int id;
        public int r;
        public double sideSquare;
        public Pancake(int id, int r, int h) {
            this.id = id;
            this.r = r;
            this.sideSquare = 2 * Math.PI * r * h;
        }
    }

}
