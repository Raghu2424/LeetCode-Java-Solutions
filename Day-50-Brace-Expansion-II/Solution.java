import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parseExpr(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parseExpr(String s) {
        Set<String> resultSet = new HashSet<>();
        List<Set<String>> concatGroups = new ArrayList<>();

        int n = s.length();
        int i = 0;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '{') {
                int open = 1;
                int j = i + 1;

                while (j < n && open > 0) {
                    if (s.charAt(j) == '{') {
                        open++;
                    } else if (s.charAt(j) == '}') {
                        open--;
                    }
                    j++;
                }

                Set<String> inner = parseExpr(s.substring(i + 1, j - 1));
                concatGroups.add(inner);
                i = j;

            } else if (Character.isLetter(c)) {
                Set<String> letterSet = new HashSet<>();
                letterSet.add(String.valueOf(c));
                concatGroups.add(letterSet);
                i++;

            } else if (c == ',') {
                resultSet.addAll(combineCartesianProduct(concatGroups));
                concatGroups.clear();
                i++;
            }
        }

        resultSet.addAll(combineCartesianProduct(concatGroups));

        return resultSet;
    }

    private Set<String> combineCartesianProduct(List<Set<String>> groups) {
        if (groups.isEmpty()) {
            return Collections.emptySet();
        }

        Set<String> current = new HashSet<>();
        current.add("");

        for (Set<String> group : groups) {
            Set<String> next = new HashSet<>();

            for (String prefix : current) {
                for (String suffix : group) {
                    next.add(prefix + suffix);
                }
            }

            current = next;
        }

        return current;
    }
}
