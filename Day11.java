class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int temp = nums[i];
            
            // Calculate the sum of digits
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
            
            // If the sum equals the index, we return immediately 
            // since we are iterating from the smallest index upwards.
            if (sum == i) {
                return i;
            }
        }
        
        return -1;
    }
}
