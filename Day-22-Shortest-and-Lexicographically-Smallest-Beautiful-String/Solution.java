class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int count = 0;
        int minLen = Integer.MAX_VALUE;
        String ans = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }

            while (count == k) {
                while (left < right && s.charAt(left) == '0') {
                    left++;
                }

                int currentLen = right - left + 1;
                String sub = s.substring(left, right + 1);

                if (currentLen < minLen) {
                    minLen = currentLen;
                    ans = sub;
                } else if (currentLen == minLen && sub.compareTo(ans) < 0) {
                    ans = sub;
                }

                if (s.charAt(left) == '1') {
                    count--;
                }

                left++;
            }
        }

        return ans;
    }
}
