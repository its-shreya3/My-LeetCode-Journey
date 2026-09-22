class Solution {
    int[] totalProd;
    int[][] cnt;
    int K;

    private void merge(int node, int leftNode, int rightNode) {
        // Total product of the merged node
        totalProd[node] = (totalProd[leftNode] * totalProd[rightNode]) % K;
        
        // Inherit prefixes from the left child
        for (int i = 0; i < K; i++) {
            cnt[node][i] = cnt[leftNode][i];
        }
        
        // Add prefixes that span across left and right children
        for (int i = 0; i < K; i++) {
            int newRem = (totalProd[leftNode] * i) % K;
            cnt[node][newRem] += cnt[rightNode][i];
        }
    }

    private void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            totalProd[node] = nums[start] % K;
            cnt[node][nums[start] % K] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        
        build(leftNode, start, mid, nums);
        build(rightNode, mid + 1, end, nums);
        
        merge(node, leftNode, rightNode);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            totalProd[node] = val % K;
            for (int i = 0; i < K; i++) cnt[node][i] = 0;
            cnt[node][val % K] = 1;
            return;
        }
        int mid = start + (end - start) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;
        
        if (idx <= mid) {
            update(leftNode, start, mid, idx, val);
        } else {
            update(rightNode, mid + 1, end, idx, val);
        }
        
        merge(node, leftNode, rightNode);
    }

    private int[] query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }
        if (l <= start && end <= r) {
            // Return state size K+1. Index 0 to K-1 stores prefix counts, Index K stores totalProd.
            int[] res = new int[K + 1];
            for (int i = 0; i < K; i++) res[i] = cnt[node][i];
            res[K] = totalProd[node];
            return res;
        }
        int mid = start + (end - start) / 2;
        int[] p1 = query(2 * node + 1, start, mid, l, r);
        int[] p2 = query(2 * node + 2, mid + 1, end, l, r);
        
        if (p1 == null) return p2;
        if (p2 == null) return p1;
        
        int[] res = new int[K + 1];
        res[K] = (p1[K] * p2[K]) % K;
        
        for (int i = 0; i < K; i++) {
            res[i] = p1[i];
        }
        for (int i = 0; i < K; i++) {
            int newRem = (p1[K] * i) % K;
            res[newRem] += p2[i];
        }
        return res;
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        this.K = k;
        this.totalProd = new int[4 * n];
        this.cnt = new int[4 * n][K];
        
        build(0, 0, n - 1, nums);
        
        int m = queries.length;
        int[] ans = new int[m];
        
        for (int i = 0; i < m; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];
            
            update(0, 0, n - 1, index, value);
            
            int[] res = query(0, 0, n - 1, start, n - 1);
            ans[i] = res != null ? res[x] : 0;
        }
        
        return ans;
    }
}
