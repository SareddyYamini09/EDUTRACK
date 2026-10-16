package m4_flow;

import java.util.*;

// Input: Bipartite graph G = (U, V, E) where U = Faculty, V = Courses, E = Qualification edges
// Algorithm: Maximum Bipartite Matching via Augmenting Paths (Ford-Fulkerson / DFS)
// Output: Maximum cardinality matching mapping each faculty member to at most one course
// Time Complexity: O(V * E)
// Space Complexity: O(V)
// EduTrack Application: Optimal faculty-to-course teaching allocation
public class BipartiteMatching {

    public static class MatchingResult {
        public final int maxMatchingSize;
        public final Map<String, String> facultyToCourse;
        public final Map<String, String> courseToFaculty;

        public MatchingResult(int size, Map<String, String> f2c, Map<String, String> c2f) {
            this.maxMatchingSize = size;
            this.facultyToCourse = f2c;
            this.courseToFaculty = c2f;
        }
    }

    /**
     * Computes maximum bipartite matching between faculty members and courses.
     */
    public static MatchingResult computeMaxMatching(List<String> faculty, List<String> courses, Map<String, List<String>> qualifications) {
        int uCount = faculty.size();
        int vCount = courses.size();

        Map<String, Integer> facultyIdx = new HashMap<>();
        for (int i = 0; i < uCount; i++) facultyIdx.put(faculty.get(i), i);

        Map<String, Integer> courseIdx = new HashMap<>();
        for (int j = 0; j < vCount; j++) courseIdx.put(courses.get(j), j);

        // matchR[j] = index of faculty matched to course j (-1 if unmatched)
        int[] matchR = new int[vCount];
        Arrays.fill(matchR, -1);

        int resultSize = 0;
        for (int u = 0; u < uCount; u++) {
            boolean[] visited = new boolean[vCount];
            if (bpmDfs(u, qualifications, faculty, courses, courseIdx, visited, matchR)) {
                resultSize++;
            }
        }

        Map<String, String> f2c = new LinkedHashMap<>();
        Map<String, String> c2f = new LinkedHashMap<>();

        for (int j = 0; j < vCount; j++) {
            if (matchR[j] != -1) {
                String fName = faculty.get(matchR[j]);
                String cName = courses.get(j);
                f2c.put(fName, cName);
                c2f.put(cName, fName);
            }
        }

        return new MatchingResult(resultSize, f2c, c2f);
    }

    private static boolean bpmDfs(int u, Map<String, List<String>> qualifications,
                                  List<String> faculty, List<String> courses,
                                  Map<String, Integer> courseIdx, boolean[] visited, int[] matchR) {
        String uName = faculty.get(u);
        List<String> qualifiedCourses = qualifications.getOrDefault(uName, Collections.emptyList());

        for (String cName : qualifiedCourses) {
            Integer v = courseIdx.get(cName);
            if (v != null && !visited[v]) {
                visited[v] = true;

                // If course v is not assigned, or previously assigned faculty has an alternate course
                if (matchR[v] < 0 || bpmDfs(matchR[v], qualifications, faculty, courses, courseIdx, visited, matchR)) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Demonstrates Maximum Bipartite Matching on university faculty and courses.
     */
    public static void demonstrate() {
        System.out.println("\n=== Bipartite Matching Faculty Allocation Demonstration ===");
        List<String> faculty = Arrays.asList("Dr. Turing", "Dr. Hinton", "Dr. Codd", "Dr. Tanenbaum", "Dr. Gosling");
        List<String> courses = Arrays.asList("DSA301", "AI201", "DBMS201", "OS301", "JAVA201", "CN301");

        Map<String, List<String>> qual = new HashMap<>();
        qual.put("Dr. Turing", Arrays.asList("DSA301", "OS301"));
        qual.put("Dr. Hinton", Arrays.asList("AI201", "DSA301"));
        qual.put("Dr. Codd", Arrays.asList("DBMS201"));
        qual.put("Dr. Tanenbaum", Arrays.asList("CN301", "OS301"));
        qual.put("Dr. Gosling", Arrays.asList("JAVA201"));

        System.out.println("Faculty Qualifications (Bipartite Edges):");
        for (String f : faculty) {
            System.out.printf("  %-15s can teach -> %s%n", f, qual.get(f));
        }

        MatchingResult result = computeMaxMatching(faculty, courses, qual);

        System.out.println("\nOptimal Maximum Matching Result (Size = " + result.maxMatchingSize + "):");
        for (Map.Entry<String, String> entry : result.facultyToCourse.entrySet()) {
            System.out.printf("  [Assigned] %-15s ===> %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println("Complexity: O(V * E) = O(" + (faculty.size() + courses.size()) + " * " + 8 + ")");
    }
}
