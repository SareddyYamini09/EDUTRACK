package m5_np;

import java.util.ArrayList;
import java.util.List;

// Input: 3-CNF formula with k clauses
// Algorithm: Karp's Polynomial-Time Reduction from 3-SAT to CLIQUE (1972)
// Output: Constructed Graph G = (V, E) with 3k vertices, compatibility edges, and target clique size k
// Time Complexity: O(k^2) reduction time
// Space Complexity: O(k^2) graph representation
// EduTrack Application: Proving computational hardness of multi-agent academic scheduling and timetabling
public class ThreeSATToClique {

    public static class Vertex {
        public final int id;
        public final int clauseIndex;
        public final int literal; // e.g., 1 for x1, -2 for !x2

        public Vertex(int id, int clauseIndex, int literal) {
            this.id = id;
            this.clauseIndex = clauseIndex;
            this.literal = literal;
        }

        @Override
        public String toString() {
            return String.format("v%d[C%d:%s]", id, clauseIndex + 1, literal > 0 ? "x" + literal : "!x" + (-literal));
        }
    }

    public static class Edge {
        public final Vertex u;
        public final Vertex v;

        public Edge(Vertex u, Vertex v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public String toString() {
            return "(" + u + " <-> " + v + ")";
        }
    }

    public static class ReductionResult {
        public final List<Vertex> vertices;
        public final List<Edge> edges;
        public final int targetCliqueSize;
        public final int kClauses;

        public ReductionResult(List<Vertex> vertices, List<Edge> edges, int targetCliqueSize, int kClauses) {
            this.vertices = vertices;
            this.edges = edges;
            this.targetCliqueSize = targetCliqueSize;
            this.kClauses = kClauses;
        }
    }

    /**
     * Performs Karp reduction from 3-SAT instance to CLIQUE problem instance.
     */
    public static ReductionResult reduce(List<ThreeSAT.ThreeClause> clauses) {
        int k = clauses.size();
        List<Vertex> vertices = new ArrayList<>();
        int idCounter = 0;

        // Create 3 vertices for each of the k clauses
        for (int c = 0; c < k; c++) {
            ThreeSAT.ThreeClause tc = clauses.get(c);
            vertices.add(new Vertex(idCounter++, c, tc.l1));
            vertices.add(new Vertex(idCounter++, c, tc.l2));
            vertices.add(new Vertex(idCounter++, c, tc.l3));
        }

        // Add edge between two vertices iff:
        // 1. They reside in different clauses (c_i != c_j)
        // 2. They are not contradictory negations (l_i != -l_j)
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                Vertex u = vertices.get(i);
                Vertex v = vertices.get(j);
                if (u.clauseIndex != v.clauseIndex && u.literal != -v.literal) {
                    edges.add(new Edge(u, v));
                }
            }
        }

        return new ReductionResult(vertices, edges, k, k);
    }

    /**
     * Demonstrates 3-SAT to CLIQUE polynomial reduction.
     */
    public static void demonstrate() {
        System.out.println("\n=== NP-Reduction: 3-SAT ---> CLIQUE Demonstration ===");
        System.out.println("Concept: Karp (1972) reduction proving CLIQUE is NP-complete.");
        System.out.println("Theorem: A 3-CNF formula with k clauses is satisfiable IF AND ONLY IF");
        System.out.println("         the reduced graph G has a clique of size >= k.");

        List<ThreeSAT.ThreeClause> clauses = new ArrayList<>();
        // C1: (x1 OR x2 OR x3)
        // C2: (!x1 OR !x2 OR x3)
        // C3: (!x2 OR x3 OR !x4)
        clauses.add(new ThreeSAT.ThreeClause(1, 2, 3));
        clauses.add(new ThreeSAT.ThreeClause(-1, -2, 3));
        clauses.add(new ThreeSAT.ThreeClause(-2, 3, -4));

        System.out.println("\nInput 3-CNF Formula (k = " + clauses.size() + " clauses):");
        for (int i = 0; i < clauses.size(); i++) {
            System.out.printf("  Clause %d: %s%n", i + 1, clauses.get(i));
        }

        ReductionResult res = reduce(clauses);

        System.out.println("\nConstructed Reduction Graph G = (V, E):");
        System.out.println("Total Vertices |V| = 3 * k = " + res.vertices.size());
        System.out.println("Vertices: " + res.vertices);
        System.out.println("Total Edges |E| = " + res.edges.size());
        System.out.println("First 8 Edges: " + res.edges.subList(0, Math.min(8, res.edges.size())) + " ...");
        System.out.println("\nTarget Clique Size k = " + res.targetCliqueSize);
        System.out.println("Reduction Property: Independent choice of 1 non-conflicting literal per clause forms a k-clique.");
        System.out.println("Complexity: O(k^2) polynomial reduction time.");
    }
}
