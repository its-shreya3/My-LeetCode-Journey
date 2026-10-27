class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        // Construct the first row (all free cells '.')
        StringBuilder firstRow = new StringBuilder();
        for (int j = 0; j < n; j++) {
            firstRow.append('.');
        }
        grid[0] = firstRow.toString();
        
        // Construct the remaining rows (all obstacles '#' except the last column)
        if (m > 1) {
            StringBuilder otherRow = new StringBuilder();
            for (int j = 0; j < n - 1; j++) {
                otherRow.append('#');
            }
            otherRow.append('.');
            String otherRowStr = otherRow.toString();
            
            // Fill the rest of the grid
            for (int i = 1; i < m; i++) {
                grid[i] = otherRowStr;
            }
        }
        
        return grid;
    }
}
