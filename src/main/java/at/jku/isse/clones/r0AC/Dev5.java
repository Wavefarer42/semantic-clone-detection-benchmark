package at.jku.isse.clones.r0AC;

/**
 * @author annie1003
 */
public class Dev5 {
    public static Result run(int _l, int _r) {

        long n = _l;
        long k = _r;
        while (--k > 0) {
            n = (n - 1 + k % 2) / 2;
            k = k / 2 + k % 2;
        }
        return new Result((int) n / 2, (int) (n - 1) / 2);
    }
}
