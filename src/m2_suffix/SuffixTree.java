package m2_suffix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Input: Text string T of length n
// Algorithm: Suffix Tree Construction with Edge Compression and DFS Traversal
// Output: Compact Suffix Tree supporting fast O(m) substring existence and occurrence queries
// Time Complexity: Construction O(n^2) educational / O(n) Ukkonen, Query O(m)
// Space Complexity: O(n) nodes
// EduTrack Application: Fast multi-pattern search in university library catalogs and digital lecture notes
public class SuffixTree {

    public static class Node {
        Map<Character, Node> children = new HashMap<>();
        int start;
        int end;
        List<Integer> suffixIndices = new ArrayList<>();

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }
    }

    private final String originalText;
    private final String text;
    private final Node root;

    public SuffixTree(String text) {
        this.originalText = text;
        // Append unique sentinel $ to guarantee every suffix ends at a unique leaf
        this.text = text.endsWith("$") ? text : text + "$";
        this.root = new Node(-1, -1);
        buildTree();
    }

    private void buildTree() {
        int n = text.length();
        for (int i = 0; i < n; i++) {
            insertSuffix(i);
        }
    }

    private void insertSuffix(int suffixIndex) {
        Node curr = root;
        int i = suffixIndex;
        int n = text.length();

        while (i < n) {
            char ch = text.charAt(i);
            if (!curr.children.containsKey(ch)) {
                // New branch directly to leaf
                Node leaf = new Node(i, n);
                leaf.suffixIndices.add(suffixIndex);
                curr.children.put(ch, leaf);
                return;
            }

            Node child = curr.children.get(ch);
            int edgeLen = child.end - child.start;
            int matchLen = 0;

            while (matchLen < edgeLen && i + matchLen < n &&
                    text.charAt(child.start + matchLen) == text.charAt(i + matchLen)) {
                matchLen++;
            }

            if (matchLen == edgeLen) {
                // Fully matched current edge, advance deeper
                child.suffixIndices.add(suffixIndex);
                curr = child;
                i += matchLen;
            } else {
                // Partial match: split existing edge
                Node split = new Node(child.start, child.start + matchLen);
                split.suffixIndices.addAll(child.suffixIndices);
                split.suffixIndices.add(suffixIndex);

                curr.children.put(ch, split);

                // Update original child
                child.start += matchLen;
                split.children.put(text.charAt(child.start), child);

                // Add new leaf for remainder of current suffix if any
                if (i + matchLen < n) {
                    Node leaf = new Node(i + matchLen, n);
                    leaf.suffixIndices.add(suffixIndex);
                    split.children.put(text.charAt(i + matchLen), leaf);
                }
                return;
            }
        }
    }

    /**
     * Checks if pattern exists as a substring in O(m).
     */
    public boolean contains(String pattern) {
        return !findOccurrences(pattern).isEmpty();
    }

    /**
     * Returns starting positions of all occurrences of pattern.
     */
    public List<Integer> findOccurrences(String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern == null || pattern.isEmpty() || originalText.isEmpty()) return result;

        Node curr = root;
        int i = 0;
        int m = pattern.length();

        while (i < m) {
            char ch = pattern.charAt(i);
            if (!curr.children.containsKey(ch)) {
                return result; // No path matches
            }

            Node child = curr.children.get(ch);
            int edgeLen = child.end - child.start;
            int j = 0;

            while (j < edgeLen && i + j < m) {
                if (text.charAt(child.start + j) != pattern.charAt(i + j)) {
                    return result; // Mismatch on edge
                }
                j++;
            }

            if (i + j == m) {
                // Pattern matched completely
                collectSuffixIndices(child, result);
                return result;
            }

            i += edgeLen;
            curr = child;
        }

        return result;
    }

    private void collectSuffixIndices(Node node, List<Integer> result) {
        if (node == null) return;
        for (int idx : node.suffixIndices) {
            if (idx < originalText.length() && !result.contains(idx)) {
                result.add(idx);
            }
        }
        for (Node child : node.children.values()) {
            collectSuffixIndices(child, result);
        }
    }

    /**
     * Demonstrates Suffix Tree construction and substring queries.
     */
    public static void demonstrate(String document, String query) {
        System.out.println("\n=== Suffix Tree Substring Query Demonstration ===");
        System.out.println("Document: \"" + document + "\"");
        System.out.println("Query:    \"" + query + "\"");

        SuffixTree tree = new SuffixTree(document);
        List<Integer> positions = tree.findOccurrences(query);

        System.out.println("Query Found: " + (!positions.isEmpty()));
        System.out.println("Occurrences Count: " + positions.size());
        System.out.println("Starting Positions: " + positions);
        System.out.println("Complexity: Query time O(m) = O(" + query.length() + ")");
    }
}
