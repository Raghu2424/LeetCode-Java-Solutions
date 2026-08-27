class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';

            if (cnt[x] > 0) {
                cnt[x]--;
                prefix.append(target.charAt(i));
            } else {
                for (int c = x + 1; c < 26; c++) {
                    if (cnt[c] > 0) {
                        StringBuilder ans = new StringBuilder(prefix);
                        ans.append((char) ('a' + c));
                        cnt[c]--;
                        return build(ans, cnt);
                    }
                }

                for (int j = i - 1; j >= 0; j--) {
                    int old = target.charAt(j) - 'a';
                    cnt[old]++;

                    for (int c = old + 1; c < 26; c++) {
                        if (cnt[c] > 0) {
                            StringBuilder ans =
                                new StringBuilder(target.substring(0, j));
                            ans.append((char) ('a' + c));
                            cnt[c]--;
                            return build(ans, cnt);
                        }
                    }
                }

                return "";
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            int x = target.charAt(i) - 'a';
            cnt[x]++;

            for (int c = x + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    StringBuilder ans =
                        new StringBuilder(target.substring(0, i));
                    ans.append((char) ('a' + c));
                    cnt[c]--;
                    return build(ans, cnt);
                }
            }
        }

        return "";
    }

    private String build(StringBuilder ans, int[] cnt) {
        for (int c = 0; c < 26; c++) {
            while (cnt[c] > 0) {
                ans.append((char) ('a' + c));
                cnt[c]--;
            }
        }
        return ans.toString();
    }
}
