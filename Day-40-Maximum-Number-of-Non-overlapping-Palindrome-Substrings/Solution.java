class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // dp[i] = maximum number of non-overlapping palindromes
        // using the first i characters
        int[] dp = new int[n + 1];

        // palindrome[i][j] = true if s[i...j] is a palindrome
        boolean[][] palindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            // Do not take a palindrome ending at i-1
            dp[i] = dp[i - 1];

            // Try every possible starting position
            for (int j = 0; j <= i - k; j++) {
                if (palindrome[j][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}
