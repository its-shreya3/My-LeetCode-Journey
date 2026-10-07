import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // Calculate minimum number of invalid '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // Matches a previous '('
                } else {
                    rightRem++; // Unmatched ')'
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(
        String s, 
        int index, 
        int openCount, 
        int closeCount, 
        int leftRem, 
        int rightRem, 
        StringBuilder path, 
        Set<String> result
    ) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && openCount == closeCount) {
                result.add(path.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int length = path.length();

        // Option 1: Remove current character if it's a parenthesis and removals are remaining
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, openCount, closeCount, leftRem - 1, rightRem, path, result);
        } else if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, openCount, closeCount, leftRem, rightRem - 1, path, result);
        }

        // Option 2: Keep current character
        path.append(currentChar);

        if (currentChar != '(' && currentChar != ')') {
            // Regular character, simply continue
            backtrack(s, index + 1, openCount, closeCount, leftRem, rightRem, path, result);
        } else if (currentChar == '(') {
            backtrack(s, index + 1, openCount + 1, closeCount, leftRem, rightRem, path, result);
        } else if (currentChar == ')' && openCount > closeCount) {
            // Only keep ')' if there is a matching '(' prior to it
            backtrack(s, index + 1, openCount, closeCount + 1, leftRem, rightRem, path, result);
        }

        // Backtrack
        path.setLength(length);
    }
}import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // Calculate minimum number of invalid '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // Matches a previous '('
                } else {
                    rightRem++; // Unmatched ')'
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(
        String s, 
        int index, 
        int openCount, 
        int closeCount, 
        int leftRem, 
        int rightRem, 
        StringBuilder path, 
        Set<String> result
    ) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && openCount == closeCount) {
                result.add(path.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int length = path.length();

        // Option 1: Remove current character if it's a parenthesis and removals are remaining
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, openCount, closeCount, leftRem - 1, rightRem, path, result);
        } else if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, openCount, closeCount, leftRem, rightRem - 1, path, result);
        }

        // Option 2: Keep current character
        path.append(currentChar);

        if (currentChar != '(' && currentChar != ')') {
            // Regular character, simply continue
            backtrack(s, index + 1, openCount, closeCount, leftRem, rightRem, path, result);
        } else if (currentChar == '(') {
            backtrack(s, index + 1, openCount + 1, closeCount, leftRem, rightRem, path, result);
        } else if (currentChar == ')' && openCount > closeCount) {
            // Only keep ')' if there is a matching '(' prior to it
            backtrack(s, index + 1, openCount, closeCount + 1, leftRem, rightRem, path, result);
        }

        // Backtrack
        path.setLength(length);
    }
}
