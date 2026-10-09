class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int needed = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                if (needed % 2 != 0) {
                    insertions++;
                    needed--;
                }
                needed += 2;
            } else {
                needed--;

                if (needed < 0) {
                    insertions++;
                    needed += 2;
                }
            }
        }

        return insertions + needed;
    }
}
