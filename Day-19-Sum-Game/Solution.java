class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum = 0;
        int q = 0;

        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '?') {
                q += (i < n / 2 ? 1 : -1);
            } else {
                sum += (i < n / 2 ? 1 : -1)
                        * (num.charAt(i) - '0');
            }
        }

        return sum * 2 + q * 9 != 0;
    }
}
