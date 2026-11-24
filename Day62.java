class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            long prod = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {
                int val = nums[j];
                gcd = (j == i) ? val : gcd(gcd, val);
                lcm = lcm(lcm, val);

                // Guard against overflow during multiplication check
                if (prod > Long.MAX_VALUE / val) {
                    break;
                }
                prod *= val;

                if (prod == (long) lcm * gcd) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }
}
