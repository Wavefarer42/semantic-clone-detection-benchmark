package at.jku.isse.clones.r0AC;

/**
 * @author Cratus
 */
public class Dev2 {
    public static int[] run(int _l, int _r) {
        int n = _l;
        int k = _r;

        //People gone in till last equal divisions
        int treeDepth = (int) Math.floor(Math.log(k) / Math.log(2.0));
        int peopleEntered = (int) Math.pow(2, treeDepth) - 1;

        int remBath = n - peopleEntered;
        int equalDivSize = remBath / (peopleEntered + 1);
        int spareDivCount = remBath % (peopleEntered + 1);

        int peoplePending = k - peopleEntered;
        int lastSlotSize = equalDivSize;
        if (peoplePending <= spareDivCount) {
            lastSlotSize++;
        }

        if (lastSlotSize % 2 == 0) {
            return new int[]{lastSlotSize / 2, (lastSlotSize / 2 - 1)};
        } else {
            return new int[]{(lastSlotSize - 1) / 2, (lastSlotSize - 1) / 2};
        }
    }
}
