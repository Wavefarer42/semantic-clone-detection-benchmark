package at.jku.isse.clones.r0AC;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 2001zhaozhao
 */
public class Dev8 {
    public static Result run(int _l, int _r) {
        long initialsize = _l;
        long numCustomers = _r;

        //key: the gap's size
        //value: how many gaps there are
        TreeMap<Long, Long> map = new TreeMap<Long, Long>(new Comparator<Long>() {
            //goes backwards so the largest integer goes in first
            @Override
            public int compare(Long o1, Long o2) {
                if (o2 > o1) return 1;
                if (o2 == o1) return 0;
                return -1;
            }
        });

        map.put(initialsize, 1L);

        //for each key, split in half
        //and remove the original key
        //should be log^2(x) or something considering the hashmap could gain some size
        int left = -1, right = -1;
        while (numCustomers > 0) {
            Map.Entry<Long, Long> entry = map.firstEntry();
            Long length = entry.getKey();
            long amount = entry.getValue();
            long firsthalf = (length - 1) / 2;
            long secondhalf = (length - 1) - firsthalf;
            add(map, firsthalf, amount);
            add(map, secondhalf, amount);
            numCustomers -= amount;
            map.remove(length);

            //now were done, the two halves represent the last customer now
            if (numCustomers <= 0) {
                left = (int) Math.max(firsthalf, secondhalf);
                right = (int) Math.min(firsthalf, secondhalf);
            }
        }

        return new Result(left, right);
    }

    public static void add(TreeMap<Long, Long> map, long key, long value) {
        if (value == 0) return; //dont bother adding gaps with length 0 we dont need it
        long initial = map.containsKey(key) ? map.get(key) : 0;
        initial += value;
        map.put(key, initial);
    }
}
