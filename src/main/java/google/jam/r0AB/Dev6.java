package google.jam.r0AB;

/**
 * @author 3mara
 */
public class Dev6 {
    public static long run(long _num) {
        char[] array = String.valueOf(_num).toCharArray();
        boolean run = true;
        boolean borrow = false;
        int farSub = array.length;
        while (run) {
            run = false;
            for (int j = array.length - 2; j >= 0; j--) {
                if (array[j] > array[j + 1]) {
                    if (!borrow && j < farSub) {
                        array[j]--;
                        farSub = j;
                    }
                    array[j + 1] = '9';
                    borrow = true;
                    j++;
                    run = true;
                } else {
                    borrow = false;
                }
            }
        }
        String text = String.valueOf(array);
        int index = text.indexOf("0");

        return index + 1;
    }
}
