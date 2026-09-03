class Solution {
    public boolean uniformArray(int[] nums1) {
        int minVal = Integer.MAX_VALUE;
        boolean hasOdd = false;
        boolean hasEven = false;

        for (int x : nums1) {
            minVal = Math.min(minVal, x);

            if (x % 2 == 0)
                hasEven = true;
            else
                hasOdd = true;
        }

        if (!hasOdd || !hasEven)
            return true;

        return minVal % 2 != 0;
    }
}
