package at.jku.isse.clones.r1CA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author hyphensarethebest
 */
public class Dev7 {

    public static double run(int _n, int _k, int[] _r, int[] _h) {
        int n = _n;
        int k = _k;
        ArrayList<Long> product = new ArrayList<Long>();
        HashMap<Long, ArrayList<Long>> productToRadius = new HashMap<Long, ArrayList<Long>>();
        for (int i = 0; i < n; i++) {
//            line = in.nextLine();
//            sc = new Scanner(line);
            long r = _r[i];
            long h = _h[i];
            product.add(r * h);
            if (productToRadius.containsKey(r * h)) {
                productToRadius.get(r * h).add(r);
            } else {
                ArrayList<Long> temp = new ArrayList<Long>();
                temp.add(r);
                productToRadius.put(r * h, temp);
            }

        }
        for (long key : productToRadius.keySet()) {
            Collections.sort(productToRadius.get(key));
        }
        Collections.sort(product);
        long[] radius = new long[n];
        for (int i = 0; i < n; i++) {
            ArrayList<Long> temp = productToRadius.get(product.get(i));
            long curr = temp.get(0);
            temp.remove(0);
            radius[i] = curr;
        }
        long maxRadius = 0;
        long total = 0;
        for (int i = 0; i < k - 1; i++) {
            total += (2L * product.get(n - 1 - i));
            if (radius[n - 1 - i] > maxRadius) {
                maxRadius = radius[n - 1 - i];
            }
        }
        long biggestOtherTerm = 0;
        for (int i = k - 1; i < n; i++) {
            long otherTerm = (2L * product.get(n - 1 - i)) + Math.max(maxRadius, radius[n - 1 - i]) * Math.max(maxRadius, radius[n - 1 - i]);
            biggestOtherTerm = Math.max(biggestOtherTerm, otherTerm);
        }
        return Math.PI * (total + biggestOtherTerm);
    }
}
