package m6_random_parallel;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// Input: Large array of student performance values (grades, exam marks, course points)
// Algorithm: Parallel Tree Reduction (Divide and Conquer via ForkJoin framework)
// Output: Aggregate metrics: Sum, Mean/Average, Maximum, Minimum
// Time Complexity: Work O(n), Span O(log n) with P processors
// Space Complexity: O(log n) recursion call tree depth
// EduTrack Application: University-wide real-time cohort analytics, GPA distributions, and cohort averages
public class ParallelReduce {

    public static class AggregateResult {
        public final double sum;
        public final double average;
        public final double min;
        public final double max;
        public final int count;

        public AggregateResult(double sum, double min, double max, int count) {
            this.sum = sum;
            this.count = count;
            this.average = count > 0 ? (sum / count) : 0.0;
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return String.format("Count=%d, Sum=%.2f, Mean=%.2f, Min=%.2f, Max=%.2f",
                    count, sum, average, min, max);
        }
    }

    private static class PartialStats {
        double sum;
        double min;
        double max;
        int count;

        PartialStats(double sum, double min, double max, int count) {
            this.sum = sum;
            this.min = min;
            this.max = max;
            this.count = count;
        }
    }

    private static class ReduceTask extends RecursiveTask<PartialStats> {
        private static final int THRESHOLD = 1000;
        private final double[] data;
        private final int start;
        private final int end;

        public ReduceTask(double[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        protected PartialStats compute() {
            if (end - start <= THRESHOLD) {
                double sum = 0;
                double min = Double.MAX_VALUE;
                double max = -Double.MAX_VALUE;
                for (int i = start; i < end; i++) {
                    double val = data[i];
                    sum += val;
                    if (val < min) min = val;
                    if (val > max) max = val;
                }
                return new PartialStats(sum, min, max, end - start);
            } else {
                int mid = (start + end) / 2;
                ReduceTask left = new ReduceTask(data, start, mid);
                ReduceTask right = new ReduceTask(data, mid, end);
                left.fork();
                PartialStats r = right.compute();
                PartialStats l = left.join();

                return new PartialStats(
                        l.sum + r.sum,
                        Math.min(l.min, r.min),
                        Math.max(l.max, r.max),
                        l.count + r.count
                );
            }
        }
    }

    /**
     * Executes parallel reduction across the array.
     */
    public static AggregateResult reduce(double[] data) {
        if (data == null || data.length == 0) {
            return new AggregateResult(0, 0, 0, 0);
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        PartialStats stats = pool.invoke(new ReduceTask(data, 0, data.length));
        return new AggregateResult(stats.sum, stats.min, stats.max, stats.count);
    }

    /**
     * Demonstrates Parallel Reduction on student assessment marks.
     */
    public static void demonstrate() {
        System.out.println("\n=== Parallel Tree Reduction Demonstration ===");
        double[] marks = {70.0, 85.5, 90.0, 75.0, 88.0, 94.5, 82.0, 96.0};

        System.out.println("Student Exam Scores: " + Arrays.toString(marks));
        System.out.println("Concept: Binary Reduction Tree combining node statistics across cores in O(log n) span.");

        AggregateResult stats = reduce(marks);

        System.out.println("\nParallel Reduction Aggregate Metrics:");
        System.out.printf("  Total Submissions Count: %d%n", stats.count);
        System.out.printf("  Sum Total Score:         %.2f%n", stats.sum);
        System.out.printf("  Average Cohort Mark:     %.2f%n", stats.average);
        System.out.printf("  Minimum Score:           %.2f%n", stats.min);
        System.out.printf("  Maximum Score:           %.2f%n", stats.max);
        System.out.println("Complexity: Work W = O(n), Span S = O(log n), Processors P = " + Runtime.getRuntime().availableProcessors());
    }
}
