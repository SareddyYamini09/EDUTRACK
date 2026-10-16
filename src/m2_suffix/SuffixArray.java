package m2_suffix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Input: Text string T of length n
// Algorithm: Suffix Array Construction via Suffix Sorting and Binary Search Querying
// Output: Array SA where SA[i] is the starting index of the i-th lexicographically smallest suffix
// Time Complexity: Construction O(n^2 log n) or O(n log^2 n), Query O(m log n)
// Space Complexity: O(n) for suffix indices
// EduTrack Application: Document indexing, fast keyword lookup in course syllabi and lecture transcripts
public class SuffixArray {

    private final String text;
    private final int[] sa;

    public SuffixArray(String text) {
        this.text = text;
        this.sa = buildSuffixArray(text);
    }

    private int[] buildSuffixArray(String s) {
        int n = s.length();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        // Lexicographical sorting of suffixes
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return s.substring(a).compareTo(s.substring(b));
            }
        });

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = order[i];
        }
        return result;
    }

    public int[] getSA() {
        return sa;
    }

    public String getText() {
        return text;
    }

    /**
     * Binary search to find all occurrences of pattern P using Suffix Array in O(m log n).
     */
    public List<Integer> search(String pattern) {
        List<Integer> matches = new ArrayList<>();
        if (pattern == null || pattern.isEmpty() || text.isEmpty()) return matches;

        int n = text.length();
        int m = pattern.length();

        // Find lower bound
        int low = 0, high = n - 1;
        int firstOccur = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String suffix = text.substring(sa[mid]);
            int cmp = suffix.length() >= m ? suffix.substring(0, m).compareTo(pattern) : suffix.compareTo(pattern);

            if (cmp >= 0) {
                if (cmp == 0) firstOccur = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (firstOccur == -1) return matches;

        // Find upper bound
        low = firstOccur;
        high = n - 1;
        int lastOccur = firstOccur;
        while (low <= high) {
            int mid = (low + high) / 2;
            String suffix = text.substring(sa[mid]);
            int cmp = suffix.length() >= m ? suffix.substring(0, m).compareTo(pattern) : suffix.compareTo(pattern);

            if (cmp == 0) {
                lastOccur = mid;
                low = mid + 1;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        for (int i = firstOccur; i <= lastOccur; i++) {
            matches.add(sa[i]);
        }
        return matches;
    }

    /**
     * Demonstrates Suffix Array construction and indexing.
     */
    public static void demonstrate(String document, String searchPattern) {
        System.out.println("\n=== Suffix Array Indexing Demonstration ===");
        System.out.println("Document: \"" + document + "\"");

        SuffixArray suffixArray = new SuffixArray(document);
        int[] sa = suffixArray.getSA();

        System.out.println("\nRank | Suffix Index | Suffix Content");
        System.out.println("-----+--------------+---------------------------------------");
        for (int i = 0; i < sa.length; i++) {
            String suf = document.substring(sa[i]);
            if (suf.length() > 35) suf = suf.substring(0, 32) + "...";
            System.out.printf("%-4d | %-12d | %s%n", i, sa[i], suf);
        }
        System.out.println("-----+--------------+---------------------------------------");

        if (searchPattern != null && !searchPattern.isEmpty()) {
            System.out.println("Searching for: \"" + searchPattern + "\" via Binary Search");
            List<Integer> occurrences = suffixArray.search(searchPattern);
            System.out.println("Matches Found: " + occurrences.size() + " at indices: " + occurrences);
        }
    }
}
