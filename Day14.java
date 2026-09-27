import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // Store the current length of StringBuilder as the start index for reversal
                stack.push(sb.length());
            } else if (c == ')') {
                // Pop the start index and reverse the substring in sb from start to current end
                int start = stack.pop();
                reverse(sb, start, sb.length() - 1);
            } else {
                // Append character
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }
}
