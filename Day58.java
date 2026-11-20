import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 1;

        for (int x : nums) {
            // Option 1: increment x to (x + 1), extending sequence ending at x
            int extendInc = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), extendInc));
            maxLen = Math.max(maxLen, dp.get(x + 1));

            // Option 2: keep x as x, extending sequence ending at (x - 1)
            int extendSame = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, Math.max(dp.getOrDefault(x, 0), extendSame));
            maxLen = Math.max(maxLen, dp.get(x));
        }

        return maxLen;
    }
}
