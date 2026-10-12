import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[32];

        // Count set bits at each position
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if (((x >> i) & 1) == 1) {
                    bitCount[i]++;
                }
            }
        }

        long totalSum = 0;
        long MOD = 1_000_000_007L;

        // Greedily construct k numbers to maximize the sum of squares
        for (int step = 0; step < k; step++) {
            long currentNum = 0;

            for (int i = 0; i < 32; i++) {
                if (bitCount[i] > 0) {
                    currentNum |= (1L << i);
                    bitCount[i]--;
                }
            }

            totalSum = (totalSum + (currentNum * currentNum) % MOD) % MOD;
        }

        return (int) totalSum;
    }
}
