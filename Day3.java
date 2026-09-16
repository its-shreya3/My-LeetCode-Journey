class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        long N = n + k - 1;
        long K = 2 * k;

        if (K > N) return 0;

        long numerator = 1;
        long denominator = 1;

        for (int i = 1; i <= K; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }

        return (int) ((numerator * modInverse(denominator, MOD)) % MOD);
    }

    private long modInverse(long a, int m) {
        return power(a, m - 2, m);
    }

    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
