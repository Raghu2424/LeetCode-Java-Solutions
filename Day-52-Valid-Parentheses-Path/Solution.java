class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;

        int pathLength = m + n - 1;

        // Valid parentheses string must have even length.
        if (pathLength % 2 != 0) {
            return false;
        }

        // Must start with '(' and end with ')'.
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        memo = new Boolean[m][n][(m + n) / 2 + 1];

        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int open) {
        // Update balance.
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        // Balance cannot become negative.
        if (open < 0) {
            return false;
        }

        // Not enough remaining cells to close all open brackets.
        int remaining = (m - r - 1) + (n - c - 1);
        if (open > remaining) {
            return false;
        }

        // Reached destination.
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        // Check memoized result.
        if (memo[r][c][open] != null) {
            return memo[r][c][open];
        }

        boolean found = false;

        // Move down.
        if (r + 1 < m) {
            found = dfs(grid, r + 1, c, open);
        }

        // Move right.
        if (!found && c + 1 < n) {
            found = dfs(grid, r, c + 1, open);
        }

        return memo[r][c][open] = found;
    }
}
