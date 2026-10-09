class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // If we needed an odd number of ')', we must complete the previous pair first
                if (neededRight % 2 != 0) {
                    insertions++;   // insert one ')'
                    neededRight--;  // pair is now closed
                }
                neededRight += 2;   // current '(' requires two ')'
            } else {
                neededRight--;
                // Encountered ')' without a preceding '('
                if (neededRight < 0) {
                    insertions++;   // insert one '('
                    neededRight += 2; // this new '(' covers the current ')' and expects 1 more
                }
            }
        }

        return insertions + neededRight;
    }
}
