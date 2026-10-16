package m1_string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Input: String S of length n (or pattern P and text T)
// Algorithm: Linear-Time Z-Algorithm using Z-box [L, R] interval tracking
// Output: Z-array where Z[i] is the length of the longest common prefix of S and S[i..n-1]
// Time Complexity: O(n) total time
// Space Complexity: O(n) for the Z-array
// EduTrack Application: Assignment text repetition detection, plagiarism pattern finding
public class ZFunction {

    /**
     * Computes the Z-array for a given string S in O(n) time.
     */
    public static int[] computeZ(String s) {
        int n = s.length();
        int[] z = new int[n];
        if (n == 0) return z;

        z[0] = n;
        int l = 0, r = 0; // [l, r] defines current Z-box

        for (int i = 1; i < n; i++) {
            if (i <= r) {
                // Inside Z-box: reuse previously computed values
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            // Explicitly match characters beyond r
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            // Update Z-box if matched interval extends past r
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    /**
     * Searches for pattern P in text T using Z-function on "P$T".
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return matches;
        }

        String concat = pattern + "$" + text;
        int[] z = computeZ(concat);
        int m = pattern.length();

        for (int i = m + 1; i < concat.length(); i++) {
            if (z[i] == m) {
                matches.add(i - (m + 1));
            }
        }
        return matches;
    }

    /**
     * Detects repeated substrings or phrases of minimum length in a student submission.
     */
    public static List<String> findRepeatedPrefixPhrases(String text, int minLength) {
        List<String> phrases = new ArrayList<>();
        int[] z = computeZ(text);
        for (int i = 1; i < z.length; i++) {
            if (z[i] >= minLength) {
                String sub = text.substring(0, z[i]);
                if (!phrases.contains(sub)) {
                    phrases.add(sub);
                }
            }
        }
        return phrases;
    }

    /**
     * Demonstrates Z-Algorithm with detailed display of the Z-array.
     */
    public static void demonstrate(String text, String pattern) {
        System.out.println("\n=== Z-Function String Matching Demonstration ===");
        System.out.println("Text:    \"" + text + "\"");
        System.out.println("Pattern: \"" + pattern + "\"");

        String combined = pattern + "$" + text;
        int[] z = computeZ(combined);

        System.out.println("Combined String (P$T): \"" + combined + "\"");
        System.out.println("Z-Array: " + Arrays.toString(z));

        List<Integer> matches = search(text, pattern);
        System.out.println("Matches Found: " + matches.size());
        if (!matches.isEmpty()) {
            System.out.print("Starting Positions in Text: ");
            for (int idx : matches) {
                System.out.print(idx + " ");
            }
            System.out.println();
        } else {
            System.out.println("No match found.");
        }

        System.out.println("Complexity: O(n + m) = O(" + (text.length() + pattern.length()) + ") time, O(n + m) space");
    }
}
