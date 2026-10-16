package m4_flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Input: Network flow graph G = (V, E) with source s, sink t, and capacities
// Algorithm: Dinic's Algorithm using BFS Level Graph and DFS Blocking Flow with pointer advancement
// Output: Maximum network flow value and residual saturation
// Time Complexity: O(V^2 * E) general networks, O(E * sqrt(V)) unit networks
// Space Complexity: O(V + E)
// EduTrack Application: Multi-tier campus resource scheduling (Departments -> Buildings -> Classrooms -> Student Cohorts)
public class Dinic {

    public static class Edge {
        public final int to;
        public final int rev;
        public int cap;
        public int flow;

        public Edge(int to, int rev, int cap) {
            this.to = to;
            this.rev = rev;
            this.cap = cap;
            this.flow = 0;
        }
    }

    private final int n;
    private final List<List<Edge>> adj;
    private final int[] level;
    private final int[] ptr;

    public Dinic(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        this.level = new int[n];
        this.ptr = new int[n];
    }

    public void addEdge(int from, int to, int cap) {
        Edge a = new Edge(to, adj.get(to).size(), cap);
        Edge b = new Edge(from, adj.get(from).size(), 0);
        adj.get(from).add(a);
        adj.get(to).add(b);
    }

    private boolean bfs(int s, int t) {
        Arrays.fill(level, -1);
        level[s] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int v = q.poll();
            for (Edge edge : adj.get(v)) {
                if (edge.cap - edge.flow > 0 && level[edge.to] == -1) {
                    level[edge.to] = level[v] + 1;
                    q.add(edge.to);
                }
            }
        }
        return level[t] != -1;
    }

    private int dfs(int v, int t, int pushed) {
        if (pushed == 0) return 0;
        if (v == t) return pushed;

        for (int cid = ptr[v]; cid < adj.get(v).size(); cid = ++ptr[v]) {
            Edge edge = adj.get(v).get(cid);
            int tr = edge.to;

            if (level[v] + 1 != level[tr] || edge.cap - edge.flow == 0) {
                continue;
            }

            int trPushed = dfs(tr, t, Math.min(pushed, edge.cap - edge.flow));
            if (trPushed == 0) continue;

            edge.flow += trPushed;
            adj.get(tr).get(edge.rev).flow -= trPushed;
            return trPushed;
        }
        return 0;
    }

    public int maxFlow(int s, int t) {
        int flow = 0;
        while (bfs(s, t)) {
            Arrays.fill(ptr, 0);
            while (true) {
                int pushed = dfs(s, t, Integer.MAX_VALUE);
                if (pushed == 0) break;
                flow += pushed;
            }
        }
        return flow;
    }

    /**
     * Demonstrates Dinic's Algorithm on a multi-tier university resource hierarchy.
     */
    public static void demonstrate() {
        System.out.println("\n=== Dinic's Multi-Tier Resource Flow Demonstration ===");
        // Hierarchy:
        // Source (0) -> Dept-CSE (1), Dept-AI (2)
        // Depts -> Block-A (3), Block-B (4)
        // Blocks -> Hall-101 (5), Hall-202 (6), Lab-301 (7)
        // Classrooms/Labs -> Student Groups Sink (8)
        String[] nodeNames = {
                "Source", "Dept-CSE", "Dept-AI",
                "Building Block-A", "Building Block-B",
                "Hall-101", "Hall-202", "Lab-301",
                "Student Groups (Sink)"
        };

        Dinic dinic = new Dinic(nodeNames.length);

        // Source to Departments
        dinic.addEdge(0, 1, 150); // Source -> CSE
        dinic.addEdge(0, 2, 120); // Source -> AI

        // Departments to Buildings
        dinic.addEdge(1, 3, 100); // CSE -> Block-A
        dinic.addEdge(1, 4, 60);  // CSE -> Block-B
        dinic.addEdge(2, 3, 50);  // AI -> Block-A
        dinic.addEdge(2, 4, 80);  // AI -> Block-B

        // Buildings to Classrooms/Labs
        dinic.addEdge(3, 5, 80);  // Block-A -> Hall-101
        dinic.addEdge(3, 6, 70);  // Block-A -> Hall-202
        dinic.addEdge(4, 6, 40);  // Block-B -> Hall-202
        dinic.addEdge(4, 7, 90);  // Block-B -> Lab-301

        // Classrooms/Labs to Sink
        dinic.addEdge(5, 8, 75);  // Hall-101 -> Sink
        dinic.addEdge(6, 8, 100); // Hall-202 -> Sink
        dinic.addEdge(7, 8, 85);  // Lab-301 -> Sink

        System.out.println("Network Tiers:");
        System.out.println("  Tier 0: Central Academic Scheduler (Source)");
        System.out.println("  Tier 1: Departments (CSE, AI)");
        System.out.println("  Tier 2: Campus Buildings (Block-A, Block-B)");
        System.out.println("  Tier 3: Facility Rooms (Hall-101, Hall-202, Lab-301)");
        System.out.println("  Tier 4: Student Attendance Capacity (Sink)");

        int totalMaxFlow = dinic.maxFlow(0, 8);

        System.out.println("\nMaximum Resource Allocation Flow: " + totalMaxFlow);
        System.out.println("Complexity: O(V^2 * E) = O(" + (nodeNames.length * nodeNames.length) + " * 11)");
    }
}
