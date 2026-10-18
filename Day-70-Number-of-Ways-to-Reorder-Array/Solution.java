import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Precompute Pascal's triangle for combinations C(n, k)
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;

            for (int j = 1; j <= i; j++) {
                comb[i][j] =
                    (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> arr) {
        if (arr.size() <= 2) {
            return 1;
        }

        int root = arr.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < arr.size(); i++) {
            int val = arr.get(i);

            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long waysLeft = dfs(left);
        long waysRight = dfs(right);

        // Choose positions for left elements among all remaining spots
        long merges = comb[left.size() + right.size()][left.size()];

        return (((merges * waysLeft) % MOD) * waysRight) % MOD;
    }
}
