class Solution {
    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int closedIslands = 0;
        
        // 1. Mark all islands connected to the grid boundaries as visited (change 0 to 1)
        for (int r = 0; r < rows; r++) {
            if (grid[r][0] == 0) dfs(grid, r, 0);
            if (grid[r][cols - 1] == 0) dfs(grid, r, cols - 1);
        }
        for (int c = 0; c < cols; c++) {
            if (grid[0][c] == 0) dfs(grid, 0, c);
            if (grid[rows - 1][c] == 0) dfs(grid, rows - 1, c);
        }
        
        // 2. Count the remaining islands, which are guaranteed to be closed
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                if (grid[r][c] == 0) {
                    dfs(grid, r, c);
                    closedIslands++;
                }
            }
        }
        
        return closedIslands;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 0) {
            return;
        }
        
        // Mark the current cell as water to avoid revisiting
        grid[r][c] = 1;
        
        // Explore all 4 directions
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
}
