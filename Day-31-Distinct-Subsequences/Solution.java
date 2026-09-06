class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        double[] dp = new double[m + 1];
        dp[0] = 1;

        for (char c : s.toCharArray()) {
            for (int j = m; j >= 1; j--) {
                if (c == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return (int) dp[m];
    }
}
