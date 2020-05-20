package at.jku.isse.clones.r1CB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author hyphensarethebest
 */
public class Dev8 {
    public static int run(int _ac, int _aj, int[] _c, int[] _d, int[] _j, int[] _k) {

        int ac = _ac;
        int aj = _aj;
        HashMap<Integer, Integer> endTimes = new HashMap<Integer, Integer>();
        HashSet<Integer> cStart = new HashSet<Integer>();
        HashSet<Integer> jStart = new HashSet<Integer>();
        ArrayList<Integer> starts = new ArrayList<Integer>();
        for (int i = 0; i < ac; i++) {
//            line = in.nextLine();
//            sc = new Scanner(line);
            int start = _c[i];
            int end = _d[i];
            cStart.add(start);
            endTimes.put(start, end);
            starts.add(start);
        }
        for (int i = 0; i < aj; i++) {
//            line = in.nextLine();
//            sc = new Scanner(line);
            int start = _j[i];
            int end = _k[i];
            jStart.add(start);
            endTimes.put(start, end);
            starts.add(start);
        }
        Collections.sort(starts);
        int cTime = 0;
        int jTime = 0;
        int freeSplitTime = 0;
        ArrayList<Integer> cLock = new ArrayList<Integer>();
        ArrayList<Integer> jLock = new ArrayList<Integer>();
        int startTime = starts.get(0);
        int lastTime = endTimes.get(starts.get(0));
        boolean cLast = cStart.contains(starts.get(0));
        if (cLast) {
            cTime += (lastTime - startTime);
        } else {
            jTime += (lastTime - startTime);
        }
        int swaps = 0;
        for (int i = 1; i < ac + aj; i++) {
            startTime = starts.get(i);
            int endTime = endTimes.get(startTime);
            if (cLast && cStart.contains(startTime)) {
                if (startTime != lastTime) cLock.add(startTime - lastTime);
                cLast = true;
                cTime += (endTime - startTime);
            } else if (cLast && !cStart.contains(startTime)) {
                freeSplitTime += (startTime - lastTime);
                cLast = false;
                jTime += (endTime - startTime);
                swaps++;
            } else if (!cLast && cStart.contains(startTime)) {
                freeSplitTime += (startTime - lastTime);
                cLast = true;
                cTime += (endTime - startTime);
                swaps++;
            } else {
                if (startTime != lastTime) jLock.add(startTime - lastTime);
                cLast = false;
                jTime += (endTime - startTime);
            }
            lastTime = endTime;
        }
        startTime = starts.get(0) + 1440;
        if (cLast && cStart.contains(starts.get(0))) {
            if (startTime != lastTime) cLock.add(startTime - lastTime);
        } else if (cLast && !cStart.contains(starts.get(0))) {
            freeSplitTime += (startTime - lastTime);
            swaps++;
        } else if (!cLast && cStart.contains(starts.get(0))) {
            freeSplitTime += (startTime - lastTime);
            swaps++;
        } else {
            if (startTime != lastTime) jLock.add(startTime - lastTime);
        }
        Collections.sort(jLock);
        Collections.sort(cLock);
        int cLockTotal = 0;
        for (int i = 0; i < cLock.size(); i++) {
            cLockTotal += cLock.get(i);
        }
        int jLockTotal = 0;
        for (int i = 0; i < jLock.size(); i++) {
            jLockTotal += jLock.get(i);
        }
        if (cLockTotal + cTime > 720) {
            for (int i = cLock.size() - 1; i >= 0; i--) {
                cLockTotal -= cLock.get(i);
                swaps += 2;
                if (cLockTotal + cTime <= 720) break;
            }
        } else if (jLockTotal + jTime > 720) {
            for (int i = jLock.size() - 1; i >= 0; i--) {
                jLockTotal -= jLock.get(i);
                swaps += 2;
                if (jLockTotal + jTime <= 720) break;
            }
        }
        return swaps;
    }
}
