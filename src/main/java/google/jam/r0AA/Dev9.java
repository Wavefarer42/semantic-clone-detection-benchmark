package google.jam.r0AA;

/**
 * @author 2001zhaozhao
 */
public class Dev9 {
    public static int run(String _pattern, int _num) {
        String str = _pattern;
        int movecount = _num;
        boolean[] list = new boolean[str.length()];
        boolean lastoneistrue = false;
        for (int j = 0; j < str.length(); j++) {
            char c = str.charAt(j);
            boolean thisoneistrue = c == '+';
            list[j] = lastoneistrue != thisoneistrue; //true if its different
            lastoneistrue = thisoneistrue;
        }

        //calculate
        int flips = 0;
        boolean currentstate = false;
        for (int j = 0; j < str.length() + 1 - movecount; j++) {
            currentstate = list[j] ? !currentstate : currentstate; //get new current
            if (!currentstate) {
                //flip
                currentstate = !currentstate;
                list[j] = !list[j];
                //flip back after
                if (j + movecount < str.length()) list[j + movecount] = !list[j + movecount];
                flips++;
            }
        }

        boolean valid = true;
        //check if the last ones are all a plus
        for (int j = str.length() + 1 - movecount; j < str.length(); j++) {
            currentstate = list[j] ? !currentstate : currentstate; //get new current
            if (currentstate == false) valid = false;
        }

        return flips;
    }
}
