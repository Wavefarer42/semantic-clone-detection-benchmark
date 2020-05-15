package google.jam.r0AB;

/**
 * @author araver
 */
public class Dev0 {

    public static long run(long _num) {
        String line = String.valueOf(_num);
        if (line.length() == 1) {
            return Long.parseLong(line);
        } else {
            return Long.parseLong(goTidy(line.toCharArray()));
        }
    }

    private static String goTidy(char[] a) {
        int i, n;
        n = a.length;// > 1
        char[] b = new char[n];

        //fall back to smaller string if needed.
        String fallback;
        if (a[0] == '1') {
            //fall back is '9999''
            fallback = String.format("%0" + (n - 1) + "d", 0).replace("0", "9");
            //System.out.println("fallback 1: "+fallback);
        } else {
            //fall back is smth like '29999''
            fallback = "" + (a[0] - '1') + String.format("%0" + (n - 1) + "d", 0).replace("0", "9");
            //System.out.println("fallback 2: "+fallback);
        }

        //greedy
        boolean cool = true;
        boolean backwards = false;

        for (i = 1, b[0] = a[0]; i >= 0 && i < n && cool; ) {
            if (i == 0) cool = false;

            //current char is a[i], biggest so far is last
            if (a[i] >= b[i - 1]) //go on
            {
                b[i] = a[i];
                i++;
            } else {
                //go back
                backwards = true;
                do {
                    i--;
                    if (i == 0) backwards = false;
                    else if (b[i] > 0 && b[i] - 1 >= b[i - 1]) {//still tidy
                        b[i]--;
                        backwards = false;
                    } //else keep going back;
                } while (backwards);

                //reached end
                cool = false;
                if (i > 0)//pad with 9s
                {
                    i++;
                    for (; i < n; i++) {
                        b[i] = '9';
                    }
                    fallback = String.valueOf(b);
                }
            }
        }

        if (cool == true) return String.valueOf(a);
        return fallback;
    }
}
