import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTarget = 0;
        int targetCount = 0;
        
        // Count elements less than target and equal to target
        for (int num : nums) {
            if (num < target) {
                lessThanTarget++;
            } else if (num == target) {
                targetCount++;
            }
        }
        
        // Build the result list with consecutive indices
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetCount; i++) {
            result.add(lessThanTarget + i);
        }
        
        return result;
    }
}
