class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        String mid = "";

        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                if (!mid.isEmpty()) return "";
                mid = String.valueOf((char) ('a' + i));
            }
            cnt[i] /= 2;
        }

        int h = n / 2;

        // First try the exact first half of target.
        int[] rem = cnt.clone();
        StringBuilder first = new StringBuilder();

        boolean possible = true;

        for (int i = 0; i < h; i++) {
            int x = target.charAt(i) - 'a';

            if (rem[x] == 0) {
                possible = false;
                break;
            }

            rem[x]--;
            first.append(target.charAt(i));
        }

        if (possible) {
            String ans = makePalindrome(first.toString(), mid);

            if (ans.compareTo(target) > 0)
                return ans;
        }

        // Change one position from right to left.
        for (int pos = h - 1; pos >= 0; pos--) {
            rem = cnt.clone();
            StringBuilder prefix = new StringBuilder();
            boolean ok = true;

            // Match target before pos.
            for (int i = 0; i < pos; i++) {
                int x = target.charAt(i) - 'a';

                if (rem[x] == 0) {
                    ok = false;
                    break;
                }

                rem[x]--;
                prefix.append(target.charAt(i));
            }

            if (!ok)
                continue;

            int targetChar = target.charAt(pos) - 'a';

            // Choose the smallest character greater than target[pos].
            for (int c = targetChar + 1; c < 26; c++) {
                if (rem[c] == 0)
                    continue;

                rem[c]--;

                StringBuilder left = new StringBuilder(prefix);
                left.append((char) ('a' + c));

                // Fill remaining half with smallest characters.
                for (int x = 0; x < 26; x++) {
                    while (rem[x] > 0) {
                        left.append((char) ('a' + x));
                        rem[x]--;
                    }
                }

                String ans = makePalindrome(left.toString(), mid);

                if (ans.compareTo(target) > 0)
                    return ans;
            }
        }

        return "";
    }

    private String makePalindrome(String left, String mid) {
        StringBuilder ans = new StringBuilder(left);
        ans.append(mid);

        for (int i = left.length() - 1; i >= 0; i--)
            ans.append(left.charAt(i));

        return ans.toString();
    }
}
