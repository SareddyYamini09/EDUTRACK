package m5_np;

import java.util.*;

// Input: Undirected graph G = (V, E)
// Algorithm: Maximal Matching 2-Approximation Algorithm for Vertex Cover (Gavril & Yannakakis)
// Output: Vertex cover C such that |C| <= 2 * |C_optimal|
// Time Complexity: O(V + E)
// Space Complexity: O(V + E)
// EduTrack Application: Fast approximation of minimum exam invigilators or conflicting course relocations
public class VertexCoverApproximation {

    public static class ApproxResult {
        public final Set<String> vertexCover;
        public final List<String[]> maximalMatching;
        public final int approxSize;

        public ApproxResult(Set<String> vertexCover, List<String[]> maximalMatching) {
            this.vertexCover = vertexCover;
            this.maximalMatching = maximalMatching;
            this.approxSize = vertexCover.size();
        }
    }

    /**
     * Computes a 2-approximation of the Minimum Vertex Cover using maximal matching.
     */
    public static ApproxResult compute2Approximation(List<String> vertices, List<String[]> edges) {
        Set<String> cover = new LinkedHashSet<>();
        List<String[]> matching = new ArrayList<>();
        Set<String> coveredVertices = new HashSet<>();

        for (String[] edge : edges) {
            String u = edge[0];
            String v = edge[1];

            // If neither endpoint is already covered, take this edge into our maximal matching
            if (!coveredVertices.contains(u) && !coveredVertices.contains(v)) {
                matching.add(edge);
                cover.add(u);
                cover.add(v);
                coveredVertices.add(u);
                coveredVertices.add(v);
            }
        }

        return new ApproxResult(cover, matching);
    }

    /**
     * Demonstrates 2-approximation on an examination conflict graph.
     */
    public static void demonstrate() {
        System.out.println("\n=== Vertex Cover 2-Approximation Demonstration ===");
        System.out.println("Algorithm: Maximal-Matching-based 2-Approximation (Polynomial time).");
        System.out.println("Guarantee: |Cover_approx| <= 2 * |Cover_optimal|.");

        List<String> examHalls = Arrays.asList("Hall-A", "Hall-B", "Hall-C", "Hall-D", "Hall-E", "Hall-F");
        List<String[]> conflictEdges = new ArrayList<>();
        // Star-like and cycle graph with known optimum
        conflictEdges.add(new String[]{"Hall-A", "Hall-B"});
        conflictEdges.add(new String[]{"Hall-A", "Hall-C"});
        conflictEdges.add(new String[]{"Hall-A", "Hall-D"});
        conflictEdges.add(new String[]{"Hall-B", "Hall-E"});
        conflictEdges.add(new String[]{"Hall-C", "Hall-F"});

        System.out.println("\nExam Hall Conflict Graph:");
        System.out.println("Vertices: " + examHalls);
        System.out.println("Conflict Edges (Total " + conflictEdges.size() + "):");
        for (String[] e : conflictEdges) {
            System.out.printf("  (%s <---> %s)%n", e[0], e[1]);
        }

        ApproxResult res = compute2Approximation(examHalls, conflictEdges);

        System.out.println("\nSelected Maximal Matching (Disjoint Edges):");
        for (String[] m : res.maximalMatching) {
            System.out.printf("  * Edge [%s -- %s] -> Both endpoints added to cover%n", m[0], m[1]);
        }

        System.out.println("\nApproximate Vertex Cover C: " + res.vertexCover);
        System.out.println("Approximate Cover Size:     " + res.approxSize);
        System.out.println("Known Optimum Cover Size:   3 (e.g. {Hall-A, Hall-E, Hall-F})");
        System.out.printf("Approximation Ratio:        %.2f <= 2.00 (Provably within theoretical factor 2)%n",
                (double) res.approxSize / 3.0);
        System.out.println("Note: This is a fast polynomial-time approximation, not guaranteed to be exact minimum.");
        System.out.println("Complexity: O(V + E) = O(" + examHalls.size() + " + " + conflictEdges.size() + ")");
    }
}
