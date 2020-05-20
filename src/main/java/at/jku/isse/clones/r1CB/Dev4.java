package at.jku.isse.clones.r1CB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author jeffreyxiao
 */
public class Dev4 {
    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {
        int C = _ac;
        int J = _aj;
        int leftC = 720;
        int leftJ = 720;

        Activity[] A = new Activity[C + J];

        for (int i = 0; i < C; i++) {
            int l = _c[i];
            int r = _d[i];
            leftC -= r - l;
            A[i] = new Activity(l, r, 0);
        }
        for (int i = 0; i < J; i++) {
            int l = _j[i];
            int r = _k[i];
            leftJ -= r - l;
            A[i + C] = new Activity(l, r, 1);
        }
        Arrays.sort(A);
        ArrayList<Integer> diffC = new ArrayList<Integer>();
        ArrayList<Integer> diffJ = new ArrayList<Integer>();
        int exchanges = 0;
        for (int i = 0; i < C + J; i++) {
            int j = (i + 1) % (C + J);
            if (A[i].type != A[j].type) {
                exchanges++;
                continue;
            }
            exchanges += 2;
            if (i < C + J - 1) {
                if (A[i].type == 0)
                    diffC.add(A[j].start - A[i].end);
                else
                    diffJ.add(A[j].start - A[i].end);
            } else {
                if (A[i].type == 0)
                    diffC.add(A[j].start - (A[i].end - 720 * 2));
                else
                    diffJ.add(A[j].start - (A[i].end - 720 * 2));
            }
        }
        Collections.sort(diffC);
        Collections.sort(diffJ);
        for (int i = 0; i < diffC.size(); i++) {
            if (diffC.get(i) <= leftC) {
                exchanges -= 2;
                leftC -= diffC.get(i);
            }
        }
        for (int i = 0; i < diffJ.size(); i++) {
            if (diffJ.get(i) <= leftJ) {
                exchanges -= 2;
                leftJ -= diffJ.get(i);
            }
        }
        return exchanges;
    }

    static class Activity implements Comparable<Activity> {
        int start, end, type;

        Activity(int start, int end, int type) {
            this.start = start;
            this.end = end;
            this.type = type;
        }

        @Override
        public int compareTo(Activity a) {
            return start - a.start;
        }
    }

}
