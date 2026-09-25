import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Object> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '{') {
                stack.push('{');
            } else if (c == '}') {
                // Collect and union all terms inside the current brace level
                List<Set<String>> unionTerms = new ArrayList<>();
                while (!stack.isEmpty() && !stack.peek().equals('{')) {
                    if (stack.peek().equals(',')) {
                        stack.pop(); // remove comma
                    } else {
                        unionTerms.add((Set<String>) stack.pop());
                    }
                }
                stack.pop(); // remove '{'
                
                // Union of all sets in unionTerms
                Set<String> combinedSet = new HashSet<>();
                for (Set<String> set : unionTerms) {
                    combinedSet.addAll(set);
                }
                
                // After closing a brace, check if we need to concatenate with previous term
                pushAndConcatenate(stack, combinedSet);
            } else if (c == ',') {
                stack.push(',');
            } else {
                // Character 'a'..'z'
                Set<String> set = new HashSet<>();
                set.add(String.valueOf(c));
                pushAndConcatenate(stack, set);
            }
        }
        
        // Combine all remaining top-level union parts
        Set<String> resultSet = new HashSet<>();
        while (!stack.isEmpty()) {
            if (!stack.peek().equals(',')) {
                resultSet.addAll((Set<String>) stack.pop());
            } else {
                stack.pop();
            }
        }
        
        // Sort results lexicographically
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }
    
    private void pushAndConcatenate(Stack<Object> stack, Set<String> nextSet) {
        // If top of stack is a set (not '{' or ','), perform concatenation (Cartesian product)
        if (!stack.isEmpty() && stack.peek() instanceof Set) {
            Set<String> prevSet = (Set<String>) stack.pop();
            Set<String> concatenated = new HashSet<>();
            for (String s1 : prevSet) {
                for (String s2 : nextSet) {
                    concatenated.add(s1 + s2);
                }
            }
            stack.push(concatenated);
        } else {
            stack.push(nextSet);
        }
    }
}
