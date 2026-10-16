package m3_dp;

// Input: Sorted academic search keys K = {k1, k2, ..., kn} and access frequencies P = {p1, p2, ..., pn}
// Algorithm: Dynamic Programming for Optimal Binary Search Tree (Cormen et al. OBST)
// Output: Minimum expected search cost, cost table e[i][j], weight table w[i][j], and optimal root choices
// Time Complexity: O(n^3) (or O(n^2) with Knuth optimization)
// Space Complexity: O(n^2) for DP tables
// EduTrack Application: Fast index organization for university course catalog and high-frequency portal query lookup
public class OptimalBST {

    public static class OBSTResult {
        public final double minCost;
        public final double[][] e;
        public final double[][] w;
        public final int[][] root;
        public final String[] keys;

        public OBSTResult(double minCost, double[][] e, double[][] w, int[][] root, String[] keys) {
            this.minCost = minCost;
            this.e = e;
            this.w = w;
            this.root = root;
            this.keys = keys;
        }
    }

    /**
     * Solves the Optimal Binary Search Tree DP.
     */
    public static OBSTResult solve(String[] keys, double[] p) {
        int n = keys.length;
        double[][] e = new double[n + 2][n + 2];
        double[][] w = new double[n + 2][n + 2];
        int[][] root = new int[n + 1][n + 1];

        // Base cases
        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = 0;
            w[i][i - 1] = 0;
        }

        // Subtree sizes l from 1 to n
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j - 1];

                for (int r = i; r <= j; r++) {
                    double t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }

        return new OBSTResult(e[1][n], e, w, root, keys);
    }

    /**
     * Prints the tree hierarchy recursively.
     */
    public static void printTree(int[][] root, String[] keys, int i, int j, int parent, boolean isLeft) {
        if (i > j) return;
        int r = root[i][j];
        if (parent == 0) {
            System.out.printf("  Root: %s%n", keys[r - 1]);
        } else if (isLeft) {
            System.out.printf("    Left child of %-7s -> %s%n", keys[parent - 1], keys[r - 1]);
        } else {
            System.out.printf("    Right child of %-7s -> %s%n", keys[parent - 1], keys[r - 1]);
        }
        printTree(root, keys, i, r - 1, r, true);
        printTree(root, keys, r + 1, j, r, false);
    }

    /**
     * Demonstrates Optimal BST on academic course codes.
     */
    public static void demonstrate() {
        System.out.println("\n=== Optimal Binary Search Tree (OBST) Demonstration ===");
        String[] keys = {"AI201", "CSE101", "DSA301", "ML401"};
        double[] freq = {0.15, 0.35, 0.30, 0.20}; // Search probabilities

        System.out.println("Academic Search Keys and Access Frequencies:");
        for (int i = 0; i < keys.length; i++) {
            System.out.printf("  Key: %-8s | Probability: %.2f%n", keys[i], freq[i]);
        }

        OBSTResult res = solve(keys, freq);

        int n = keys.length;
        System.out.println("\nExpected Cost Matrix e[i][j]:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < i) {
                    System.out.print("      -");
                } else {
                    System.out.printf("%7.3f", res.e[i][j]);
                }
            }
            System.out.println();
        }

        System.out.println("\nOptimal Root Selection Table root[i][j]:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j < i) {
                    System.out.print("   -");
                } else {
                    System.out.printf("%4d", res.root[i][j]);
                }
            }
            System.out.println();
        }

        System.out.printf("\nOptimal Expected Search Cost: %.4f%n", res.minCost);
        System.out.println("Constructed Optimal Binary Search Tree:");
        printTree(res.root, keys, 1, n, 0, false);
        System.out.println("Complexity: O(n^3) time = O(" + (n * n * n) + "), O(n^2) space");
    }
}
