package m1_string;

import java.util.*;

// Input: Text T, Dictionary of multiple patterns {P1, P2, ..., Pk}
// Algorithm: Aho-Corasick Automaton (Trie + BFS Failure Transitions + Output Links)
// Output: All occurrences of any dictionary keyword with corresponding starting positions
// Time Complexity: O(n + sum(m_i) + z) where z is number of matches
// Space Complexity: O(sum(m_i) * Sigma) where Sigma is alphabet size
// EduTrack Application: Simultaneous multi-keyword search in curriculum notes, syllabus, and submissions
public class AhoCorasick {

    public static class MatchResult {
        public final String keyword;
        public final int position;

        public MatchResult(String keyword, int position) {
            this.keyword = keyword;
            this.position = position;
        }

        @Override
        public String toString() {
            return String.format("%-25s | Position %d", keyword, position);
        }
    }

    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node fail = null;
        List<String> output = new ArrayList<>();
    }

    private final Node root;

    public AhoCorasick(List<String> keywords) {
        root = new Node();
        buildTrie(keywords);
        buildFailureLinks();
    }

    private void buildTrie(List<String> keywords) {
        for (String word : keywords) {
            if (word == null || word.isEmpty()) continue;
            Node curr = root;
            for (char ch : word.toCharArray()) {
                curr = curr.children.computeIfAbsent(ch, c -> new Node());
            }
            curr.output.add(word);
        }
    }

    private void buildFailureLinks() {
        Queue<Node> queue = new LinkedList<>();

        // Level 1 nodes fail to root
        for (Node child : root.children.values()) {
            child.fail = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Map.Entry<Character, Node> entry : curr.children.entrySet()) {
                char ch = entry.getKey();
                Node child = entry.getValue();

                // Trace failure link
                Node f = curr.fail;
                while (f != null && !f.children.containsKey(ch)) {
                    f = f.fail;
                }

                child.fail = (f == null) ? root : f.children.get(ch);
                // Inherit output words from failure node
                child.output.addAll(child.fail.output);

                queue.add(child);
            }
        }
    }

    /**
     * Searches all keywords simultaneously across the provided text.
     */
    public List<MatchResult> search(String text) {
        List<MatchResult> results = new ArrayList<>();
        if (text == null || text.isEmpty()) return results;

        Node curr = root;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            while (curr != null && !curr.children.containsKey(ch)) {
                curr = curr.fail;
            }

            if (curr == null) {
                curr = root;
                continue;
            }

            curr = curr.children.get(ch);

            for (String keyword : curr.output) {
                int startPos = i - keyword.length() + 1;
                results.add(new MatchResult(keyword, startPos));
            }
        }
        return results;
    }

    /**
     * Demonstrates Aho-Corasick multi-pattern search.
     */
    public static void demonstrate(String document, List<String> keywords) {
        System.out.println("\n=== Aho-Corasick Multi-Pattern Search Demonstration ===");
        System.out.println("Document: \"" + document + "\"");
        System.out.println("Keywords: " + keywords);

        AhoCorasick ac = new AhoCorasick(keywords);
        List<MatchResult> matches = ac.search(document);

        System.out.println("\nKeyword                   | Position");
        System.out.println("---------------------------------------------");
        if (matches.isEmpty()) {
            System.out.println("No matching keywords found.");
        } else {
            for (MatchResult m : matches) {
                System.out.println(m);
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total Matches: " + matches.size());
        System.out.println("Complexity: O(TextLength + TotalKeywordLength + Matches) time");
    }
}
