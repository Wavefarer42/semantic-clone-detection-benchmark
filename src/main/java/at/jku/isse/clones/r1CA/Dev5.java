package at.jku.isse.clones.r1CA;

import java.util.Arrays;

public class Dev5 {

    public static double run(int _n, int _k, int[] _r, int[] _h) {

        int N = _n;
        int K = _k;

        Pancake[] arr = new Pancake[N];
        for (int i = 0; i < N; i++) {
            long r = _r[i];
            long h = _h[i];
            arr[i] = new Pancake(r, h);
        }
        Arrays.sort(arr);

        long maxArea = -1;

        for (int i = 0; i < N; i++) {
            //boolean[] visited = new boolean[N];
            //visited[i] = true;
            int size = 1;
            long area = arr[i].area;
            for (int j = 0; j < N && size < K; j++) {
                if (i == j) continue;
                if (arr[i].r >= arr[j].r) {
                    size++;
                    area += arr[j].area;
                    if (size == K) break;
                }
            }
            if (size == K) {
                long aa = area * 2 + (long) arr[i].r * arr[i].r;

                maxArea = Math.max(maxArea, aa);
            }
        }

        return Math.PI * maxArea;
    }

    static class Pancake implements Comparable<Pancake> {
        long r;
        long h;
        long area;

        Pancake(long rr, long hh) {
            r = rr;
            h = hh;
            area = (long) r * h;
        }

        public int compareTo(Pancake p) {
            long res = p.area - area;
            if (res < 0) return -1;
            else if (res == 0) return 0;
            else return 1;
        }
    }

}
