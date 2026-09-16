class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007L;
        long N = n + k - 1;
        long R = 2L * k;

        if (R > N) return 0;

        long num = 1;
        long den = 1;

        for (long i = 1; i <= R; i++) {
            num = (num * (N - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        return (int) ((num * power(den, MOD - 2, MOD)) % MOD);
    }

    private long power(long base, long exp, long mod) {
        long result = 1;
        base %= mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }

            base = (base * base) % mod;
            exp >>= 1;
        }

        return result;
    }
}
