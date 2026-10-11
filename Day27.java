class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;
        
        // Count frequencies of each absolute difference
        int maxDiff = 0;
        for (int i = 0; i < n; i++) {
            maxDiff = Math.max(maxDiff, Math.abs(nums1[i] - nums2[i]));
        }
        
        long[] count = new long[maxDiff + 1];
        long totalSumDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            count[d]++;
            totalSumDiff += d;
        }
        
        // If total operations can reduce all differences to 0
        if (totalSumDiff <= totalK) {
            return 0;
        }
        
        // Greedily reduce from the largest differences downwards
        for (int d = maxDiff; d > 0 && totalK > 0; d--) {
            if (count[d] == 0) continue;
            
            long take = Math.min(count[d], totalK);
            count[d] -= take;
            count[d - 1] += take;
            totalK -= take;
        }
        
        // Calculate the minimum sum of squared differences
        long result = 0;
        for (int d = 0; d <= maxDiff; d++) {
            if (count[d] > 0) {
                result += count[d] * (long) d * d;
            }
        }
        
        return result;
    }
}
