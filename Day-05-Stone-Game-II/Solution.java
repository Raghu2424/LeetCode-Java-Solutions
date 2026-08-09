import java.util.Arrays;

class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[][] memo = new int[n][n + 1];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Suffix sums
        int[] suffixSum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return helper(0, 1, piles, suffixSum, memo);
    }

    private int helper(int i, int M, int[] piles,
                       int[] suffixSum, int[][] memo) {

        int n = piles.length;

        if (i >= n) {
            return 0;
        }

        // Take all remaining piles
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        int maxStones = 0;

        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);

            int currentStones =
                suffixSum[i] -
                helper(i + X, nextM, piles, suffixSum, memo);

            maxStones = Math.max(maxStones, currentStones);
        }

        return memo[i][M] = maxStones;
    }
}
