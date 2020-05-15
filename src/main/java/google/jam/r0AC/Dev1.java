package google.jam.r0AC;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Alibi
 */
public class Dev1 {
    public static Result run(int _l, int _r) {
        Map<Integer, Integer> map = new HashMap<>();
        int N = _l;
        int K = _r;
        int kLeft = K;
        map.put(N, 1);
        int space;
        while (true) {
            // find highest index
            space = findLargestSpace(map);
            int lsCount = map.get(space);
            if (kLeft <= lsCount) {
                if (space % 2 == 1) {
                    int leftAndRight = (space - 1) / 2;
                    return new Result(leftAndRight, leftAndRight);
                } else {
                    return new Result((space / 2), ((space / 2) - 1));
                }
            }
            kLeft -= lsCount;
            map.remove(space);
            if (space % 2 == 1) {
                addToSpace(map, (space - 1) / 2, lsCount * 2);
            } else {
                int rightSpace = space / 2;
                if (rightSpace > 0) {
                    addToSpace(map, rightSpace, lsCount);
                }
                int leftSpace = (space / 2) - 1;
                if (leftSpace > 0) {
                    addToSpace(map, leftSpace, lsCount);
                }
            }
        }
    }

    private static int findLargestSpace(Map<Integer, Integer> map) {
        Iterator<Integer> iterateKeys = map.keySet().iterator();
        int largestKey = 0;
        while (iterateKeys.hasNext()) {
            int keyToCheck = iterateKeys.next();
            if (keyToCheck > largestKey) {
                largestKey = keyToCheck;
            }
        }
        return largestKey;
    }

    private static void addToSpace(Map<Integer, Integer> map, int space, int count) {
        Integer existingObj = map.get(space);
        int existingNumeric = existingObj == null ? 0 : existingObj;
        map.put(space, count + existingNumeric);
    }
}
