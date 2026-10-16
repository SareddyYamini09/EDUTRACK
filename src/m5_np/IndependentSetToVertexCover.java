package m5_np;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

// Input: Graph G = (V, E) and an Independent Set S of size k
// Algorithm: Complement Set Reduction from INDEPENDENT SET to VERTEX COVER (Karp 1972)
// Output: Vertex Cover C = V \ S of size |V| - k covering all edges in E
// Time Complexity: O(V + E)
// Space Complexity: O(V)
// EduTrack Application: Deriving minimum examination invigilator coverage from student conflict-free schedules
public class IndependentSetToVertexCover {

    /**
     * Given graph vertices V and an independent set S, derives the vertex cover C = V \ S.
     */
    public static Set<String> deriveVertexCover(List<String> allVertices, Set<String> independentSet) {
        Set<String> vertexCover = new LinkedHashSet<>(allVertices);
        vertexCover.removeAll(independentSet);
        return vertexCover;
    }

    /**
     * Verifies whether a candidate subset C is indeed a valid vertex cover of graph edges.
     */
    public static boolean verifyVertexCover(Set<String> cover, List<String[]> edges) {
        for (String[] edge : edges) {
            String u = edge[0];
            String v = edge[1];
            if (!cover.contains(u) && !cover.contains(v)) {
                return false; // Edge is uncovered
            }
        }
        return true;
    }

    /**
     * Demonstrates the structural duality: Independent Set <=> Vertex Cover.
     */
    public static void demonstrate() {
        System.out.println("\n=== NP-Reduction: INDEPENDENT SET ---> VERTEX COVER Demonstration ===");
        System.out.println("Fundamental Duality Theorem:");
        System.out.println("  In any graph G = (V, E), a subset S is an INDEPENDENT SET");
        System.out.println("  IF AND ONLY IF its complement C = (V \\ S) is a VERTEX COVER.");

        List<String> vertices = List.of("Exam-DSA", "Exam-AI", "Exam-DBMS", "Exam-OS", "Exam-CN");
        List<String[]> conflictEdges = List.of(
                new String[]{"Exam-DSA", "Exam-AI"},
                new String[]{"Exam-DSA", "Exam-OS"},
                new String[]{"Exam-AI", "Exam-DBMS"},
                new String[]{"Exam-DBMS", "Exam-OS"},
                new String[]{"Exam-OS", "Exam-CN"}
        );

        System.out.println("\nAcademic Examination Conflict Graph G = (V, E):");
        System.out.println("All Vertices |V| = " + vertices.size() + " : " + vertices);
        System.out.println("Conflict Edges: ");
        for (String[] e : conflictEdges) {
            System.out.printf("  (%s <---> %s)%n", e[0], e[1]);
        }

        // Example independent set: Exams with no shared students (can be scheduled simultaneously)
        Set<String> independentSet = new LinkedHashSet<>(List.of("Exam-AI", "Exam-OS")); // Wait, check: Exam-AI - Exam-OS edge? No edge between AI and OS!
        // Wait, check conflictEdges:
        // DSA-AI, DSA-OS, AI-DBMS, DBMS-OS, OS-CN.
        // Pair (AI, OS): edge? No! But CN with AI? (Exam-AI, Exam-CN) is independent!
        Set<String> validIS = new LinkedHashSet<>(List.of("Exam-AI", "Exam-CN"));
        // Edges: DSA-AI (has AI), DSA-OS (neither!), AI-DBMS (has AI), DBMS-OS (neither!), OS-CN (has CN)
        // Wait: Is validIS an independent set? Between AI and CN, is there an edge? No edge! So validIS is indeed an independent set.

        System.out.println("\nIndependent Set S (Simultaneous Conflict-Free Exams, size k = " + validIS.size() + "):");
        System.out.println("  S = " + validIS);

        Set<String> vertexCover = deriveVertexCover(vertices, validIS);
        System.out.println("\nDerived Vertex Cover C = V \\ S (Size = |V| - k = " + vertexCover.size() + "):");
        System.out.println("  C = " + vertexCover);

        boolean valid = verifyVertexCover(vertexCover, conflictEdges);
        System.out.println("\nEdge Verification: All conflict edges covered by C? -> " + (valid ? "YES (100% COVERED)" : "NO"));
        System.out.println("Reduction Property: |S| + |C| = " + validIS.size() + " + " + vertexCover.size() + " = " + vertices.size() + " = |V|");
        System.out.println("Complexity: O(V + E)");
    }
}
