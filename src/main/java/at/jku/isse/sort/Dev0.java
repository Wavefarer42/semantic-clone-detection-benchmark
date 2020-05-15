package at.jku.isse.sort;

import java.util.ArrayList;
import java.util.List;


/**
 * BubbleSort iterative
 */
public class Dev0 {

    public static List<Integer> run(List<Integer> _list) {
        assert _list != null;

        final List<Integer> sortedList = new ArrayList<>(_list);
        for (int i = 0; i < sortedList.size() - 1; i++) {

            for (int j = 0; j < sortedList.size() - 1 - i; j++) {
                if (sortedList.get(j) > sortedList.get(j + 1)) {
                    sortedList.set(j, sortedList.set(j + 1, sortedList.get(j)));
                }
            }
        }

        return sortedList;
    }
}
