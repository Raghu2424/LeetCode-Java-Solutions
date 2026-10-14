class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num || sum < 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < num; i++) {
            int upperBound = Math.min(9, sum);

            result.append(upperBound);
            sum -= upperBound;
        }

        return result.toString();
    }
}
