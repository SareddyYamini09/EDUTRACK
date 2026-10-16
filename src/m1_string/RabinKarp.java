package m1_string;

import java.util.ArrayList;
import java.util.List;

// Input: Text T (length n), Pattern P (length m), Radix d, Prime modulus q
// Algorithm: Rabin-Karp Rolling Hash with Collision Verification
// Output: Starting indices of all exact occurrences of P in T
// Time Complexity: O(n + m) average, O(n * m) worst case
// Space Complexity: O(1) auxiliary space
// EduTrack Application: Fast lookup for course codes (e.g. "DSA301"), assignment IDs, and exam codes
public class RabinKarp {

    private static final int BASE = 256;
    private static final long MOD = 1_000_000_007L;

    /**
     * Searches for pattern in text using rolling hash and explicit verification.
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return matches;
        }

        int n = text.length();
        int m = pattern.length();

        // Calculate highest power h = (BASE^(m-1)) % MOD
        long h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * BASE) % MOD;
        }

        long patternHash = 0;
        long textHash = 0;

        // Compute initial hash values for pattern and the first window of text
        for (int i = 0; i < m; i++) {
            patternHash = (BASE * patternHash + pattern.charAt(i)) % MOD;
            textHash = (BASE * textHash + text.charAt(i)) % MOD;
        }

        int collisionCount = 0;

        // Slide the window across text
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                // Verify characters on hash match to eliminate spurious hits
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        collisionCount++;
                        break;
                    }
                }
                if (match) {
                    matches.add(i);
                }
            }

            // Compute rolling hash for next window: remove leading char, add trailing char
            if (i < n - m) {
                textHash = (BASE * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % MOD;
                if (textHash < 0) {
                    textHash += MOD;
                }
            }
        }
        return matches;
    }

    /**
     * Demonstrates Rabin-Karp pattern search with diagnostic details.
     */
    public static void demonstrate(String text, String pattern) {
        System.out.println("\n=== Rabin-Karp Rolling Hash Demonstration ===");
        System.out.println("Text:            \"" + text + "\"");
        System.out.println("Pattern:         \"" + pattern + "\"");
        System.out.println("Radix Base:      " + BASE + ", Modulus: " + MOD);

        List<Integer> matches = search(text, pattern);

        System.out.println("Matches Found:   " + matches.size());
        if (!matches.isEmpty()) {
            System.out.print("Starting Positions: ");
            for (int pos : matches) {
                System.out.print(pos + " ");
            }
            System.out.println();
        } else {
            System.out.println("No occurrences found.");
        }
        System.out.println("Complexity: Average O(n+m) = O(" + (text.length() + pattern.length()) + "), Space O(1)");
    }
}
