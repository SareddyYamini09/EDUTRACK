package m3_dp;

import java.util.List;

// Input: Two strings S1 (length n) and S2 (length m)
// Algorithm: Wagner-Fischer Dynamic Programming for Levenshtein Edit Distance
// Output: Minimum number of edit operations (Insert, Delete, Substitute) to convert S1 to S2
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)
// EduTrack Application: Fuzzy search and auto-correction for student search queries and course lookups
public class LevenshteinDistance {

    /**
     * Computes the Levenshtein distance between s1 and s2.
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
                dp[i][j] = Math.min(
                        dp[i - 1][j] + 1,       // Deletion
                        Math.min(
                                dp[i][j - 1] + 1,   // Insertion
                                dp[i - 1][j - 1] + cost // Substitution
                        )
                );
            }
        }
        return dp[n][m];
    }

    /**
     * Finds the closest match in a candidate list for query correction.
     */
    public static String findClosestMatch(String query, List<String> candidates) {
        if (query == null || candidates == null || candidates.isEmpty()) return query;

        String bestMatch = query;
        int minDistance = Integer.MAX_VALUE;

        for (String cand : candidates) {
            int dist = compute(query.toLowerCase(), cand.toLowerCase());
            if (dist < minDistance) {
                minDistance = dist;
                bestMatch = cand;
            }
        }
        return bestMatch;
    }

    /**
     * Demonstrates Levenshtein Distance computation with DP table visualization.
     */
    public static void demonstrate(String s1, String s2) {
        System.out.println("\n=== Levenshtein Edit Distance Demonstration ===");
        System.out.println("String 1 (Input):  \"" + s1 + "\"");
        System.out.println("String 2 (Target): \"" + s2 + "\"");

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));
            }
        }

        System.out.println("\nDynamic Programming Matrix:");
        System.out.print("      ");
        for (int j = 0; j < m; j++) System.out.printf(" %c ", s2.charAt(j));
        System.out.println();

        for (int i = 0; i <= n; i++) {
            if (i == 0) System.out.print("  # ");
            else System.out.printf("  %c ", s1.charAt(i - 1));

            for (int j = 0; j <= m; j++) {
                System.out.printf("%2d ", dp[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nLevenshtein Distance: " + dp[n][m]);
        System.out.println("Complexity: O(n * m) = O(" + (n * m) + ")");
    }
}
