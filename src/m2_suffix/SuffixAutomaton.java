package m2_suffix;

import java.util.HashMap;
import java.util.Map;

// Input: String S of length n
// Algorithm: Suffix Automaton (DAWG - Directed Acyclic Word Graph) Construction (Blumer et al. / Crochemore)
// Output: Minimal state automaton recognizing all substrings of S
// Time Complexity: O(n) strictly linear construction time, O(m) substring query
// Space Complexity: O(n) states and transitions (at most 2n-1 states and 3n-4 transitions)
// EduTrack Application: Instant substring existence queries, counting unique lecture subtopics, distinct terms analysis
public class SuffixAutomaton {

    public static class State {
        int len;
        int link;
        Map<Character, Integer> next = new HashMap<>();

        public State(int len, int link) {
            this.len = len;
            this.link = link;
        }
    }

    private final State[] st;
    private int sz;
    private int last;
    private final String text;

    public SuffixAutomaton(String text) {
        this.text = text;
        int maxStates = 2 * text.length() + 2;
        st = new State[maxStates];

        // Initialize root state 0
        st[0] = new State(0, -1);
        sz = 1;
        last = 0;

        for (int i = 0; i < text.length(); i++) {
            extend(text.charAt(i));
        }
    }

    private void extend(char c) {
        int cur = sz++;
        st[cur] = new State(st[last].len + 1, 0);

        int p = last;
        while (p != -1 && !st[p].next.containsKey(c)) {
            st[p].next.put(c, cur);
            p = st[p].link;
        }

        if (p == -1) {
            st[cur].link = 0;
        } else {
            int q = st[p].next.get(c);
            if (st[p].len + 1 == st[q].len) {
                st[cur].link = q;
            } else {
                int clone = sz++;
                st[clone] = new State(st[p].len + 1, st[q].link);
                st[clone].next.putAll(st[q].next);

                while (p != -1 && st[p].next.get(c) == q) {
                    st[p].next.put(c, clone);
                    p = st[p].link;
                }
                st[q].link = clone;
                st[cur].link = clone;
            }
        }
        last = cur;
    }

    /**
     * Checks if query exists as a substring in O(m) time.
     */
    public boolean contains(String query) {
        if (query == null || query.isEmpty()) return true;
        int curr = 0;
        for (int i = 0; i < query.length(); i++) {
            char c = query.charAt(i);
            if (!st[curr].next.containsKey(c)) {
                return false;
            }
            curr = st[curr].next.get(c);
        }
        return true;
    }

    /**
     * Calculates the total number of distinct substrings in O(states).
     */
    public long countDistinctSubstrings() {
        long[] dp = new long[sz];
        return countDfs(0, dp);
    }

    private long countDfs(int u, long[] dp) {
        if (dp[u] != 0) return dp[u];
        long count = 0;
        for (int v : st[u].next.values()) {
            count += 1 + countDfs(v, dp);
        }
        dp[u] = count;
        return count;
    }

    /**
     * Demonstrates Suffix Automaton queries, state count, and distinct substring metrics.
     */
    public static void demonstrate(String document, String testWord) {
        System.out.println("\n=== Suffix Automaton (DAWG) Demonstration ===");
        System.out.println("Document:   \"" + document + "\"");
        System.out.println("Test Query: \"" + testWord + "\"");

        SuffixAutomaton sam = new SuffixAutomaton(document);

        System.out.println("Total States Built:        " + sam.sz);
        System.out.println("Contains Query Substring: " + sam.contains(testWord));
        System.out.println("Number of Distinct Substrings: " + sam.countDistinctSubstrings());
        System.out.println("Complexity: Construction O(n) = O(" + document.length() + "), Query O(m) = O(" + testWord.length() + ")");
    }
}
