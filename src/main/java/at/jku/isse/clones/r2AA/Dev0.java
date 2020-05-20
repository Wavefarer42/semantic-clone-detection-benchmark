package at.jku.isse.clones.r2AA;

/**
 * @author acmabhi
 */
public class Dev0 {

    public static String run(int _n, int[] _colors) {
        int n = _n;
        int t1 = _colors[0];
        int t12 = _colors[1];
        int t2 = _colors[2];
        int t23 = _colors[3];
        int t3 = _colors[4];
        int t13 = _colors[5];
        if (t1 == t23)
            if (t1 + t23 == n)
                return build("RG", t1);

        if (t2 == t13)
            if (t2 + t13 == n)
                return build("YV", t2);

        if (t3 == t12)
            if (t3 + t12 == n)
                return build("BO", t3);

        int vt1 = t1;
        int vt2 = t2;
        int vt3 = t3;

        String rR = build("RG", t23) + "R";
        t1 -= t23;
        String rY = build("YV", t13) + "Y";
        t2 -= t13;
        String rB = build("BO", t12) + "B";
        t3 -= t12;
        //System.err.println(rR + " " + rY + " " + rB);
        if (vt1 > 0 && t1 <= 0)
            return "IMPOSSIBLE";
        if (vt2 > 0 && t2 <= 0)
            return "IMPOSSIBLE";
        if (vt3 > 0 && t3 <= 0)
            return "IMPOSSIBLE";
        String s = solveSimple(t1, t2, t3);
        if (s.equals("IMPOSSIBLE"))
            return s;
        if (t23 != 0)
            s = s.replaceFirst("R", rR);
        if (t13 != 0)
            s = s.replaceFirst("Y", rY);
        if (t12 != 0)
            s = s.replaceFirst("B", rB);
        return s;
    }

    static String build(String rep, int q) {
        StringBuilder x = new StringBuilder("");
        for (int i = 0; i < q; i++)
            x.append(rep);
        return x.toString();
    }

    static String solveSimple(int r, int y, int b) {
        if (r + y < b)
            return "IMPOSSIBLE";
        if (r + b < y)
            return "IMPOSSIBLE";
        if (b + y < r)
            return "IMPOSSIBLE";

        StringBuilder ans = new StringBuilder("");
        int max = Math.max(r, Math.max(y, b));
        int first = (r == max ? 0 : y == max ? 1 : 2);
        int last = first;
        if (first == 0) {
            ans.append("R");
            r--;
            last = 0;
        }

        if (first == 1) {
            ans.append("Y");
            y--;
            last = 1;
        }

        if (first == 2) {
            ans.append("B");
            b--;
            last = 2;
        }

        while (r + y + b != 0) {
            boolean canr = last != 0;
            boolean cany = last != 1;
            boolean canb = last != 2;

            max = Math.max(r, Math.max(y, b));

            if (first == 0 && r == max && canr) {
                ans.append("R");
                r--;
                last = 0;
            } else if (first == 1 && y == max && cany) {
                ans.append("Y");
                y--;
                last = 1;
            } else if (first == 2 && b == max && canb) {
                ans.append("B");
                b--;
                last = 2;
            } else if (r == max && canr) {
                ans.append("R");
                r--;
                last = 0;
            } else if (y == max && cany) {
                ans.append("Y");
                y--;
                last = 1;
            } else if (b == max && canb) {
                ans.append("B");
                b--;
                last = 2;
            } else {
                int adjr = r * 2 + first == 0 ? 1 : 0;
                int adjy = y * 2 + first == 1 ? 1 : 0;
                int adjb = b * 2 + first == 2 ? 1 : 0;

                if (canr && cany) {
                    if (adjr > adjy) {
                        ans.append("R");
                        r--;
                        last = 0;
                    } else {
                        ans.append("Y");
                        y--;
                        last = 1;
                    }
                } else if (canr && canb) {
                    if (adjr > adjb) {
                        ans.append("R");
                        r--;
                        last = 0;
                    } else {
                        ans.append("B");
                        b--;
                        last = 2;
                    }
                } else if (canb && cany) {
                    if (adjb > adjy) {
                        ans.append("B");
                        b--;
                        last = 2;
                    } else {
                        ans.append("Y");
                        y--;
                        last = 1;
                    }
                }
            }

        }

        return ans.toString();
    }

}
