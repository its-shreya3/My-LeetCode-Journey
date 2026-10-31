import java.util.Arrays;

class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

        // jump[i][j][0] stores the final (r, c) when trying to move right from (i, j)
        // jump[i][j][1] stores the final (r, c) when trying to move down from (i, j)
        int[][][] jump = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(jump[i][j], -1);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Precompute for move right (dir = 0)
                jump[i][j][0] = getDestination(i, j, 0, grid, m, n);
                // Precompute for move down (dir = 1)
                jump[i][j][1] = getDestination(i, j, 1, grid, m, n);
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // Try moving right/jump[0]
                int rightDest = jump[i][j][0];
                if (rightDest != -1) {
                    int nx = rightDest / n;
                    int ny = rightDest % n;
                    dp[nx][ny] = (dp[nx][ny] + dp[i][j]) % MOD;
                }

                // Try moving down/jump[1]
                int downDest = jump[i][j][1];
                if (downDest != -1) {
                    int nx = downDest / n;
                    int ny = downDest % n;
                    dp[nx][ny] = (dp[nx][ny] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    private int getDestination(int r, int c, int dir, int[][] grid, int m, int n) {
        int currR = r;
        int currC = c;
        int currDir = dir; // 0: right, 1: down

        while (true) {
            int nextR = currR + (currDir == 1 ? 1 : 0);
            int nextC = currC + (currDir == 0 ? 1 : 0);

            if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n) {
                return -1; // Out of bounds
            }

            if (grid[nextR][nextC] == 1) {
                // Reflected: if moving right into mirror, turn down. If down, turn right.
                currDir = 1 - currDir;
                currR = nextR;
                currC = nextC;
            } else {
                return nextR * n + nextC;
            }
        }
    }
}
