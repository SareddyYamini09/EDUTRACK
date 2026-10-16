package m2_suffix;

import java.util.Arrays;

// Input: Text string T of length n, Suffix Array SA of T
// Algorithm: Kasai's Algorithm for Longest Common Prefix (LCP) Array Construction
// Output: Array LCP where LCP[i] = length of common prefix between suffix SA[i] and SA[i-1]
// Time Complexity: O(n) strictly linear time
// Space Complexity: O(n) for rank and LCP arrays
// EduTrack Application: Assignment plagiarism detection, common passage extraction, syllabus deduplication
public class LCPKasai {

    public static class LCPResult {
        public final int[] lcp;
        public final String longestRepeatedPhrase;
        public final int maxLcpValue;
        public final int startingIndex;

        public LCPResult(int[] lcp, String longestRepeatedPhrase, int maxLcpValue, int startingIndex) {
            this.lcp = lcp;
            this.longestRepeatedPhrase = longestRepeatedPhrase;
            this.maxLcpValue = maxLcpValue;
            this.startingIndex = startingIndex;
        }
    }

    /**
     * Computes the LCP array in O(n) using Kasai's algorithm.
     */
    public static LCPResult computeLCP(String text, int[] sa) {
        int n = text.length();
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0; // length of current matched prefix

        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1]; // preceding suffix in sorted suffix array
                while (i + h < n && j + h < n && text.charAt(i + h) == text.charAt(j + h)) {
                    h++;
                }
                lcp[rank[i]] = h;
                if (h > 0) {
                    h--; // decrement for the next suffix (Kasai property)
                }
            } else {
                lcp[0] = 0;
            }
        }

        // Identify longest repeated phrase
        int maxLcp = 0;
        int bestIdx = -1;
        for (int i = 1; i < n; i++) {
            if (lcp[i] > maxLcp) {
                maxLcp = lcp[i];
                bestIdx = sa[i];
            }
        }

        String longestRepeated = (maxLcp > 0 && bestIdx != -1) ? text.substring(bestIdx, bestIdx + maxLcp) : "";
        return new LCPResult(lcp, longestRepeated, maxLcp, bestIdx);
    }

    /**
     * Finds common phrases between two documents by concatenating with a delimiter.
     */
    public static String findLongestCommonSubstring(String doc1, String doc2) {
        String combined = doc1 + "#" + doc2 + "$";
        int splitPoint = doc1.length();

        SuffixArray saObj = new SuffixArray(combined);
        int[] sa = saObj.getSA();
        LCPResult result = computeLCP(combined, sa);

        int maxLen = 0;
        String bestSub = "";

        for (int i = 1; i < combined.length(); i++) {
            boolean firstInDoc1 = sa[i] < splitPoint;
            boolean secondInDoc1 = sa[i - 1] < splitPoint;

            // One suffix must belong to doc1 and the other to doc2
            if (firstInDoc1 != secondInDoc1) {
                int commonLen = result.lcp[i];
                // Must not include delimiters
                if (commonLen > maxLen) {
                    String candidate = combined.substring(sa[i], sa[i] + commonLen);
                    if (!candidate.contains("#") && !candidate.contains("$")) {
                        maxLen = commonLen;
                        bestSub = candidate;
                    }
                }
            }
        }
        return bestSub;
    }

    /**
     * Demonstrates Kasai LCP calculation and repeated phrase analysis.
     */
    public static void demonstrate(String text) {
        System.out.println("\n=== Kasai LCP Array & Repetition Demonstration ===");
        System.out.println("Text: \"" + text + "\"");

        SuffixArray saObj = new SuffixArray(text);
        int[] sa = saObj.getSA();
        LCPResult result = computeLCP(text, sa);

        System.out.println("\nSuffix Index | LCP | Suffix");
        System.out.println("-------------+-----+---------------------------------------");
        for (int i = 0; i < sa.length; i++) {
            String suf = text.substring(sa[i]);
            if (suf.length() > 30) suf = suf.substring(0, 27) + "...";
            System.out.printf("%-12d | %-3d | %s%n", sa[i], result.lcp[i], suf);
        }
        System.out.println("-------------+-----+---------------------------------------");
        System.out.println("Longest Repeated Phrase: \"" + result.longestRepeatedPhrase + "\" (Length: " + result.maxLcpValue + ")");
        System.out.println("Complexity: O(n) time, O(n) space");
    }
}
