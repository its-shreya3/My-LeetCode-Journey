import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        
        // Step 1: Find the first and last occurrence of every character
        Arrays.fill(left, n);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            left[c] = Math.min(left[c], i);
            right[c] = Math.max(right[c], i);
        }
        
        List<String> result = new ArrayList<>();
        int lastRight = -1;
        
        // Step 2: Iterate through the string and greedily pick valid intervals
        for (int i = 0; i < n; i++) {
            // Only attempt to build a valid substring starting at a character's very first occurrence
            if (i == left[s.charAt(i) - 'a']) {
                int newRight = getValidRightEnd(s, i, left, right);
                
                // If a valid substring boundary is found
                if (newRight != -1) {
                    // If this valid substring starts after our last recorded substring, it's a new separate interval
                    if (i > lastRight) {
                        result.add(""); 
                    }
                    // Otherwise, it starts INSIDE our last recorded substring.
                    // Because we want the minimum total length, we replace the larger outer substring with this smaller inner one.
                    
                    lastRight = newRight;
                    result.set(result.size() - 1, s.substring(i, lastRight + 1));
                }
            }
        }
        
        return result;
    }
    
    private int getValidRightEnd(String s, int start, int[] left, int[] right) {
        int maxRight = right[s.charAt(start) - 'a'];
        
        // Expand the right boundary if characters within the current window extend further right
        for (int j = start; j <= maxRight; j++) {
            int c = s.charAt(j) - 'a';
            // If any character inside our window has its first occurrence BEFORE our start index, 
            // this substring is invalid (it would require extending leftward).
            if (left[c] < start) {
                return -1;
            }
            maxRight = Math.max(maxRight, right[c]);
        }
        
        return maxRight;
    }
}
