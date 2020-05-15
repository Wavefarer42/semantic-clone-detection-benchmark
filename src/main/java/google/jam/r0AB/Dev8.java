package google.jam.r0AB;

/**
 * @author 24man
 */
public class Dev8 {
    public static long run(long _num) {
        int[][] lofl = new int[1][];
        String input = String.valueOf(_num);
        int length = input.length();
        lofl[0] = new int[length];
        for (int j = 0; j < length; j++) {
            lofl[0][j] = Integer.parseInt(input.substring(j, j + 1));
        }

        adjust(lofl[0], lofl[0].length - 1);

        return dsum(lofl[0], 0, lofl[0].length);
    }


    public static int dsum(int[] tosum, int start, int end) {
        int total = 0;

        for (int i = start; i < end; i++)
            total = total + tosum[i];

        return total;

    }

    public static void adjust(int[] dights, int tofix) {
        int size = 0;
        //System.out.println(Arrays.toString(dights));
        for (int i = 0; i < dights.length; i++) {
            if (dights[i] != 0)
                break;
            else
                size++;
        }

        if (tofix <= size)
            return;

        if (dights[tofix] < dights[tofix - 1]) {
            for (int p = tofix; p < dights.length; p++)
                dights[p] = 9;
            dights[tofix - 1]--;
            //if(dights[tofix-1]>9||dights[tofix]>9)
            //   System.out.println("errror");

            if (tofix + 1 < dights.length && dights[tofix] > dights[tofix + 1])
                adjust(dights, tofix + 1);
        }

        if (tofix < dights.length - 1 && dights[tofix] > dights[tofix + 1])
            adjust(dights, tofix + 1);

        adjust(dights, tofix - 1);
    }
}
