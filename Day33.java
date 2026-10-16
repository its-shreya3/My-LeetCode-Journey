class Solution {
    private static final int MOD = 1000000007;

    // Segment Tree arrays
    private long[] sumSq;  // stores the sum of squares of counts
    private long[] sumV;   // stores the sum of counts
    private long[] lazy;   // lazy propagation array

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSq = new long[4 * n];
        sumV = new long[4 * n];
        lazy = new long[4 * n];

        // Map to store the last seen index of each element
        java.util.Map<Integer, Integer> lastSeen = new java.util.HashMap<>();
        
        long totalSum = 0;
        long currentSumSq = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];
            int p = lastSeen.getOrDefault(x, -1);
            
            // Update segment tree for range [p + 1, j] by adding 1 to counts
            if (p + 1 <= j) {
                update(1, 0, n - 1, p + 1, j, 1);
            }
            lastSeen.put(x, j);

            // Query the sum of squares for the current prefix [0, j]
            currentSumSq = querySumSq(1, 0, n - 1, 0, j);
            totalSum = (totalSum + currentSumSq) % MOD;
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = l + (r - l) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // (c + val)^2 = c^2 + 2*val*c + val^2 * len
        sumSq[node] = (sumSq[node] + 2 * val * sumV[node] + val * val * len) % MOD;
        sumV[node] = (sumV[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            return;
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        if (ql <= mid) {
            update(2 * node, l, mid, ql, qr, val);
        }
        if (qr > mid) {
            update(2 * node + 1, mid + 1, r, ql, qr, val);
        }
        sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
        sumV[node] = (sumV[2 * node] + sumV[2 * node + 1]) % MOD;
    }

    private long querySumSq(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return sumSq[node];
        }
        pushDown(node, l, r);
        int mid = l + (r - l) / 2;
        long res = 0;
        if (ql <= mid) {
            res = (res + querySumSq(2 * node, l, mid, ql, qr)) % MOD;
        }
        if (qr > mid) {
            res = (res + querySumSq(2 * node + 1, mid + 1, r, ql, qr)) % MOD;
        }
        return res;
    }
}
