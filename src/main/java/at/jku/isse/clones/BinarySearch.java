package at.jku.isse.clones;

import at.jku.isse.gradient.annotations.GradientModel;

import java.util.List;

@GradientModel
public class BinarySearch {

    static int iterative(int key, List<Integer> list) {
        int result = -1, low = 0, high = list.size() - 1;

        while (result == -1 && low <= high) {
            int mid = (low + high) / 2;

            if (key < list.get(mid)) {
                high = mid - 1;
            } else if (key > list.get(mid)) {
                low = mid + 1;
            } else if (key == list.get(mid)) {
                result = mid;
            }
        }

        return result;
    }

    static int recursive(int key, List<Integer> list) {
        return recursiveRegionSearch(key, list, 0, list.size() - 1);
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
