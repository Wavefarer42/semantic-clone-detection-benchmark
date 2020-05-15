package google.jam.r1AB;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author abear8888
 */
public class Dev0 {
    public static int MAX_SERVINGS = 1000000;

    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {
        int n = _ingredientsCount;
        int p = _packagesCount;
        int[] serving = _ingredientQuantities;
        ArrayList<ArrayList<Integer>> packages = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> cur = new ArrayList<Integer>();
            for (int j = 0; j < p; j++) {
                cur.add(_packages[i][j]);
            }
            Collections.sort(cur);
            packages.add(cur);
        }
        int ans = 0;
        int numServings = 1;
        int[] index = new int[n]; //index of ingredient
        boolean done = false;
        while (numServings <= MAX_SERVINGS) {
            boolean goodPackage = true;
            for (int j = 0; j < n; j++) {//ingredient
                if (index[j] == p) { //done
                    done = true;
                    break;
                }
                int cur = packages.get(j).get(index[j]);
                int target = serving[j] * numServings;
                if (cur < 0.9 * target) {
                    index[j]++;
                    goodPackage = false;
                    break;
                }
                if (cur > 1.1 * target) {
                    numServings++;
                    goodPackage = false;
                    break;
                }
            }
            if (done) {
                break;
            }
            if (goodPackage) {
                for (int i = 0; i < n; i++) {
                    index[i]++;
                }
                ans++;
            }
        }

        return ans;
    }
}
