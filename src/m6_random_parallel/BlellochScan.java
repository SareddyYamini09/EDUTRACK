package m6_random_parallel;

import java.util.Arrays;

// Input: Array A of n numbers (e.g. daily student study hours, cumulative attendance, assignment scores)
// Algorithm: Blelloch Work-Efficient Parallel Prefix Scan (Upsweep Reduce + Downsweep Propagation)
// Output: Exclusive and Inclusive prefix sums
// Time Complexity: Work O(n), Span (Depth) O(log n) on P processors
// Space Complexity: O(n) array padded to nearest power of 2
// EduTrack Application: Fast parallel cumulative tracking of student progress, total semester credit hours, and activity trends
public class BlellochScan {

    public static class ScanResult {
        public final int[] exclusiveScan;
        public final int[] inclusiveScan;
        public final int originalLength;

        public ScanResult(int[] exclusiveScan, int[] inclusiveScan, int originalLength) {
            this.exclusiveScan = exclusiveScan;
            this.inclusiveScan = inclusiveScan;
            this.originalLength = originalLength;
        }
    }

    /**
     * Executes Blelloch Parallel Scan algorithm.
     */
    public static ScanResult scan(int[] input) {
        int n = input.length;
        if (n == 0) return new ScanResult(new int[0], new int[0], 0);

        // Pad to next power of 2
        int m = 1;
        while (m < n) m <<= 1;

        int[] a = new int[m];
        System.arraycopy(input, 0, a, 0, n);

        // -------------------------------------------------------------
        // PHASE 1: UPSWEEP (Parallel Tree Reduction)
        // At step d, add elements 2^(d+1) distance apart
        // -------------------------------------------------------------
        for (int d = 0; (1 << d) < m; d++) {
            int stride = 1 << (d + 1);
            int halfStride = 1 << d;
            for (int k = 0; k < m; k += stride) {
                a[k + stride - 1] += a[k + halfStride - 1];
            }
        }

        // Total sum is now at root a[m - 1]
        // -------------------------------------------------------------
        // PHASE 2: DOWNSWEEP
        // Set root to 0, then traverse back down
        // -------------------------------------------------------------
        a[m - 1] = 0;

        for (int d = (int) (Math.log(m) / Math.log(2)) - 1; d >= 0; d--) {
            int stride = 1 << (d + 1);
            int halfStride = 1 << d;
            for (int k = 0; k < m; k += stride) {
                int left = a[k + halfStride - 1];
                a[k + halfStride - 1] = a[k + stride - 1];
                a[k + stride - 1] += left;
            }
        }

        int[] exclusive = Arrays.copyOf(a, n);
        int[] inclusive = new int[n];
        for (int i = 0; i < n; i++) {
            inclusive[i] = exclusive[i] + input[i];
        }

        return new ScanResult(exclusive, inclusive, n);
    }

    /**
     * Demonstrates Blelloch Parallel Scan with step details.
     */
    public static void demonstrate() {
        System.out.println("\n=== Blelloch Work-Efficient Parallel Prefix Scan Demonstration ===");
        // Daily hours spent by student on academic modules
        int[] studentDailyHours = {2, 4, 6, 8, 10, 12, 14};

        System.out.println("Input Student Daily Study Hours: " + Arrays.toString(studentDailyHours));
        System.out.println("Concept: Upsweep (Reduce) Phase -> Set Root 0 -> Downsweep Phase");

        ScanResult result = scan(studentDailyHours);

        System.out.println("\nExclusive Prefix Sums (Prior to current day):");
        System.out.println("  " + Arrays.toString(result.exclusiveScan));

        System.out.println("Inclusive Prefix Sums (Cumulative total including current day):");
        System.out.println("  " + Arrays.toString(result.inclusiveScan));

        System.out.println("\nAnalytical Interpretation:");
        for (int i = 0; i < studentDailyHours.length; i++) {
            System.out.printf("  Day %d: Worked %2d hrs | Cumulative To-Date: %2d hrs%n",
                    i + 1, studentDailyHours[i], result.inclusiveScan[i]);
        }
        System.out.println("Theoretical Metrics: Work W = O(n), Span S = O(log n)");
    }
}
