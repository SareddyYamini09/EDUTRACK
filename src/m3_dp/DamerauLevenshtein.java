package m3_dp;

// Input: Two strings S1 (length n) and S2 (length m)
// Algorithm: Damerau-Levenshtein Distance with Adjacent Transposition Support
// Output: Minimum cost of edits including Insertion, Deletion, Substitution, and Transposition
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)
// EduTrack Application: Handling typing transpositions in student search queries (e.g. "tehc" -> "tech", "Algoritm" -> "Algorithm")
public class DamerauLevenshtein {

    /**
     * Computes the Damerau-Levenshtein distance supporting adjacent transpositions.
     */
    public static int compute(String s1, String s2) {
        if (s1 == null || s2 == null) return Integer.MAX_VALUE;
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                // Standard operations: Delete, Insert, Substitute
                dp[i][j] = Math.min(
                        dp[i - 1][j] + 1,
                        Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost)
                );

                // Adjacent transposition
                if (i > 1 && j > 1 &&
                        s1.charAt(i - 1) == s2.charAt(j - 2) &&
                        s1.charAt(i - 2) == s2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + 1);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * Demonstrates Damerau-Levenshtein distance calculation and transposition handling.
     */
    public static void demonstrate(String s1, String s2) {
        System.out.println("\n=== Damerau-Levenshtein Distance Demonstration ===");
        System.out.println("Source: \"" + s1 + "\"");
        System.out.println("Target: \"" + s2 + "\"");

        int dist = compute(s1, s2);
        System.out.println("Computed Distance (with Transposition): " + dist);
        System.out.println("Supports: Insert, Delete, Substitute, Transposition (Swap)");
        System.out.println("Complexity: O(n * m) = O(" + (s1.length() * s2.length()) + ")");
    }
}
