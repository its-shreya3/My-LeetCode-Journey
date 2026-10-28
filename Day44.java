class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][][] dp = new boolean[m][n][1024];

        // Base case: XOR value at the starting cell
        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int v = 0; v < 1024; v++) {
                    if (dp[i][j][v]) {

                        // Move down
                        if (i + 1 < m) {
                            dp[i + 1][j][v ^ grid[i + 1][j]] = true;
                        }

                        // Move right
                        if (j + 1 < n) {
                            dp[i][j + 1][v ^ grid[i][j + 1]] = true;
                        }
                    }
                }
            }
        }

        // Find the minimum XOR value
        for (int v = 0; v < 1024; v++) {
            if (dp[m - 1][n - 1][v]) {
                return v;
            }
        }

        return 0;
    }
}
