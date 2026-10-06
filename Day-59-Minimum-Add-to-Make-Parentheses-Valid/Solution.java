class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int addNeeded = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openNeeded++;
            } else {
                if (openNeeded > 0) {
                    openNeeded--;
                } else {
                    addNeeded++;
                }
            }
        }

        return openNeeded + addNeeded;
    }
}
