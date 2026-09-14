class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // Check if the 1D projections along x-axis and y-axis overlap
        boolean xOverlap = Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]);
        boolean yOverlap = Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
        
        return xOverlap && yOverlap;
    }
}
