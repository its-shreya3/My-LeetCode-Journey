import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            return 0;
        }

        Arrays.sort(nums);

        int changeSmallestTwo = nums[n - 1] - nums[2];
        int changeLargestTwo = nums[n - 3] - nums[0];
        int changeOneSmallestOneLargest = nums[n - 2] - nums[1];

        return Math.min(
            changeSmallestTwo,
            Math.min(changeLargestTwo, changeOneSmallestOneLargest)
        );
    }
}
