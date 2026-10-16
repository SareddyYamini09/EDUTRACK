package m1_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Input: Text string T (length n), Pattern string P (length m)
// Algorithm: Knuth-Morris-Pratt (KMP) Pattern Matching with Failure Function (LPS)
// Output: Starting indices of all exact occurrences of P in T
// Time Complexity: O(n + m) — O(m) preprocessing, O(n) text search
// Space Complexity: O(m) for the LPS array
// EduTrack Application: Fast search for student queries, course titles, and curriculum keywords
public class KMP {

    /**
     * Computes the Longest Proper Prefix that is also Suffix (LPS) array.
     */
    public static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // fallback without advancing i
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * Searches for pattern P in text T. Returns all matching starting indices.
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return matches;
        }

        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPS(pattern);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                matches.add(i - j);
                j = lps[j - 1]; // look for next occurrence
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return matches;
    }

    /**
     * Case-insensitive wrapper for flexible search in academic records.
     */
    public static List<Integer> searchIgnoreCase(String text, String pattern) {
        if (text == null || pattern == null) return new ArrayList<>();
        return search(text.toLowerCase(), pattern.toLowerCase());
    }

    /**
     * Demonstrates KMP algorithm with full diagnostic output.
     */
    public static void demonstrate(String text, String pattern) {
        System.out.println("\n=== KMP String Matching Demonstration ===");
        System.out.println("Text:    \"" + text + "\"");
        System.out.println("Pattern: \"" + pattern + "\"");

        int[] lps = computeLPS(pattern);
        System.out.println("LPS Array: " + Arrays.toString(lps));

        List<Integer> matches = searchIgnoreCase(text, pattern);
        System.out.println("Matches Found: " + matches.size());
        if (!matches.isEmpty()) {
            System.out.print("Matching Starting Positions: ");
            for (int idx : matches) {
                System.out.print(idx + " ");
            }
            System.out.println();
        } else {
            System.out.println("No match found.");
        }

        System.out.println("Complexity: Preprocessing O(m) = O(" + pattern.length() + "), Search O(n) = O(" + text.length() + ")");
    }
}
