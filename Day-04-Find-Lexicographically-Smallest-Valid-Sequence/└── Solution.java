class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        int ptr = n - 1;

        // Find positions for matching the suffix of word2.
        for (int i = m - 1; i >= 0; i--) {
            while (ptr >= 0 && word1.charAt(ptr) != word2.charAt(i)) {
                ptr--;
            }

            last[i] = ptr;
            ptr--;
        }

        int[] result = new int[m];
        boolean changed = false;
        int index = 0;

        for (int i = 0; i < m; i++) {
            boolean found = false;

            while (index < n) {

                // Exact character match
                if (word1.charAt(index) == word2.charAt(i)) {
                    result[i] = index;
                    index++;
                    found = true;
                    break;
                }

                // Use the one allowed mismatch
                if (!changed) {
                    boolean suffixPossible =
                        i == m - 1 ||
                        (last[i + 1] != -1 &&
                         index + 1 <= last[i + 1]);

                    if (suffixPossible) {
                        result[i] = index;
                        index++;
                        changed = true;
                        found = true;
                        break;
                    }
                }

                index++;
            }

            if (!found) {
                return new int[0];
            }
        }

        return result;
    }
}
