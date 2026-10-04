class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--;
                maxOpen++;
            }

            // Too many closing brackets.
            if (maxOpen < 0) {
                return false;
            }

            // '*' can represent an empty string.
            minOpen = Math.max(minOpen, 0);
        }

        return minOpen == 0;
    }
}
