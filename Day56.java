class Solution {
    public int minChanges(String s) {
        int changes = 0;
        // Traverse the string in steps of 2
        for (int i = 0; i < s.length(); i += 2) {
            // If adjacent characters don't match, 1 change is required
            if (s.charAt(i) != s.charAt(i + 1)) {
                changes++;
            }
        }
        return changes;
    }
}
