class Solution {
    public int longestSubsequence(int[] nums) {
        int xorAll = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xorAll ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        if (xorAll != 0) {
            return nums.length;
        }

        return hasNonZero ? nums.length - 1 : 0;
    }
}
