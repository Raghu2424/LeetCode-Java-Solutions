class Solution {
    public int sortPermutation(int[] nums) {
        int maxK = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                maxK = (maxK == -1) ? nums[i] : maxK & nums[i];
            }
        }

        return maxK == -1 ? 0 : maxK;
    }
}
