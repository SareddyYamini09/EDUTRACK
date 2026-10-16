package m2_suffix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Input: Text string T of length n (appended with unique sentinel '$')
// Algorithm: SA-IS (Suffix Array Induced Sorting) by Nong, Zhang, and Chan (2009)
// Output: Exact linear-time Suffix Array SA of length n
// Time Complexity: O(n) strictly linear time
// Space Complexity: O(n) auxiliary space
// EduTrack Application: Large-scale academic document indexing and syllabus compression
public class SAIS {

    private static final boolean S_TYPE = true;
    private static final boolean L_TYPE = false;

    /**
     * Constructs Suffix Array for a given string using SA-IS.
     */
    public static int[] buildSuffixArray(String text) {
        if (text == null || text.isEmpty()) return new int[0];

        // Convert string to integer array with sentinel 0 at end
        int n = text.length();
        int[] s = new int[n + 1];
        int maxChar = 0;
        for (int i = 0; i < n; i++) {
            s[i] = text.charAt(i) + 1; // shift by 1 so 0 is strictly smallest
            if (s[i] > maxChar) maxChar = s[i];
        }
        s[n] = 0; // sentinel

        int[] sa = sais(s, maxChar + 1);

        // Remove the sentinel from the resulting suffix array
        int[] result = new int[n];
        System.arraycopy(sa, 1, result, 0, n);
        return result;
    }

    /**
     * Core SA-IS recursive algorithm.
     * @param s String as integer array
     * @param K Alphabet size
     */
    private static int[] sais(int[] s, int K) {
        int n = s.length;
        if (n == 1) return new int[]{0};

        // -------------------------------------------------------------
        // STEP 1: S/L Type Classification
        // A character is S-type if suffix s[i..] < s[i+1..]
        // A character is L-type if suffix s[i..] > s[i+1..]
        // -------------------------------------------------------------
        boolean[] t = new boolean[n];
        t[n - 1] = S_TYPE; // Sentinel is S-type
        for (int i = n - 2; i >= 0; i--) {
            if (s[i] < s[i + 1]) {
                t[i] = S_TYPE;
            } else if (s[i] > s[i + 1]) {
                t[i] = L_TYPE;
            } else {
                t[i] = t[i + 1];
            }
        }

        // -------------------------------------------------------------
        // STEP 2: Find LMS (Leftmost S-type) characters
        // s[i] is LMS if t[i] == S_TYPE and t[i-1] == L_TYPE
        // -------------------------------------------------------------
        int[] lmsMap = new int[n];
        Arrays.fill(lmsMap, -1);
        int m = 0;
        for (int i = 1; i < n; i++) {
            if (t[i] == S_TYPE && t[i - 1] == L_TYPE) {
                lmsMap[i] = m++;
            }
        }

        int[] lms = new int[m];
        m = 0;
        for (int i = 1; i < n; i++) {
            if (t[i] == S_TYPE && t[i - 1] == L_TYPE) {
                lms[m++] = i;
            }
        }

        // -------------------------------------------------------------
        // STEP 3: Initial Induced Sort to sort LMS substrings
        // -------------------------------------------------------------
        int[] sa = new int[n];
        inducedSort(s, sa, t, K, lms);

        // -------------------------------------------------------------
        // STEP 4: Name LMS substrings and build reduced string s1
        // -------------------------------------------------------------
        int[] s1 = new int[m];
        int name = 0;
        int prev = -1;

        for (int i = 0; i < n; i++) {
            int pos = sa[i];
            if (lmsMap[pos] != -1) {
                if (prev == -1) {
                    name = 0;
                } else if (!isEqualLmsSubstring(s, t, prev, pos)) {
                    name++;
                }
                s1[lmsMap[pos]] = name;
                prev = pos;
            }
        }

        // -------------------------------------------------------------
        // STEP 5: Recursion on reduced string if names are not unique
        // -------------------------------------------------------------
        int[] sa1;
        if (name + 1 < m) {
            sa1 = sais(s1, name + 1);
        } else {
            sa1 = new int[m];
            for (int i = 0; i < m; i++) {
                sa1[s1[i]] = i;
            }
        }

        // -------------------------------------------------------------
        // STEP 6: Final Induced Sort using accurately ordered LMS suffixes
        // -------------------------------------------------------------
        int[] orderedLms = new int[m];
        for (int i = 0; i < m; i++) {
            orderedLms[i] = lms[sa1[i]];
        }

        inducedSort(s, sa, t, K, orderedLms);
        return sa;
    }

    /**
     * Checks if two LMS substrings are identical.
     */
    private static boolean isEqualLmsSubstring(int[] s, boolean[] t, int p1, int p2) {
        int i = 0;
        while (true) {
            if (s[p1 + i] != s[p2 + i] || t[p1 + i] != t[p2 + i]) return false;
            if (i > 0 && (t[p1 + i] == S_TYPE && t[p1 + i - 1] == L_TYPE)) {
                return (t[p2 + i] == S_TYPE && t[p2 + i - 1] == L_TYPE);
            }
            i++;
        }
    }

    /**
     * Performs bucket-based induced sorting.
     */
    private static void inducedSort(int[] s, int[] sa, boolean[] t, int K, int[] lms) {
        int n = s.length;
        Arrays.fill(sa, -1);

        int[] count = new int[K];
        for (int x : s) count[x]++;

        // Compute bucket ends
        int[] bkt = new int[K];
        int sum = 0;
        for (int i = 0; i < K; i++) {
            sum += count[i];
            bkt[i] = sum;
        }

        // Place LMS items into the end of their buckets
        int[] bktEnd = Arrays.copyOf(bkt, K);
        for (int i = lms.length - 1; i >= 0; i--) {
            int ch = s[lms[i]];
            sa[--bktEnd[ch]] = lms[i];
        }

        // Induce L-type suffixes by scanning left-to-right
        int[] bktHead = new int[K];
        sum = 0;
        for (int i = 0; i < K; i++) {
            bktHead[i] = sum;
            sum += count[i];
        }

        for (int i = 0; i < n; i++) {
            if (sa[i] > 0) {
                int j = sa[i] - 1;
                if (t[j] == L_TYPE) {
                    sa[bktHead[s[j]]++] = j;
                }
            }
        }

        // Induce S-type suffixes by scanning right-to-left
        bktEnd = Arrays.copyOf(bkt, K);
        for (int i = n - 1; i >= 0; i--) {
            if (sa[i] > 0) {
                int j = sa[i] - 1;
                if (t[j] == S_TYPE) {
                    sa[--bktEnd[s[j]]] = j;
                }
            }
        }
    }

    /**
     * Demonstrates SA-IS construction and S/L type principles.
     */
    public static void demonstrate(String document) {
        System.out.println("\n=== SA-IS Linear-Time Suffix Array Construction ===");
        System.out.println("Document: \"" + document + "\"");
        System.out.println("Length:   " + document.length());

        int[] sa = buildSuffixArray(document);

        System.out.println("\nSA-IS Output (First 10 or all):");
        System.out.println("Rank | Suffix Index | Suffix Preview");
        System.out.println("-----+--------------+---------------------------------");
        int limit = Math.min(sa.length, 10);
        for (int i = 0; i < limit; i++) {
            String suf = document.substring(sa[i]);
            if (suf.length() > 30) suf = suf.substring(0, 27) + "...";
            System.out.printf("%-4d | %-12d | %s%n", i, sa[i], suf);
        }
        if (sa.length > 10) {
            System.out.printf("... and %d more suffixes.%n", sa.length - 10);
        }
        System.out.println("-----+--------------+---------------------------------");
        System.out.println("Complexity: O(n) guaranteed linear time via S/L induced sorting.");
    }
}
