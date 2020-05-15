package at.jku.isse.search;

import java.util.List;

/**
 * binary search recursive
 */
public class Dev1 {

    public static int run(int _key, List<Integer> _list) {
        return recursiveRegionSearch(_key, _list, 0, _list.size() - 1);
    }

    private static int recursiveRegionSearch(int key, List<Integer> list, int low, int high) {
        assert low >= 0 & high < list.size();

        int mid = (low + high) / 2;

        if (high < low) {
            return -1;
        }

        if (key < list.get(mid)) {
            return recursiveRegionSearch(key, list, low, mid - 1);
        } else if (key > list.get(mid)) {
            return recursiveRegionSearch(key, list, mid + 1, high);
        } else if (key == list.get(mid)) {
            return mid;
        }

        return -1;
    }
}
