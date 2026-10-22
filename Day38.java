class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        int zeroIndex = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
                break;
            }
        }
        
        boolean isInc = true;
        boolean isDec = true;
        
        // Check if the array is cyclically increasing or decreasing
        for (int i = 0; i < n; i++) {
            if (nums[(i + 1) % n] != (nums[i] + 1) % n) {
                isInc = false;
            }
            if (nums[(i + 1) % n] != (nums[i] - 1 + n) % n) {
                isDec = false;
            }
        }
        
        int minOps = Integer.MAX_VALUE;
        
        if (isInc) {
            if (zeroIndex == 0) {
                minOps = Math.min(minOps, 0);
            } else {
                minOps = Math.min(minOps, zeroIndex);
                minOps = Math.min(minOps, n - zeroIndex + 2);
            }
        }
        
        if (isDec) {
            minOps = Math.min(minOps, n - zeroIndex);
            minOps = Math.min(minOps, zeroIndex + 2);
        }
        
        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}
