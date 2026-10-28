class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][][] dp = new boolean[m][n][1024];

        // Base case: include the starting cell's value.
        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int xorVal = 0; xorVal < 1024; xorVal++) {
                    if (!dp[i][j][xorVal]) {
                        continue;
                    }

                    // Move right
                    if (j + 1 < n) {
                        dp[i][j + 1][xorVal ^ grid[i][j + 1]] = true;
                    }

                    // Move down
                    if (i + 1 < m) {
                        dp[i + 1][j][xorVal ^ grid[i + 1][j]] = true;
                    }
                }
            }
        }

        // Find the minimum reachable XOR value.
        for (int xorVal = 0; xorVal < 1024; xorVal++) {
            if (dp[m - 1][n - 1][xorVal]) {
                return xorVal;
            }
        }

        return 0;
    }
}
