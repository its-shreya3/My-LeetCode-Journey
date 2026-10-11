class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Iterate through 1-based indexing
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                int val = nums[i - 1]; // map 1-based index to 0-based array index
                sum += val * val;
            }
        }
        
        return sum;
    }
}
