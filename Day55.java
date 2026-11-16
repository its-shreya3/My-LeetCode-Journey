class Solution {
    public String digitSum(String s, int k) {
        // Continue rounds as long as the string length is greater than k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Process the string in consecutive groups of size k
            for (int i = 0; i < s.length(); i += k) {
                int end = Math.min(i + k, s.length());
                String group = s.substring(i, end);
                
                // Calculate the sum of digits in the current group
                int sum = 0;
                for (int j = 0; j < group.length(); j++) {
                    sum += group.charAt(j) - '0';
                }
                
                // Append the sum as a string
                nextRound.append(sum);
            }
            
            // Update s for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}
