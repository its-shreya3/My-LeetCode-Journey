import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        
        // Direction arrays for moving 4-directionally (up, down, left, right)
        int[] dirX = {-1, 1, 0, 0};
        int[] dirY = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If we find an unvisited piece of land
                if (grid[i][j] > 0) {
                    long currentIslandSum = 0; // Use long to avoid overflow
                    Queue<int[]> queue = new LinkedList<>();
                    
                    queue.offer(new int[]{i, j});
                    currentIslandSum += grid[i][j];
                    grid[i][j] = 0; // Mark as visited by setting it to 0

                    // BFS traversal for the entire island
                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int x = curr[0];
                        int y = curr[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = x + dirX[d];
                            int ny = y + dirY[d];

                            // Check bounds and if it is unvisited land
                            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                                currentIslandSum += grid[nx][ny];
                                grid[nx][ny] = 0; // Mark as visited
                                queue.offer(new int[]{nx, ny});
                            }
                        }
                    }

                    // Check if the total value of the island is divisible by k
                    if (currentIslandSum % k == 0) {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}
