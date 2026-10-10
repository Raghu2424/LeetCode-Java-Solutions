class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalK = (long) k1 + k2;

        long[] count = new long[100001];
        long maxDiff = 0;

        for (int i = 0; i < n; i++) {
            long diff = Math.abs((long) nums1[i] - nums2[i]);
            count[(int) diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }

        // Greedily reduce the largest differences
        for (long d = maxDiff; d > 0 && totalK > 0; d--) {
            if (count[(int) d] == 0) {
                continue;
            }

            long take = Math.min(totalK, count[(int) d]);
            count[(int) d] -= take;
            count[(int) (d - 1)] += take;
            totalK -= take;
        }

        // Calculate the minimum sum of squared differences
        long ans = 0;

        for (int d = 1; d <= maxDiff; d++) {
            if (count[d] > 0) {
                ans += count[d] * (long) d * d;
            }
        }

        return ans;
    }
}
