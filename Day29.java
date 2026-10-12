import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCounts = new int[31];

        // 1. Count frequency of set bits across all numbers
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if (((num >> b) & 1) == 1) {
                    bitCounts[b]++;
                }
            }
        }

        long totalSum = 0;

        // 2. Greedily construct the k largest numbers
        for (int i = 0; i < k; i++) {
            long currentVal = 0;
            for (int b = 0; b < 31; b++) {
                if (bitCounts[b] > 0) {
                    currentVal |= (1L << b);
                    bitCounts[b]--;
                }
            }
            // 3. Add currentVal^2 modulo 10^9 + 7
            totalSum = (totalSum + (currentVal % MOD) * (currentVal % MOD)) % MOD;
        }

        return (int) totalSum;
    }
}
