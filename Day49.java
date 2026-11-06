import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        // Store queries along with their original indices
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries and logs by time
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] result = new int[m];
        Map<Integer, Integer> serverCounts = new HashMap<>();
        
        int l = 0, r = 0;
        int numLogs = logs.length;

        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int qIndex = sortedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;

            // Expand window right pointer to include all logs <= windowEnd
            while (r < numLogs && logs[r][1] <= windowEnd) {
                int serverId = logs[r][0];
                serverCounts.put(serverId, serverCounts.getOrDefault(serverId, 0) + 1);
                r++;
            }

            // Shrink window left pointer to exclude logs < windowStart
            while (l < numLogs && logs[l][1] < windowStart) {
                int serverId = logs[l][0];
                serverCounts.put(serverId, serverCounts.get(serverId) - 1);
                if (serverCounts.get(serverId) == 0) {
                    serverCounts.remove(serverId);
                }
                l++;
            }

            // Total servers minus servers with at least one request in the window
            result[qIndex] = n - serverCounts.size();
        }

        return result;
    }
}
