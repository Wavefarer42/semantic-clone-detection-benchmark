package at.jku.isse.clones.r0AC;

/**
 * @author araver
 */
public class Dev0 {
    public static Result run(int _l, int _r) {
        int i, j, l;

        int minS = 0, maxS = 0, indexS = 0, beforeS, afterS;

        int n = _l;
        int k = _r;
        int MAX = 100000;
        int[] left = new int[MAX], right = new int[MAX], s = new int[MAX], min = new int[MAX], max = new int[MAX];

        boolean done = false;
        l = 0;
        int r = 0;

        if (n == k) {
            done = true;
        }//only one choice at the end

        if (!done) {
            //init
            s[0] = 1;
            s[n + 1] = 1;
            for (j = 0; j < n; j++) {
                left[j] = j;
                right[j] = n - 1 - j;
                min[j] = Math.min(left[j], right[j]);
                max[j] = Math.max(left[j], right[j]);
                s[j + 1] = 0;
            }

            //prettyPrint();

            //simulate
            for (j = 1; j <= k; j++) {
                //compute min, max, remember index.
                minS = -1;
                maxS = -1;
                indexS = -1;

                //compute
                //those S for which min(LS, RS) is maximal.
                //If tied, they choose the one among those where max(LS, RS) is maximal.
                //If tied, they choose the leftmost stall among those.

                //max(LS, RS) min(LS, RS)
                for (l = 0; l < n; l++) {
                    if (s[l + 1] == 0) {
                        if (min[l] == minS) {
                            if (max[l] > maxS) {
                                maxS = max[l];
                                indexS = l;
                            }
                        } else if (min[l] > minS) {//new min found
                            minS = min[l];
                            maxS = max[l];
                            indexS = l;
                        }
                    }
                }

                //occupy
                s[indexS + 1] = 1;
                //System.out.println("Place: "+(indexS+1));

                //reindex left and right stall only
                for (l = indexS; l >= 0 && s[l] == 0; l--) ;
                beforeS = l;
                for (l = indexS + 2; l <= n + 1 && s[l] == 0; l++) ;
                afterS = l;

                //System.out.println("Before = "+beforeS+", After = "+afterS);

                if (beforeS > 0) {//found left before guard
                    left[indexS] = indexS - beforeS;
                    beforeS--;
                    right[beforeS] = left[indexS];
                    min[beforeS] = Math.min(left[beforeS], right[beforeS]);
                    max[beforeS] = Math.max(left[beforeS], right[beforeS]);
                    beforeS++;
                } else {//only guard to the left
                    left[indexS] = indexS;
                }
                beforeS--;
                //update in-between
                //System.out.println("["+(beforeS+1)+", "+(indexS-1)+"]");
                for (l = indexS - 1; l > beforeS; l--) {
                    left[l] = l - beforeS - 1;
                    right[l] = indexS - l - 1;
                    min[l] = Math.min(left[l], right[l]);
                    max[l] = Math.max(left[l], right[l]);
                }

                if (afterS < n + 1) {//found right before guard
                    right[indexS] = afterS - 1 - indexS - 1;
                    afterS--;
                    right[afterS] = right[indexS];
                    min[afterS] = Math.min(left[afterS], right[afterS]);
                    max[afterS] = Math.max(left[afterS], right[afterS]);
                    afterS++;
                } else {//only guard to the right
                    right[indexS] = n - 1 - indexS;
                }

                afterS--;
                //update in-between
                //System.out.println("["+(indexS+1)+", "+(afterS-1)+"]");
                for (l = afterS - 1; l > indexS; l--) {
                    left[l] = l - indexS - 1;
                    right[l] = afterS - 1 - l;
                    min[l] = Math.min(left[l], right[l]);
                    max[l] = Math.max(left[l], right[l]);
                }

                min[indexS] = Math.min(left[indexS], right[indexS]);
                max[indexS] = Math.max(left[indexS], right[indexS]);

                //debug
                //prettyPrint();

                //System.out.println("min = "+minS+", max = "+maxS);
            }

            l = minS;
            r = maxS;

        }

        //System.out.println("l = "+l+", r = "+r);
        //System.out.println("====================");

        //output
        return new Result(r, l);
    }
}
