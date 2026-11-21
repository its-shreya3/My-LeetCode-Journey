import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree for ancestor lookup
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Pass 1: Determine new parent for each node via DFS + backtracking
        reassignParents(0, s, origTree, parent, newParent, lastSeen);

        // Build modified tree using newParent
        List<Integer>[] modTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            modTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            modTree[newParent[i]].add(i);
        }

        // Pass 2: Compute subtree sizes in the final tree
        int[] answer = new int[n];
        computeSizes(0, modTree, answer);
        return answer;
    }

    private void reassignParents(
            int u,
            String s,
            List<Integer>[] origTree,
            int[] parent,
            int[] newParent,
            int[] lastSeen) {
        int c = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[c];

        if (u > 0) {
            newParent[u] = (prevAncestor != -1) ? prevAncestor : parent[u];
        }

        lastSeen[c] = u;
        for (int child : origTree[u]) {
            reassignParents(child, s, origTree, parent, newParent, lastSeen);
        }
        lastSeen[c] = prevAncestor; // backtrack so sibling branches stay isolated
    }

    private int computeSizes(int u, List<Integer>[] modTree, int[] answer) {
        int size = 1;
        for (int child : modTree[u]) {
            size += computeSizes(child, modTree, answer);
        }
        answer[u] = size;
        return size;
    }
}
