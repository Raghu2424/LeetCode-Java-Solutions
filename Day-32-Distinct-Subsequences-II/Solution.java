class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long sum = 0;

            for (long x : last) {
                sum = (sum + x) % MOD;
            }

            last[idx] = (sum + 1) % MOD;
        }

        long total = 0;
        for (long x : last) {
            total = (total + x) % MOD;
        }

        return (int) total;
    }
}
