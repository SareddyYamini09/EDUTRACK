package m4_flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Input: Flow network G = (V, E) with source s, sink t, and capacity matrix cap[u][v]
// Algorithm: Ford-Fulkerson Method using DFS on Residual Graph
// Output: Maximum network flow value, step-by-step augmenting paths, and residual capacity matrix
// Time Complexity: O(E * max_flow)
// Space Complexity: O(V^2) for capacity/residual matrix
// EduTrack Application: Campus classroom and lab capacity allocation across departments and student groups
public class FordFulkerson {

    public static class FlowResult {
        public final int maxFlow;
        public final List<String> augmentingPaths;
        public final int[][] flowMatrix;

        public FlowResult(int maxFlow, List<String> augmentingPaths, int[][] flowMatrix) {
            this.maxFlow = maxFlow;
            this.augmentingPaths = augmentingPaths;
            this.flowMatrix = flowMatrix;
        }
    }

    /**
     * Solves maximum flow using Ford-Fulkerson with DFS on the residual network.
     */
    public static FlowResult computeMaxFlow(int[][] capacity, int s, int t, String[] nodeNames) {
        int V = capacity.length;
        int[][] residual = new int[V][V];
        for (int i = 0; i < V; i++) {
            System.arraycopy(capacity[i], 0, residual[i], 0, V);
        }

        int maxFlow = 0;
        int step = 1;
        List<String> paths = new ArrayList<>();
        int[] parent = new int[V];

        while (dfs(residual, s, t, new boolean[V], parent)) {
            // Find bottleneck capacity along the augmenting path
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            // Construct path string for display
            List<String> pathNodes = new ArrayList<>();
            for (int v = t; v != s; v = parent[v]) {
                pathNodes.add(0, (nodeNames != null && v < nodeNames.length) ? nodeNames[v] : String.valueOf(v));
            }
            pathNodes.add(0, (nodeNames != null && s < nodeNames.length) ? nodeNames[s] : String.valueOf(s));
            String pathStr = String.join(" -> ", pathNodes);

            // Augment flow along path and update residual capacities
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
            paths.add(String.format("Augmentation #%d: Path [%s] with Bottleneck %d | Cumulative Flow: %d",
                    step++, pathStr, pathFlow, maxFlow));
        }

        int[][] flow = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (capacity[i][j] > 0) {
                    flow[i][j] = capacity[i][j] - residual[i][j];
                }
            }
        }

        return new FlowResult(maxFlow, paths, flow);
    }

    private static boolean dfs(int[][] residual, int u, int t, boolean[] visited, int[] parent) {
        visited[u] = true;
        if (u == t) return true;

        for (int v = 0; v < residual.length; v++) {
            if (!visited[v] && residual[u][v] > 0) {
                parent[v] = u;
                if (dfs(residual, v, t, visited, parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Demonstrates Ford-Fulkerson on university resource allocation.
     */
    public static void demonstrate() {
        System.out.println("\n=== Ford-Fulkerson Classroom Allocation Demonstration ===");
        // Network: Source (0) -> Dept-CSE (1), Dept-AI (2) -> Lab-A (3), Lab-B (4) -> Sink (5)
        String[] nodes = {"Source", "Dept-CSE", "Dept-AI", "Hall-A (Cap 120)", "Hall-B (Cap 80)", "Allocated-Sink"};
        int V = nodes.length;
        int[][] cap = new int[V][V];

        cap[0][1] = 100; // Source -> CSE requests 100 seats
        cap[0][2] = 90;  // Source -> AI requests 90 seats
        cap[1][3] = 70;  // CSE -> Hall-A can take 70
        cap[1][4] = 40;  // CSE -> Hall-B can take 40
        cap[2][3] = 50;  // AI -> Hall-A can take 50
        cap[2][4] = 60;  // AI -> Hall-B can take 60
        cap[3][5] = 110; // Hall-A -> Sink capacity 110
        cap[4][5] = 80;  // Hall-B -> Sink capacity 80

        System.out.println("Network Node Hierarchy:");
        for (int i = 0; i < V; i++) {
            System.out.printf("  [%d] %s%n", i, nodes[i]);
        }

        FlowResult res = computeMaxFlow(cap, 0, 5, nodes);

        System.out.println("\nAugmentation Steps:");
        for (String p : res.augmentingPaths) {
            System.out.println("  " + p);
        }

        System.out.println("\nMaximum Total Seats Allocated: " + res.maxFlow);
        System.out.println("Complexity: O(E * max_flow)");
    }
}
