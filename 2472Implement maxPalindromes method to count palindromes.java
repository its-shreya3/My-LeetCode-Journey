class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int lastEnd = 0;

        for (int i = 0; i < n; i++) {
            // Check both odd-length (center i, i) and even-length (center i, i+1) palindromes
            for (int j = 0; j <= 1; j++) {
                int left = i;
                int right = i + j;

                while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                    int len = right - left + 1;

                    if (len >= k) {
                        if (left >= lastEnd) {
                            count++;
                            lastEnd = right + 1;
                        }
                        // Stop expanding once we find the shortest valid palindrome for this center
                        break;
                    }
                    left--;
                    right++;
                }
            }
        }

        return count;
    }
}
