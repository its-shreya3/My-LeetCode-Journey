class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 so we can use binary search to efficiently find the closest elements
        java.util.Arrays.sort(arr2);
        int distanceValue = 0;

        for (int num : arr1) {
            // Find the insertion point of num in arr2 using binary search
            int idx = java.util.Arrays.binarySearch(arr2, num);
            
            boolean isValid = true;
            
            if (idx >= 0) {
                // If an exact match exists in arr2, the absolute difference is 0 <= d
                // (assuming d >= 0), so it violates the condition.
                isValid = false;
            } else {
                // binarySearch returns -(insertion point) - 1 when not found.
                // Convert it back to get the indices of the elements just smaller and larger than num.
                int insertionPoint = -idx - 1;
                
                // Check the element immediately smaller than num, if it exists
                if (insertionPoint > 0) {
                    if (Math.abs(num - arr2[insertionPoint - 1]) <= d) {
                        isValid = false;
                    }
                }
                
                // Check the element immediately larger than num, if it exists
                if (insertionPoint < arr2.length) {
                    if (Math.abs(num - arr2[insertionPoint]) <= d) {
                        isValid = false;
                    }
                }
            }
            
            if (isValid) {
                distanceValue++;
            }
        }

        return distanceValue;
    }
}
