import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // 1. Store knowledge in a hash map for O(1) lookup time
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder key = new StringBuilder();
        boolean insideBracket = false;

        // 2. Iterate through s to build the evaluated string
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                insideBracket = true;
            } else if (c == ')') {
                insideBracket = false;
                String keyStr = key.toString();
                // Replace with value if present, else '?'
                result.append(map.getOrDefault(keyStr, "?"));
                key.setLength(0); // Clear key buffer
            } else if (insideBracket) {
                key.append(c);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}
