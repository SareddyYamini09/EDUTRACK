package m3_dp;

// Input: Dimension array p of size n+1 representing n pipeline transformation matrices (A_i has dimension p[i-1] x p[i])
// Algorithm: Dynamic Programming for Matrix Chain Multiplication (Cormen et al.)
// Output: Minimum scalar multiplication cost, DP cost table, and optimal parenthesization tree
// Time Complexity: O(n^3)
// Space Complexity: O(n^2) for tables m and s
// EduTrack Application: Optimizing academic analytics pipeline transformations (Student -> Course -> Assignment -> Analytics)
public class MatrixChainMultiplication {

    public static class MCMResult {
        public final int minCost;
        public final int[][] m;
        public final int[][] s;
        public final String parenthesization;
        public final String[] pipelineNames;

        public MCMResult(int minCost, int[][] m, int[][] s, String parenthesization, String[] pipelineNames) {
            this.minCost = minCost;
            this.m = m;
            this.s = s;
            this.parenthesization = parenthesization;
            this.pipelineNames = pipelineNames;
        }
    }

    /**
     * Solves Matrix Chain Multiplication for array of dimensions p.
     */
    public static MCMResult solve(int[] p, String[] stageNames) {
        int n = p.length - 1; // number of matrices
        int[][] m = new int[n + 1][n + 1];
        int[][] s = new int[n + 1][n + 1];

        // Cost is 0 when multiplying one matrix
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        // l is the chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        String parens = buildParenthesization(s, 1, n, stageNames);
        return new MCMResult(m[1][n], m, s, parens, stageNames);
    }

    private static String buildParenthesization(int[][] s, int i, int j, String[] names) {
        if (i == j) {
            return (names != null && i - 1 < names.length) ? names[i - 1] : "A" + i;
        } else {
            return "(" + buildParenthesization(s, i, s[i][j], names) + " x " +
                    buildParenthesization(s, s[i][j] + 1, j, names) + ")";
        }
    }

    /**
     * Demonstrates MCM on an academic data processing pipeline.
     */
    public static void demonstrate() {
        System.out.println("\n=== Matrix Chain Multiplication Pipeline Optimization ===");
        // Academic pipeline dimensions:
        // A1: Student Features      (100 x 20)
        // A2: Course Embeddings     (20 x 50)
        // A3: Assignment Grades     (50 x 10)
        // A4: Analytics Indicators  (10 x 5)
        int[] p = {100, 20, 50, 10, 5};
        String[] stages = {"StudentData", "CourseData", "AssignmentData", "Analytics"};

        System.out.println("Pipeline Stages & Dimensional Matrices:");
        for (int i = 0; i < stages.length; i++) {
            System.out.printf("  %s (%s): %d x %d%n", stages[i], "A" + (i + 1), p[i], p[i + 1]);
        }

        MCMResult result = solve(p, stages);

        int n = p.length - 1;
        System.out.println("\nDP Cost Matrix (m[i][j]):");
        System.out.print("     ");
        for (int j = 1; j <= n; j++) System.out.printf("   j=%d  ", j);
        System.out.println();

        for (int i = 1; i <= n; i++) {
            System.out.printf("i=%d |", i);
            for (int j = 1; j <= n; j++) {
                if (j < i) {
                    System.out.print("        ");
                } else {
                    System.out.printf(" %7d", result.m[i][j]);
                }
            }
            System.out.println();
        }

        System.out.println("\nMinimum Scalar Multiplications: " + result.minCost);
        System.out.println("Optimal Execution Pipeline:    " + result.parenthesization);
        System.out.println("Complexity: O(n^3) time = O(" + (n * n * n) + "), O(n^2) space");
    }
}
