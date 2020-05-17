package at.jku.isse.clones.r1AB;

import java.util.Collections;
import java.util.LinkedList;

public class Dev6 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {
        int n = _ingredientsCount;
        int p = _packagesCount;
        int[] required = _ingredientQuantities;
        LinkedList<Integer>[] available = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            available[i] = new LinkedList<Integer>();
            for (int it : _packages[i]) {
                available[i].add(it);
            }
            Collections.sort(available[i]);
        }
        long units = 1;
        int res = 0;
        long max = (long) 1e6 + 1000;
        outer:
        while (!available[0].isEmpty()) {
            long top = available[0].remove();
            loop:
            while (units <= max) {
                if (90 * units * required[0] > top * 100) {
                    continue outer;
                }
                if (top * 100 > 110 * units * required[0]) {
                    units++;
                    continue loop;
                }
                int[] toBeRemoved = new int[available.length];
                boolean fail = false;
                for (int i = 1; i < available.length; i++) {
                    boolean can = false;
                    for (int j = 0; j < available[i].size(); j++) {
                        long first = available[i].get(j);
                        if (90 * units * required[i] <= first * 100
                                && first * 100 <= 110 * units * required[i]) {
                            can = true;
                            toBeRemoved[i] = available[i].get(j);
                            break;
                        }
                    }
                    if (!can) {
                        fail = true;
                    }
                }
                if (fail) {
                    units++;
                } else {
                    res++;
                    for (int i = 1; i < toBeRemoved.length; i++) {
                        available[i].remove((Integer) toBeRemoved[i]);
                    }
                    break loop;
                }
            }
        }
        return res;
    }
}
