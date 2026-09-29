class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // Any valid path length is m + n - 1.
        // If the path length is odd, it can never form balanced parentheses.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Maximum possible open brackets at any point is (m + n - 1) / 2
        int maxOpen = (m + n) / 2;
        memo = new Boolean[m][n][maxOpen + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int open) {
        // Track the current balance of brackets
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        // If open count goes below 0, invalid path
        // If open count exceeds maximum allowed valid depth, prune search
        if (open < 0 || open > (m + n) / 2) {
            return false;
        }

        // Base case: reached bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        // Return memoized result if already computed
        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean result = false;

        // Move Down
        if (r + 1 < m) {
            result = result || dfs(grid, r + 1, c, open);
        }

        // Move Right
        if (c + 1 < n) {
            result = result || dfs(grid, r + 1 > m ? r : r, c + 1, open);
        }

        return memo[r][c][open] = result;
    }
}
