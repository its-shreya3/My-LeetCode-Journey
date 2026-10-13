import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        
        // Take absolute values so sorting arranges them by magnitude (and thus by squared value)
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        
        Arrays.sort(nums);
        
        long totalSum = 0;
        int negativeCount = n / 2;
        
        // The smallest n / 2 squared values are subtracted
        for (int i = 0; i < negativeCount; i++) {
            long val = nums[i];
            totalSum -= val * val;
        }
        
        // The remaining (largest) squared values are added
        for (int i = negativeCount; i < n; i++) {
            long val = nums[i];
            totalSum += val * val;
        }
        
        return totalSum;
    }
}
