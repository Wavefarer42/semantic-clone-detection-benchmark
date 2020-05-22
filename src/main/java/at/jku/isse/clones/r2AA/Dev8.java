package at.jku.isse.clones.r2AA;

/**
 * @author gedrox
 */
public class Dev8 {

    public static int run(int _n, int _p, int[] _g) {
        int N = _n;
        int P = _p;

        int[] m = new int[P];

        for (int n_i = 0; n_i < N; n_i++) {
            m[_g[n_i] % P]++;
        }

        int answer = 0;
        switch (P) {
            case 2:
                answer = m[0] + (m[1] + 1) / 2;
                break;
            case 3:
                int canPair = Math.min(m[1], m[2]);
                // this divides by 3
                answer = m[0] + canPair + (m[1] - canPair) / 3 + (m[2] - canPair) / 3;
                if ((m[1] - canPair) % 3 != 0 || (m[2] - canPair) % 3 != 0) {
                    answer++;
                }
                break;
            case 4:

                answer += m[0];
                m[0] = 0;

                answer += m[2] / 2;
                m[2] = m[2] % 2;

                int set13 = Math.min(m[1], m[3]);
                answer += set13;
                m[1] -= set13;
                m[3] -= set13;

                if (m[2] == 1 && m[1] >= 2) {
                    answer++;
                    m[2] = 0;
                    m[1] -= 2;
                }

                if (m[2] == 1 && m[3] >= 2) {
                    answer++;
                    m[2] = 0;
                    m[3] -= 2;
                }

                int set1 = m[1] / 4;
                answer += set1;
                m[1] -= 4 * set1;

                int set3 = m[3] / 4;
                answer += set3;
                m[3] -= 4 * set3;

                if (m[1] > 0 || m[2] > 0 || m[3] > 0) {
                    answer++;
                }

                break;
            default:
                throw new IllegalArgumentException();
        }

        return answer;
    }
}
