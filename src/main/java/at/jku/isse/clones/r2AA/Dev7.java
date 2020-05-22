package at.jku.isse.clones.r2AA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author uwi
 */
public class Dev7 {
    public static int run(int _n, int _p, int[] _g) {

        int n = _n;
        int P = _p;
        int[] g = _g;

        int[] f = new int[P];
        for(int v : g){
            f[v%P]++;
        }
        // 1+3
        // 1+1+2
        // 3+3+2
        // 1+1+1+1
        // 2+2
        // 3+3+3+3

        if(P == 4){
            int[] u = new int[3];
            List<int[]> valid = new ArrayList<>();
            do{
                if(u[0]+u[1]+u[2] > 0 && u[0]+u[1]+u[2] <= 4 && (u[0]+2*u[1]+3*u[2])%4 == 0){
                    valid.add(Arrays.copyOf(u, 3));
                }
            }while(inc(u, 5));

            int[][][] dp = new int[f[1]+1][f[2]+1][f[3]+1];
            for(int[][] row : dp){
                for(int[] row2 : row){
                    Arrays.fill(row2, -99999999);
                }
            }
            dp[f[1]][f[2]][f[3]] = f[0];
            int ret = f[0];
            for(int i = f[1];i >= 0;i--){
                for(int j = f[2];j >= 0;j--){
                    for(int k = f[3];k >= 0;k--){
                        ret = Math.max(ret, dp[i][j][k] + (i+j+k > 0 ? 1 : 0));
                        for(int[] v : valid){
                            int ni = i-v[0], nj = j-v[1], nk = k-v[2];
                            if(ni >= 0 && nj >= 0 && nk >= 0){
                                dp[ni][nj][nk] = Math.max(dp[ni][nj][nk], dp[i][j][k] + 1);
                            }
                        }
                    }
                }
            }
            return ret;
        }else if(P == 3){
            int[] u = new int[2];
            List<int[]> valid = new ArrayList<>();
            do{
                if(u[0]+u[1] > 0 && u[0]+u[1] <= 3 && (u[0]+2*u[1])%3 == 0){
                    valid.add(Arrays.copyOf(u, 2));
                }
            }while(inc(u, 4));

            int[][] dp = new int[f[1]+1][f[2]+1];
            for(int[] row : dp){
                Arrays.fill(row, -99999999);
            }
            dp[f[1]][f[2]] = f[0];
            int ret = f[0];
            for(int i = f[1];i >= 0;i--){
                for(int j = f[2];j >= 0;j--){
                    ret = Math.max(ret, dp[i][j] + (i+j > 0 ? 1 : 0));
                    for(int[] v : valid){
                        int ni = i-v[0], nj = j-v[1];
                        if(ni >= 0 && nj >= 0){
                            dp[ni][nj] = Math.max(dp[ni][nj], dp[i][j] + 1);
                        }
                    }
                }
            }
            return ret;
        }else{
            return  f[0] + (f[1]+1) / 2;
        }
    }

    public static boolean inc(int[] a, int base) {
        int n = a.length;
        int i;
        for (i = n - 1; i >= 0 && a[i] == base - 1; i--)
            ;
        if (i == -1)
            return false;

        a[i]++;
        Arrays.fill(a, i + 1, n, 0);
        return true;
    }
}
