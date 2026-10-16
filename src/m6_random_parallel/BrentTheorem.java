package m6_random_parallel;

// Input: Work W (total operations), Span S (critical path depth), Number of processors P
// Algorithm: Brent's Theorem Scheduling Analysis (Richard P. Brent 1974)
// Output: Theoretical execution time T_P <= (W - S)/P + S, Speedup S_P, and Processor Efficiency E_P
// Time Complexity: O(1)
// Space Complexity: O(1)
// EduTrack Application: Capacity planning and parallel pipeline speedup estimation for campus batch processing
public class BrentTheorem {

    public static class AnalysisResult {
        public final double work;
        public final double span;
        public final int processors;
        public final double theoreticalTime;
        public final double speedup;
        public final double efficiency;

        public AnalysisResult(double work, double span, int processors, double theoreticalTime, double speedup, double efficiency) {
            this.work = work;
            this.span = span;
            this.processors = processors;
            this.theoreticalTime = theoreticalTime;
            this.speedup = speedup;
            this.efficiency = efficiency;
        }
    }

    /**
     * Calculates Brent's Theorem bound: T_P <= (W - S)/P + S.
     */
    public static AnalysisResult analyze(double W, double S, int P) {
        if (P <= 0) P = 1;
        if (S > W) S = W; // Span cannot exceed total work

        double timeBound = ((W - S) / P) + S;
        double speedup = W / timeBound;
        double efficiency = speedup / P;

        return new AnalysisResult(W, S, P, timeBound, speedup, efficiency);
    }

    /**
     * Demonstrates Brent's theorem with formatted analysis.
     */
    public static void demonstrate() {
        System.out.println("\n=== Brent's Theorem Parallel Scheduling Demonstration ===");
        System.out.println("Theorem (Brent 1974):");
        System.out.println("  Given a parallel computation with Work W and Span (Depth) S,");
        System.out.println("  any greedy scheduler executes on P processors in time:");
        System.out.println("      T_P <= (W - S) / P + S");

        double sampleWork = 10000.0; // 10,000 units of computation
        double sampleSpan = 50.0;    // Critical path depth 50
        int[] processorCounts = {1, 2, 4, 8, 16, 32, 64};

        System.out.printf("\nAcademic Analytics Pipeline: Work W = %.0f, Span S = %.0f%n", sampleWork, sampleSpan);
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-12s | %-16s | %-12s | %-12s%n", "Processors P", "Time T_P (units)", "Speedup S_P", "Efficiency");
        System.out.println("------------------------------------------------------------------");

        for (int p : processorCounts) {
            AnalysisResult res = analyze(sampleWork, sampleSpan, p);
            System.out.printf("%-12d | %-16.2f | %-12.2fx | %-11.2f%%%n",
                    p, res.theoreticalTime, res.speedup, res.efficiency * 100.0);
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Observation: As P -> infinity, T_P approaches the fundamental span limit S = 50.0.");
        System.out.println("Complexity: O(1) analytical calculation.");
    }
}
