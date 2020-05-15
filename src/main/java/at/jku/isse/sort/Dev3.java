package at.jku.isse.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * MergeSort revursive
 */
public class Dev3 {
    public static List<Integer> run(List<Integer> _list) {
        final List<Integer> sortedList = new ArrayList<>(_list);
        recursive(sortedList, _list.size());
        return sortedList;
    }

    private static void recursive(List<Integer> list, int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;
        List<Integer> left = list.subList(0, mid);
        List<Integer> right = list.subList(mid, list.size());

        recursive(left, mid);
        recursive(right, n - mid);

        mergeRecursive(list, left, right, mid, n - mid);
    }

    private static void mergeRecursive(List<Integer> list, List<Integer> left, List<Integer> right, int low, int high) {

        int i = 0, j = 0, k = 0;

        while (i < low && j < high) {
            if (left.get(i) <= right.get(j)) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }

        while (i < low) {
            list.set(k++, left.get(i++));
        }

        while (j < high) {
            list.set(k++, right.get(j++));
        }
    }
}
