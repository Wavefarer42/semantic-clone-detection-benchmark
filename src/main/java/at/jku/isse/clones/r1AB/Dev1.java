package at.jku.isse.clones.r1AB;

import java.util.PriorityQueue;

/**
 * @author chavit92
 */
public class Dev1 {
    public static int run(int _ingredientsCount, int _packagesCount, int[] _ingredientQuantities, int[][] _packages) {
        int n = _ingredientsCount;
        int m = _packagesCount;

        int ans = 0;

        PriorityQueue<Integer>[] a = new PriorityQueue[n];

        long[] cost = new long[n];

        for (int i = 0; i < n; i++)
        {
            cost[i] = _ingredientQuantities[i];
        }

        for (int i = 0; i < n; i++)
        {
            a[i] = new PriorityQueue<Integer>();
            for (int j = 0; j < m; j++)
            {
                a[i].add(_packages[i][j]);
            }
        }

        boolean shouldAdd = true;

        int[] presenters = new int[n];

        while (true)
        {
            if (shouldAdd)
            {
                shouldAdd = false;
                for (int i = 0; i < n; i++)
                {
                    if (a[i].size() == 0)
                    {
                        return ans;
                    }
                    presenters[i] = a[i].poll();
                }
            }

            int minP = 0, maxP = 0;

            for (int i = 0; i < n; i++)
            {
                if (presenters[i]*cost[minP] < presenters[minP]*cost[i])
                {
                    minP = i;
                }
                if (presenters[i]*cost[maxP] > presenters[maxP]*cost[i])
                {
                    maxP = i;
                }
            }

            int cand = 100+(int)(Math.round(presenters[maxP]/cost[maxP]/1.1));

            while (cand * cost[maxP] * 110 >= presenters[maxP]*100)
            {
                cand--;
            }

            cand++;

            boolean isOk =
                    cand*cost[minP]*90 <= presenters[minP]*100 &&
                            cand*cost[minP]*110 >= presenters[minP]*100;

            if (isOk)
            {
                ans++;
                shouldAdd = true;
                continue;
            }

            if (a[minP].size() == 0)
            {
                return ans;
            }
            presenters[minP] = a[minP].poll();
        }


    }
}
