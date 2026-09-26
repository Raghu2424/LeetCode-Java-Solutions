import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder currentKey = new StringBuilder();
        boolean insideBracket = false;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                insideBracket = true;
                currentKey.setLength(0);
            } else if (c == ')') {
                insideBracket = false;
                String key = currentKey.toString();
                result.append(map.getOrDefault(key, "?"));
            } else {
                if (insideBracket) {
                    currentKey.append(c);
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
