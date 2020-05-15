package at.jku.isse.clones;

import at.jku.isse.gradient.annotations.GradientModel;

import java.util.ArrayList;
import java.util.List;

@GradientModel
public class MergeSort {

    public static List<Integer> iterative(List<Integer> toSort) {
        assert toSort != null;

        final List<Integer> sortedList = new ArrayList<>(toSort);
        final List<Integer> buffer = new ArrayList<>(toSort);

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

    public static List<Integer> recursive(List<Integer> list) {
        final List<Integer> sortedList = new ArrayList<>(list);
        recursive(sortedList, list.size());
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
