package at.jku.isse.clones.r1CC;

import java.util.Arrays;

/**
 * @author Dekacc
 */
public class Dev7 {
    public static double run(int _n, int _k, double _u, double[] _ps) {
        int N = _n;
        int K = _k;
        double U = _u;
        double[] arr = new double[N];
        for (int i = 0; i < N; i++) arr[i] = _ps[i];
        Arrays.sort(arr);

        while (U > 0) {
            double lowest = arr[0];
            double second = arr[0];
            int ind = 0;
            while (lowest == second) {
                ind++;
                if (ind == N) break;
                second = arr[ind];
            }
            if (ind == N) {
                //site isti
                double piece = U / N;
                for (int i = 0; i < N; i++) {
                    arr[i] = Math.min(arr[i] + piece, 1);
                }
                break;
            } else {
                second = arr[ind];
                double piece = Math.min(U / ind, second - lowest);
                for (int i = 0; i < ind; i++) {
                    arr[i] += piece;
                }
                U -= piece * ind;
            }
        }
        //
        //
        //
        //
        //
        //
        //			double remain = U;
        //
        //			while(N > 0 && remain > 0){
        //				while(N > 0 && arr[N - 1] == 1) N--;
        //
        //				double piece = remain / N;
        //				remain = 0;
        //				for(int i = 0; i<N; i++){
        //					if(arr[i] + piece <= 1) arr[i] += piece;
        //					else{
        //						remain += piece - (1.0 - arr[i]);
        //						arr[i] = 1;
        //					}
        //				}
        //			}

        double ans = 1;
        for (double d : arr) ans *= d;


        return ans;
    }
}
