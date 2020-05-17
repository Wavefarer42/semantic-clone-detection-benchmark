package at.jku.isse.clones.search;

import java.util.List;

/**
 * binary search iterative
 */
public class Dev0 {

    public static int run(int _key, List<Integer> _list) {
        int result = -1, low = 0, high = _list.size() - 1;

        while (result == -1 && low <= high) {
            int mid = (low + high) / 2;

            if (_key < _list.get(mid)) {
                high = mid - 1;
            } else if (_key > _list.get(mid)) {
                low = mid + 1;
            } else if (_key == _list.get(mid)) {
                result = mid;
            }
        }

        return result;
    }
}
