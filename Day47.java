import java.util.Arrays;

class Solution {
    // Fenwick Tree (Binary Indexed Tree) for counts and sums
    static class Fenwick {
        int n;
        int[] treeCount;
        long[] treeSum;

        Fenwick(int n) {
            this.n = n;
            this.treeCount = new int[n + 1];
            this.treeSum = new long[n + 1];
        }

        void update(int idx, int countDelta, long valDelta) {
            for (; idx <= n; idx += idx & -idx) {
                treeCount[idx] += countDelta;
                treeSum[idx] += valDelta;
            }
        }

        // Returns {count, sum} in range [1, idx]
        long[] query(int idx) {
            int count = 0;
            long sum = 0;
            for (; idx > 0; idx -= idx & -idx) {
                count += treeCount[idx];
                sum += treeSum[idx];
            }
            return new long[]{count, sum};
        }

        // Binary lifting on BIT to find index of k-th smallest element (1-based)
        int findKth(int k) {
            int idx = 0;
            for (int i = 1 << 18; i > 0; i >>= 1) {
                if (idx + i <= n && treeCount[idx + i] < k) {
                    idx += i;
                    k -= treeCount[idx];
                }
            }
            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Coordinate Compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int uniqueCount = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount++] = sorted[i];
            }
        }

        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            ranks[i] = Arrays.binarySearch(sorted, 0, uniqueCount, nums[i]) + 1;
        }

        // Step 2: Compute cost for each window of size x
        long[] windowCost = new long[n];
        Fenwick bit = new Fenwick(uniqueCount);
        int targetRankK = (x + 1) / 2; // Lower median rank (1-indexed)

        for (int i = 0; i < n; i++) {
            bit.update(ranks[i], 1, nums[i]);

            if (i >= x) {
                bit.update(ranks[i - x], -1, -nums[i - x]);
            }

            if (i >= x - 1) {
                int medRank = bit.findKth(targetRankK);
                long medianVal = sorted[medRank - 1];

                long[] left = bit.query(medRank);
                long cLeft = left[0];
                long sLeft = left[1];

                long[] total = bit.query(uniqueCount);
                long cRight = total[0] - cLeft;
                long sRight = total[1] - sLeft;

                windowCost[i] = (cLeft * medianVal - sLeft) + (sRight - cRight * medianVal);
            }
        }

        // Step 3: Dynamic Programming
        // dp[j][i]: min cost choosing j non-overlapping windows in prefix nums[0..i-1]
        long[][] dp = new long[k + 1][n + 1];
        long INF = (long) 1e16;
        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        for (int j = 1; j <= k; j++) {
            for (int i = j * x; i <= n; i++) {
                // Option 1: Don't pick a window ending at i - 1
                long res = dp[j][i - 1];

                // Option 2: Pick the window ending at i - 1 (spans [i - x, i - 1])
                if (dp[j - 1][i - x] != INF) {
                    res = Math.min(res, dp[j - 1][i - x] + windowCost[i - 1]);
                }

                dp[j][i] = res;
            }
        }

        return dp[k][n];
    }
}
