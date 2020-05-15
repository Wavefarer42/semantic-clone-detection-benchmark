package at.jku.isse.clones;

import at.jku.isse.gradient.annotations.GradientModel;

import java.util.ArrayList;
import java.util.List;


@GradientModel
public class BubbleSort {

    public static List<Integer> iterative(List<Integer> list) {
        assert list != null;

        final List<Integer> sortedList = new ArrayList<>(list);
        for (int i = 0; i < sortedList.size() - 1; i++) {

            for (int j = 0; j < sortedList.size() - 1 - i; j++) {
                if (sortedList.get(j) > sortedList.get(j + 1)) {
                    sortedList.set(j, sortedList.set(j + 1, sortedList.get(j)));
                }
            }
        }

        return sortedList;
    }

    public static List<Integer> recursive(List<Integer> list) {
        final List<Integer> sortedList = new ArrayList<>(list);
        return recursive(sortedList, sortedList.size());
    }

    private static List<Integer> recursive(List<Integer> list, int n) {
        assert list != null;
        assert n >= 0;

        for (int i = 0; i < n - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                list.set(i, list.set(i + 1, list.get(i)));
            }
        }

        if (n - 1 > 1) {
            recursive(list, n - 1);
        }

        return list;
    }
}
