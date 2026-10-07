import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        int l = 0, r = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') l++;
            else if (c == ')' && l > 0) l--;
            else if (c == ')') r++;
        }

        dfs(s, 0, 0, 0, l, r, new StringBuilder(), res);
        return new ArrayList<>(res);
    }

    void dfs(String s, int i, int open, int close,
             int l, int r, StringBuilder sb, Set<String> res) {
        if (i == s.length()) {
            if (l == 0 && r == 0) res.add(sb.toString());
            return;
        }

        char c = s.charAt(i);
        int n = sb.length();

        if (c == '(' && l > 0)
            dfs(s, i + 1, open, close, l - 1, r, sb, res);

        if (c == ')' && r > 0)
            dfs(s, i + 1, open, close, l, r - 1, sb, res);

        sb.append(c);

        if (c != '(' && c != ')' || c == '(')
            dfs(s, i + 1, open + (c == '(' ? 1 : 0), close,
                l, r, sb, res);
        else if (open > close)
            dfs(s, i + 1, open, close + 1, l, r, sb, res);

        sb.setLength(n);
    }
}
