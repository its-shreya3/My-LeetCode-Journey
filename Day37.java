class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // 1. Check if already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }
        if (sorted) return 0;

        // 2. Length 2 cannot be sorted with a proper substring
        if (n == 2) return -1;

        // 3. Find global minimum and maximum characters
        char mn = 'z', mx = 'a';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < mn) mn = c;
            if (c > mx) mx = c;
        }

        // 4. One operation:
        // Either mn is at the start, or mx is at the end
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }

        // 5. Three operations:
        // s[0] == mx and s[n - 1] == mn, while mn does not appear anywhere in s[0..n-2]
        // and mx does not appear anywhere in s[1..n-1].
        if (s.charAt(0) == mx && s.charAt(n - 1) == mn) {
            boolean mnInPrefix = false;
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == mn) {
                    mnInPrefix = true;
                    break;
                }
            }

            boolean mxInSuffix = false;
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == mx) {
                    mxInSuffix = true;
                    break;
                }
            }

            if (!mnInPrefix && !mxInSuffix) {
                return 3;
            }
        }

        // 6. All remaining cases can be resolved in 2 operations
        return 2;
    }
}
