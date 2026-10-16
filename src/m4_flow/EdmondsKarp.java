package m4_flow;

import java.util.*;

// Input: Flow network G = (V, E) with source s, sink t, and edge capacities
// Algorithm: Edmonds-Karp Algorithm (BFS Shortest Augmenting Paths)
// Output: Maximum network flow, step-by-step BFS paths, and bottlenecks
// Time Complexity: O(V * E^2)
// Space Complexity: O(V^2) for capacity/residual representations
// EduTrack Application: Laboratory workstation and hardware equipment distribution across classes
public class EdmondsKarp {

    public static class EKResult {
        public final int maxFlow;
        public final List<String> bfsPaths;
        public final int[][] flowMatrix;

        public EKResult(int maxFlow, List<String> bfsPaths, int[][] flowMatrix) {
            this.maxFlow = maxFlow;
            this.bfsPaths = bfsPaths;
            this.flowMatrix = flowMatrix;
        }
    }

    /**
     * Solves maximum flow using Edmonds-Karp with BFS.
     */
    public static EKResult computeMaxFlow(int[][] capacity, int s, int t, String[] nodeNames) {
        int V = capacity.length;
        int[][] residual = new int[V][V];
        for (int i = 0; i < V; i++) {
            System.arraycopy(capacity[i], 0, residual[i], 0, V);
        }

        int maxFlow = 0;
        int step = 1;
        List<String> paths = new ArrayList<>();
        int[] parent = new int[V];

        while (bfs(residual, s, t, parent)) {
            // Find bottleneck capacity along the BFS path
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residual[u][v]);
            }

            // Build path string
            List<String> pathNodes = new ArrayList<>();
            for (int v = t; v != s; v = parent[v]) {
                pathNodes.add(0, (nodeNames != null && v < nodeNames.length) ? nodeNames[v] : String.valueOf(v));
            }
            pathNodes.add(0, (nodeNames != null && s < nodeNames.length) ? nodeNames[s] : String.valueOf(s));
            String pathStr = String.join(" -> ", pathNodes);

            // Update residual network
            for (int v = t; v != s; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            maxFlow += pathFlow;
            paths.add(String.format("Step #%d: BFS Path [%s] | Bottleneck: %d | Flow: %d",
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

        return new EKResult(maxFlow, paths, flow);
    }

    private static boolean bfs(int[][] residual, int s, int t, int[] parent) {
        int V = residual.length;
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == t) return true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return false;
    }

    /**
     * Demonstrates Edmonds-Karp algorithm on academic hardware resource flow.
     */
    public static void demonstrate() {
        System.out.println("\n=== Edmonds-Karp BFS Network Flow Demonstration ===");
        String[] nodes = {"Server-Source", "Lab-101", "Lab-202", "Section-A", "Section-B", "Student-Terminals"};
        int V = nodes.length;
        int[][] cap = new int[V][V];

        cap[0][1] = 40; // Server -> Lab-101
        cap[0][2] = 50; // Server -> Lab-202
        cap[1][2] = 10; // Cross-link Lab-101 -> Lab-202
        cap[1][3] = 30; // Lab-101 -> Section-A
        cap[2][4] = 45; // Lab-202 -> Section-B
        cap[3][5] = 25; // Section-A -> Terminals
        cap[4][5] = 40; // Section-B -> Terminals
        cap[3][4] = 15; // Section-A -> Section-B overflow

        EKResult res = computeMaxFlow(cap, 0, 5, nodes);

        System.out.println("BFS Augmenting Paths:");
        for (String p : res.bfsPaths) {
            System.out.println("  " + p);
        }

        System.out.println("\nTotal Workstations Allocated (Max Flow): " + res.maxFlow);
        System.out.println("Complexity: O(V * E^2) = O(" + V + " * " + (8 * 8) + ")");
    }
}
