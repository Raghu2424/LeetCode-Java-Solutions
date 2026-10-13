import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squaredNums = new long[n];

        // Square each element
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squaredNums[i] = val * val;
        }

        // Sort squared values in ascending order
        Arrays.sort(squaredNums);

        long totalScore = 0;
        int left = 0;
        int right = n - 1;
        boolean add = true;

        // Alternate between adding the largest and subtracting the smallest
        while (left <= right) {
            if (add) {
                totalScore += squaredNums[right];
                right--;
            } else {
                totalScore -= squaredNums[left];
                left++;
            }

            add = !add;
        }

        return totalScore;
    }
}
