package at.jku.isse.clones.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * MergeSort iterative
 */
public class Dev2 {
    public static List<Integer> run(List<Integer> _list) {
        assert _list != null;

        final List<Integer> sortedList = new ArrayList<>(_list);
        final List<Integer> buffer = new ArrayList<>(_list);

        int low = 0;
        int high = sortedList.size() - 1;
        for (int i = 1; i <= high - low; i = 2 * i) {
            for (int j = low; j < high; j += 2 * i) {
                mergeIterative(sortedList, buffer, j, Integer.min(j + i - 1, high), Integer.min(j + 2 * i - 1, high));
            }
        }

        return sortedList;
    }

    private static void mergeIterative(List<Integer> list, List<Integer> buffer, int low, int mid, int high) {
        int k = low, i = low, j = mid + 1;

        while (i <= mid && j <= high) {
            if (list.get(i) < list.get(j)) {
                buffer.set(k++, list.get(i++));
            } else {
                buffer.set(k++, list.get(j++));
            }
        }

        while (i <= mid) {
            buffer.set(k++, list.get(i++));
        }

        for (i = low; i <= high; i++) {
            list.set(i, buffer.get(i));
        }
    }

}
