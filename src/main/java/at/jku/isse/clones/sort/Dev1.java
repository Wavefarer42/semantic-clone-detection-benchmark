package at.jku.isse.clones.sort;

import java.util.ArrayList;
import java.util.List;


/**
 * BubbleSort recursive
 */
public class Dev1 {
    public static List<Integer> run(List<Integer> _list) {
        final List<Integer> sortedList = new ArrayList<>(_list);
        return recursive(sortedList, sortedList.size());
    }

    private static List<Integer> recursive(List<Integer> list, int n) {
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
