class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int min = 0, max = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[min]) min = i;
            if (nums[i] > nums[max]) max = i;
        }

        int left = Math.min(min, max);
        int right = Math.max(min, max);

        int front = right + 1;
        int back = n - left;
        int both = left + 1 + n - right;

        return Math.min(front, Math.min(back, both));
    }
}
