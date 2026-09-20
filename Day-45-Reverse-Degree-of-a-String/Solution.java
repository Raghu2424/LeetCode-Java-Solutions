class Solution {
    public int reverseDegree(String s) {
        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            // Position in reversed alphabet: a -> 26, b -> 25, ..., z -> 1
            int revAlphaPos = 26 - (s.charAt(i) - 'a');

            // 1-indexed position in the string
            int strPos = i + 1;

            total += revAlphaPos * strPos;
        }

        return total;
    }
}
