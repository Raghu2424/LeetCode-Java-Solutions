class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int incIndex = -1;
        int decIndex = -1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) incIndex = i;
            if (nums[i] == n - 1) decIndex = i;
        }

        boolean increasing = incIndex != -1;
        boolean decreasing = decIndex != -1;

        for (int i = 0; i < n; i++) {
            if (nums[(incIndex + i) % n] != i) {
                increasing = false;
            }

            if (nums[(decIndex + i) % n] != n - 1 - i) {
                decreasing = false;
            }

            if (!increasing && !decreasing) return -1;
        }

        int minOps = Integer.MAX_VALUE;

        if (increasing) {
            minOps = Math.min(minOps, incIndex);
            minOps = Math.min(minOps, 2 + (n - incIndex) % n);
        }

        if (decreasing) {
            minOps = Math.min(minOps, 1 + (n - decIndex) % n);
            minOps = Math.min(minOps, 1 + decIndex);
        }

        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}
