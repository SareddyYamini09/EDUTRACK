package m5_np;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Input: 3-CNF Boolean formula where every clause contains exactly 3 literals
// Algorithm: 3-SAT Verification and DPLL Satisfiability Resolution
// Output: Satisfying truth model or UNSAT proof
// Time Complexity: O(2^n) worst case, NP-complete core problem (Cook-Levin Theorem)
// Space Complexity: O(n + m)
// EduTrack Application: Rigorous academic conflict scheduling under three-way faculty-room-timeslot constraints
public class ThreeSAT {

    public static class ThreeClause {
        public final int l1, l2, l3;

        public ThreeClause(int l1, int l2, int l3) {
            this.l1 = l1;
            this.l2 = l2;
            this.l3 = l3;
        }

        public SATSolver.Clause toSolverClause() {
            return new SATSolver.Clause(l1, l2, l3);
        }

        @Override
        public String toString() {
            return String.format("(%s OR %s OR %s)",
                    l1 > 0 ? "x" + l1 : "!x" + (-l1),
                    l2 > 0 ? "x" + l2 : "!x" + (-l2),
                    l3 > 0 ? "x" + l3 : "!x" + (-l3));
        }
    }

    private final List<ThreeClause> clauses = new ArrayList<>();

    public void addClause(int l1, int l2, int l3) {
        clauses.add(new ThreeClause(l1, l2, l3));
    }

    public List<ThreeClause> getClauses() {
        return clauses;
    }

    public Map<Integer, Boolean> solve() {
        SATSolver.CNFFormula formula = new SATSolver.CNFFormula();
        for (ThreeClause tc : clauses) {
            formula.addClause(tc.toSolverClause());
        }
        return SATSolver.solve(formula);
    }

    /**
     * Demonstrates 3-SAT problem representation and solution.
     */
    public static void demonstrate() {
        System.out.println("\n=== 3-SAT Satisfiability Demonstration ===");
        ThreeSAT sat = new ThreeSAT();
        // C1: (x1 OR x2 OR x3)
        // C2: (!x1 OR !x2 OR x3)
        // C3: (x1 OR !x3 OR x4)
        // C4: (!x2 OR !x3 OR !x4)
        sat.addClause(1, 2, 3);
        sat.addClause(-1, -2, 3);
        sat.addClause(1, -3, 4);
        sat.addClause(-2, -3, -4);

        System.out.println("3-CNF Formula:");
        for (int i = 0; i < sat.getClauses().size(); i++) {
            System.out.printf("  C%d: %s%n", i + 1, sat.getClauses().get(i));
        }

        Map<Integer, Boolean> assignment = sat.solve();
        if (assignment != null) {
            System.out.println("\nResult: FORMULA IS SATISFIABLE (SAT)");
            System.out.println("Variable Assignments:");
            for (Map.Entry<Integer, Boolean> entry : new TreeMap<>(assignment).entrySet()) {
                System.out.printf("  x%d = %s%n", entry.getKey(), entry.getValue() ? "TRUE" : "FALSE");
            }
        } else {
            System.out.println("\nResult: UNSATISFIABLE (UNSAT)");
        }
        System.out.println("Computational Complexity: NP-Complete (Cook-Levin Theorem 1971)");
    }
}
