package at.jku.isse.clones.r1CC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author
 */
public class Dev9 {
    public static double run(int _n, int _k, double _u, double[] _ps) {
        int M = 1000000;
        int n = _n, k = _k;
        int u = (int)(_u * M);
        int[] p = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; ++i) {
            p[i] = (int)(_ps[i] * M);
            pq.add(p[i]);
        }
        for (int i = 0; i < u; ++i) {
            pq.add(pq.poll() + 1);
        }
        double ans = 1;
        for (int a : pq) {
            ans *= (a * 1.0 / M);
        }
        return ans;
    }
}
