class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the sum is greater than the maximum possible sum of 'num' digits, 
        // or if it's impossible to form a positive number (sum < 1 when num > 0), return ""
        if ((long) num * 9 < sum || sum < 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < num; i++) {
            // We want to pick the largest possible digit for the current position, 
            // but we must leave enough sum for the remaining (num - 1 - i) positions.
            int remainingPositions = num - 1 - i;
            int maxPossibleForRest = remainingPositions * 9;
            
            // The current digit must be at least (sum - maxPossibleForRest) 
            // to ensure the remaining sum can be formed.
            int minNeeded = Math.max(0, sum - maxPossibleForRest);
            
            // To maximize the score and the number, we want the largest valid digit 
            // up to 9, but bounded by what's left of the sum.
            int cur = Math.min(9, sum - minNeeded); 
            // Wait, to maximize the score, we actually want to greedily pack 9s from left to right as much as possible!
            // Let's refine: greedily take min(9, sum - remainingPositions * 0) 
            // as long as sum - cur <= remainingPositions * 9.
            
            int digit = Math.min(9, sum - minNeeded);
            // Actually, let's use the standard greedy choice:
            // The maximum we can put here is min(9, sum - remainingPositions * 0) 
            // but constrained by the fact that the rest can take at most maxPossibleForRest.
            // So sum - digit <= maxPossibleForRest => digit >= sum - maxPossibleForRest.
            // To make the number as large as possible on the left, we want 'digit' to be as large as possible.
            // Since we want the largest digit, can we just take min(9, sum)? 
            // Let's check: if we take min(9, sum), the remaining sum is (sum - min(9, sum)). 
            // As long as (sum - min(9, sum)) <= remainingPositions * 9, it's valid!
            // If it exceeds, we must take a smaller digit so the rest is achievable.
            // Rearranging: sum - remainingPositions * 9 <= digit <= 9. 
            // Since we want the largest digit for the leftmost position, we take the maximum possible valid digit:
            // digit = Math.min(9, sum);
            // But wait, if sum - digit > remainingPositions * 9, then digit is too small (we took too little or too much? 
            // If sum is large, we should take 9. If sum is small, we take what's needed).
            // Let's write the clean, standard greedy logic:
            
            // Correct greedy choice for max lexicographical string with target sum:
            // Try digits from 9 down to 0:
        }
        
        // Let's implement the standard O(num) approach cleanly:
        for (int i = 0; i < num; i++) {
            int rem = num - 1 - i;
            // Try digits from 9 down to 0
            for (int d = 9; d >= 0; d--) {
                if (sum - d <= rem * 9 && sum - d >= 0) {
                    sb.append(d);
                    sum -= d;
                    break;
                }
            }
        }
        
        return sb.toString();
    }
}
