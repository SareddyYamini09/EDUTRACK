package m4_flow;

import java.util.*;

// Input: Bipartite Graph G = (L, R, E) where L = Faculty, R = Courses, E = Qualification links
// Algorithm: König's Theorem (Construction of Minimum Vertex Cover from Maximum Matching via Alternating Paths)
// Output: Maximum Matching M, Minimum Vertex Cover C, and formal verification that |M| = |C|
// Time Complexity: O(V * E)
// Space Complexity: O(V + E)
// EduTrack Application: Proving minimum oversight committee (faculty or course leads) required to cover all course offerings
public class KonigTheorem {

    public static class KonigResult {
        public final Map<String, String> matching;
        public final Set<String> minVertexCover;
        public final boolean sizeVerified;

        public KonigResult(Map<String, String> matching, Set<String> minVertexCover, boolean sizeVerified) {
            this.matching = matching;
            this.minVertexCover = minVertexCover;
            this.sizeVerified = sizeVerified;
        }
    }

    /**
     * Solves König's Theorem construction:
     * 1. Find maximum bipartite matching M.
     * 2. Let U be set of unmatched vertices in L.
     * 3. Run alternating BFS from U:
     *    - From L to R: take non-matching edges
     *    - From R to L: take matching edges
     * 4. Let Z be the set of visited vertices.
     * 5. Minimum Vertex Cover C = (L \ Z) U (R ∩ Z).
     */
    public static KonigResult computeCover(List<String> left, List<String> right, Map<String, List<String>> adj) {
        // Step 1: Maximum Matching
        BipartiteMatching.MatchingResult matchRes = BipartiteMatching.computeMaxMatching(left, right, adj);
        Map<String, String> f2c = matchRes.facultyToCourse;
        Map<String, String> c2f = matchRes.courseToFaculty;

        // Step 2: Unmatched in L
        Set<String> unmatchedL = new HashSet<>();
        for (String u : left) {
            if (!f2c.containsKey(u)) {
                unmatchedL.add(u);
            }
        }

        // Step 3: Alternating BFS
        Set<String> visitedL = new HashSet<>(unmatchedL);
        Set<String> visitedR = new HashSet<>();
        Queue<String> queue = new LinkedList<>(unmatchedL);

        while (!queue.isEmpty()) {
            String u = queue.poll();

            // u is in L: follow non-matching edges to R
            List<String> neighbors = adj.getOrDefault(u, Collections.emptyList());
            for (String v : neighbors) {
                if (!visitedR.contains(v)) {
                    // Check if edge (u, v) is not in matching
                    if (!v.equals(f2c.get(u))) {
                        visitedR.add(v);
                        // From v in R, follow matched edge back to L
                        String matchedU = c2f.get(v);
                        if (matchedU != null && !visitedL.contains(matchedU)) {
                            visitedL.add(matchedU);
                            queue.add(matchedU);
                        }
                    }
                }
            }
        }

        // Step 4: Minimum Vertex Cover C = (L \ visitedL) U (R ∩ visitedR)
        Set<String> cover = new LinkedHashSet<>();
        for (String u : left) {
            if (!visitedL.contains(u)) {
                cover.add(u + " [Faculty]");
            }
        }
        for (String v : right) {
            if (visitedR.contains(v)) {
                cover.add(v + " [Course]");
            }
        }

        boolean verified = (cover.size() == f2c.size());
        return new KonigResult(f2c, cover, verified);
    }

    /**
     * Demonstrates König's theorem with verification.
     */
    public static void demonstrate() {
        System.out.println("\n=== König's Theorem: Matching vs Vertex Cover Demonstration ===");
        List<String> faculty = Arrays.asList("Dr. Turing", "Dr. Hinton", "Dr. Codd", "Dr. Tanenbaum", "Dr. Gosling");
        List<String> courses = Arrays.asList("DSA301", "AI201", "DBMS201", "OS301", "JAVA201", "CN301");

        Map<String, List<String>> qual = new HashMap<>();
        qual.put("Dr. Turing", Arrays.asList("DSA301", "OS301"));
        qual.put("Dr. Hinton", Arrays.asList("AI201", "DSA301"));
        qual.put("Dr. Codd", Arrays.asList("DBMS201"));
        qual.put("Dr. Tanenbaum", Arrays.asList("CN301", "OS301"));
        qual.put("Dr. Gosling", Arrays.asList("JAVA201"));

        KonigResult result = computeCover(faculty, courses, qual);

        System.out.println("Maximum Matching M (|M| = " + result.matching.size() + "):");
        for (Map.Entry<String, String> e : result.matching.entrySet()) {
            System.out.printf("  * %-15s <---> %s%n", e.getKey(), e.getValue());
        }

        System.out.println("\nMinimum Vertex Cover C (|C| = " + result.minVertexCover.size() + "):");
        for (String v : result.minVertexCover) {
            System.out.println("  * " + v);
        }

        System.out.println("\nFormal Equivalence Check (|M| == |C|): " + (result.sizeVerified ? "VERIFIED (EQUAL)" : "FAILED"));
        System.out.println("König's Theorem guarantees every bipartite graph satisfies |Max-Matching| = |Min-Vertex-Cover|.");
        System.out.println("Complexity: O(V * E)");
    }
}
