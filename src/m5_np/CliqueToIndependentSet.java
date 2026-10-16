package m5_np;

import java.util.ArrayList;
import java.util.List;

// Input: Undirected graph G = (V, E) and target size k
// Algorithm: Graph Complement Reduction from CLIQUE to INDEPENDENT SET
// Output: Complement graph G_comp = (V, E_comp) where (u, v) in E_comp iff (u, v) not in E
// Time Complexity: O(V^2)
// Space Complexity: O(V^2)
// EduTrack Application: Transforming cohesive academic research clusters (cliques) to conflict-free exam timeslots (independent sets)
public class CliqueToIndependentSet {

    public static class Graph {
        public final int numVertices;
        public final String[] labels;
        public final boolean[][] adj;

        public Graph(int n, String[] labels) {
            this.numVertices = n;
            this.labels = labels;
            this.adj = new boolean[n][n];
        }

        public void addEdge(int u, int v) {
            if (u >= 0 && u < numVertices && v >= 0 && v < numVertices && u != v) {
                adj[u][v] = true;
                adj[v][u] = true;
            }
        }

        public List<String> getEdgeList() {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                for (int j = i + 1; j < numVertices; j++) {
                    if (adj[i][j]) {
                        list.add("(" + labels[i] + " -- " + labels[j] + ")");
                    }
                }
            }
            return list;
        }
    }

    /**
     * Transforms graph G into its complement graph G_comp.
     */
    public static Graph complement(Graph g) {
        Graph comp = new Graph(g.numVertices, g.labels);
        for (int i = 0; i < g.numVertices; i++) {
            for (int j = i + 1; j < g.numVertices; j++) {
                if (!g.adj[i][j]) {
                    comp.addEdge(i, j);
                }
            }
        }
        return comp;
    }

    /**
     * Demonstrates the polynomial reduction from CLIQUE to INDEPENDENT SET.
     */
    public static void demonstrate() {
        System.out.println("\n=== NP-Reduction: CLIQUE ---> INDEPENDENT SET Demonstration ===");
        System.out.println("Theorem: A subset S is a CLIQUE of size k in graph G");
        System.out.println("         IF AND ONLY IF S is an INDEPENDENT SET of size k in complement(G).");

        String[] courses = {"DSA301", "AI201", "ML301", "OS301", "DBMS201"};
        Graph g = new Graph(courses.length, courses);

        // Suppose DSA301, AI201, ML301 form a 3-clique (mutually shared faculty/resources)
        g.addEdge(0, 1); // DSA301 - AI201
        g.addEdge(1, 2); // AI201 - ML301
        g.addEdge(0, 2); // DSA301 - ML301
        g.addEdge(2, 3); // ML301 - OS301
        g.addEdge(3, 4); // OS301 - DBMS201

        System.out.println("\nOriginal Graph G (Cooperation Graph):");
        System.out.println("Vertices: " + java.util.Arrays.toString(courses));
        System.out.println("Edges in G: " + g.getEdgeList());
        System.out.println("Identified Clique in G: {DSA301, AI201, ML301} of size k = 3");

        Graph comp = complement(g);

        System.out.println("\nComplement Graph G_comp (Non-adjacent conflict graph):");
        System.out.println("Complement Edges: " + comp.getEdgeList());
        System.out.println("Corresponding Independent Set in G_comp: {DSA301, AI201, ML301} of size k = 3");
        System.out.println("Verification: Since all pairs in {DSA, AI, ML} had edges in G, none have edges in G_comp!");
        System.out.println("Complexity: O(V^2) = O(" + (courses.length * courses.length) + ")");
    }
}
