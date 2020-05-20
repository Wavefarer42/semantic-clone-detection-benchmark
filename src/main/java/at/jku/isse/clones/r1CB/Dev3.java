package at.jku.isse.clones.r1CB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author Dekacc
 */
public class Dev3 {
    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {
        int Ac = _ac;
        int Aj = _aj;

        int[] arr = new int[1440];
        Arrays.fill(arr, 0);

        int t1 = 0;
        int t2 = 0;

        for (int i = 0; i < Ac; i++) {
            int s = _c[i];
            int e = _d[i];
            for (int j = s; j < e; j++) {
                arr[j] = 1;
                t1++;
            }
        }

        for (int i = 0; i < Aj; i++) {
            int s = _j[i];
            int e = _k[i];
            for (int j = s; j < e; j++) {
                arr[j] = 2;
                t2++;
            }
        }

        ArrayList<Integer> aa0 = new ArrayList<Integer>();
        ArrayList<Integer> aa1 = new ArrayList<Integer>();
        ArrayList<Integer> aa2 = new ArrayList<Integer>();

        //			int first = 0;
        //			int last = 0;

        //find start
        int start = 0;
        while (arr[start] == 0) start++;

        int L = arr[start];
        int len = 0;

        int inversions = 0;

        for (int i = start; i < 1440; i++) {
            if (arr[i] == 0) {
                len++;
            } else {
                if (L != arr[i]) inversions++;
                if (len > 0) {
                    int R = arr[i];
                    if (L == R) {
                        if (L == 1) aa1.add(len);
                        else aa2.add(len);
                    } else {
                        aa0.add(len);
                    }
                }
                len = 0;
                L = arr[i];
            }
        }

        //first
        for (int i = 0; ; i++) {
            if (arr[i] == 0) {
                len++;
            } else {
                if (L != arr[i]) inversions++;
                if (len > 0) {
                    int R = arr[i];
                    if (L == R) {
                        if (L == 1) aa1.add(len);
                        else aa2.add(len);
                    } else {
                        aa0.add(len);
                    }
                }
                break;
            }
        }

        Collections.sort(aa0);
        Collections.sort(aa1);
        Collections.sort(aa2);

        while (!aa1.isEmpty()) {
            if (t1 + aa1.get(0) <= 720) {
                t1 += aa1.get(0);
                aa1.remove(0);
            } else {
                aa1.set(0, aa1.get(0) - (720 - t1));
                t1 = 720;
                break;
            }
        }

        while (!aa2.isEmpty()) {
            if (t2 + aa2.get(0) <= 720) {
                t2 += aa2.get(0);
                aa2.remove(0);
            } else {
                aa2.set(0, aa2.get(0) - (720 - t2));
                t2 = 720;
                break;
            }
        }

        inversions += 2 * aa1.size();
        inversions += 2 * aa2.size();

        int ans = inversions;

        return ans;
    }
}
