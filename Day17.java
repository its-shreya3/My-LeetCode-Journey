class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int depth = 0;
        
        for (int i = 0; i < n; i++) {
            if (seq.charAt(i) == '(') {
                // Increment depth first for an open parenthesis, then assign
                depth++;
                result[i] = depth % 2;
            } else {
                // Assign current depth for a close parenthesis, then decrement
                result[i] = depth % 2;
                depth--;
            }
        }
        
        return result;
    }
}
