package at.jku.isse.clones.r1AB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author Aemon
 */
public class Dev5 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {
        int N = _ingredientsCount;
        int K = _packagesCount;

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();

        int[] cost = _ingredientQuantities;

        int[][] ing = _packages;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                for (int k = 0; k <= 1000000; k++) {
                    double percent = (double) ing[i][j] / (double) (k * cost[i]);
                    if (percent < .9) break;
                    if (percent <= 1.1 && percent >= .9) {
                        if (!map.containsKey(k)) {
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(i * K + j);
                            map.put(k, temp);
                        } else {
                            ArrayList<Integer> temp = map.get(k);
                            temp.add(i * K + j);
                        }
                    }
                }
            }
        }
        boolean[] used = new boolean[N * K];
        int sol = 0;
        for (int mult : map.keySet()) {

            boolean prev = true;
            while (prev == true) {
                ArrayList<Integer> list = map.get(mult);
                //System.out.println(list);
                int[] seen = new int[N];
                Arrays.fill(seen, -1);

                for (int i : list) {
                    if (used[i]) continue;
                    if (seen[i / K] == -1) seen[i / K] = i;
                    else if (ing[seen[i / K] / K][seen[i / K] % K] > ing[i / K][i % K]) {
                        seen[i / K] = i;
                    }
                }

                boolean valid = true;
                for (int i : seen) if (i == -1) valid = false;
                if (valid) {
                    sol++;
                    for (int i : seen) {
                        used[i] = true;
                    }
                } else {
                    prev = false;
                }
            }
        }

        return sol;
    }
}
