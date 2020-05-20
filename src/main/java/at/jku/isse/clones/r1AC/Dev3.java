package at.jku.isse.clones.r1AC;

/**
 * @author chavit92
 */
public class Dev3 {


    static int bestAns;
    static int HD;

    public static int run(int _hd, int _ad, int _hk, int _ak, int _b, int _d) {

        bestAns = 1000000;
        int hd = _hd;
        int ad = _ad;

        HD = hd;

        int hk = _hk;
        int ak = _ak;

        int b = _b;
        int d = _d;

        solveD(hd, ad, hk, ak, b, d, 0);
//        out.println(bestAns == 1000000 ? "IMPOSSIBLE" : bestAns);
        return bestAns == 1000000 ? -1 : bestAns;
    }

    private static void solveD(int hd, int ad, int hk, int ak, int b, int d, int i) {
        solveB(hd, ad, hk, ak, b, i);

        if (ak == 0 || d == 0) {
            return;
        }

        if (hd <= Math.max(0, ak - d)) {
            if (HD - ak <= Math.max(0, ak - d)) {
                return;
            }
            solveD(HD - ak, ad, hk, ak, b, d, i + 1);
            return;
        }

        solveD(hd - Math.max(0, ak - d), ad, hk, Math.max(0, ak - d), b, d, i + 1);
    }

    private static void solveB(int hd, int ad, int hk, int ak, int b, int i) {
        solveK(hd, ad, hk, ak, i);

        if (ad >= hk || b == 0) {
            return;
        }

        if (hd <= ak) {
            if (HD - ak <= ak) {
                return;
            }
            solveB(HD - ak, ad, hk, ak, b, i + 1);
            return;
        }
        solveB(hd - ak, ad + b, hk, ak, b, i + 1);

    }

    private static void solveK(int hd, int ad, int hk, int ak, int i) {
        if (hk <= ad) {
            bestAns = Math.min(bestAns, i + 1);
            return;
        }

        if (hd <= ak) {
            if (HD - ak <= ak) {
                return;
            }
            solveK(HD - ak, ad, hk, ak, i + 1);
            return;
        }

        solveK(hd - ak, ad, hk - ad, ak, i + 1);
    }

}
