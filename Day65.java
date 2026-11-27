class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        final int MAX_F = 1500;
        int[] freq = new int[MAX_F + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // multCount[f] = number of items whose factor is divisible by f
        int[] multCount = new int[MAX_F + 1];
        for (int f = 1; f <= MAX_F; f++) {
            int sum = 0;
            for (int m = f; m <= MAX_F; m += f) {
                sum += freq[m];
            }
            multCount[f] = sum;
        }

        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int f = item[0];
            int p = item[1];
            if (p > budget) {
                continue;
            }

            // Exclude the item itself (j != i)
            int freeCopies = multCount[f] - 1;
            int[] prev = dp.clone();
            int[] bestK = new int[budget + 1];

            for (int b = p; b <= budget; b++) {
                int gainForOneMore = prev[b - p] + 1;
                if (b >= 2 * p && bestK[b - p] + 1 > gainForOneMore) {
                    gainForOneMore = bestK[b - p] + 1;
                }
                bestK[b] = gainForOneMore;

                if (bestK[b] + freeCopies > dp[b]) {
                    dp[b] = bestK[b] + freeCopies;
                }
            }
        }

        int ans = 0;
        for (int val : dp) {
            if (val > ans) {
                ans = val;
            }
        }
        return ans;
    }
}
