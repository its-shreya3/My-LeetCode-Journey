class Solution {
    public int sortPermutation(int[] nums) {
        int k = -1; // -1 in two's complement has all bits set to 1
        boolean isSorted = true;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                isSorted = false;
                k &= nums[i]; // Bitwise AND of all misplaced elements
            }
        }
        
        return isSorted ? 0 : k;
    }
}
