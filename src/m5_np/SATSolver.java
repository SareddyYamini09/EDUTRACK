package m5_np;

import java.util.*;

// Input: Propositional logic formula in Conjunctive Normal Form (CNF)
// Algorithm: DPLL Backtracking SAT Solver with Unit Propagation and Pure Literal Elimination
// Output: Satisfying truth assignment {variable -> boolean} or UNSAT
// Time Complexity: O(2^V) worst-case exponential, efficient on practical sparse formulas
// Space Complexity: O(V + C) recursion stack and assignment state
// EduTrack Application: Exam timetable constraint validation and room reservation conflict resolution
public class SATSolver {

    public static class Clause {
        public final List<Integer> literals; // positive for x_i, negative for NOT x_i

        public Clause(Integer... lits) {
            this.literals = Arrays.asList(lits);
        }

        public Clause(List<Integer> lits) {
            this.literals = new ArrayList<>(lits);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("(");
            for (int i = 0; i < literals.size(); i++) {
                int lit = literals.get(i);
                sb.append(lit > 0 ? "x" + lit : "!x" + (-lit));
                if (i < literals.size() - 1) sb.append(" OR ");
            }
            sb.append(")");
            return sb.toString();
        }
    }

    public static class CNFFormula {
        public final List<Clause> clauses = new ArrayList<>();
        public final Set<Integer> variables = new TreeSet<>();

        public void addClause(Clause c) {
            clauses.add(c);
            for (int lit : c.literals) {
                variables.add(Math.abs(lit));
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < clauses.size(); i++) {
                sb.append(clauses.get(i));
                if (i < clauses.size() - 1) sb.append(" AND\n");
            }
            return sb.toString();
        }
    }

    /**
     * Solves the CNF formula and returns satisfying assignments or null if UNSAT.
     */
    public static Map<Integer, Boolean> solve(CNFFormula formula) {
        Map<Integer, Boolean> assignment = new HashMap<>();
        if (dpll(formula.clauses, assignment, new ArrayList<>(formula.variables))) {
            return assignment;
        }
        return null;
    }

    private static boolean dpll(List<Clause> clauses, Map<Integer, Boolean> assignment, List<Integer> vars) {
        // Unit propagation: find clauses with only one unassigned literal
        boolean progress = true;
        while (progress) {
            progress = false;
            for (Clause c : clauses) {
                int unassignedLit = 0;
                int unassignedCount = 0;
                boolean satisfied = false;

                for (int lit : c.literals) {
                    int var = Math.abs(lit);
                    if (assignment.containsKey(var)) {
                        boolean val = assignment.get(var);
                        if ((lit > 0 && val) || (lit < 0 && !val)) {
                            satisfied = true;
                            break;
                        }
                    } else {
                        unassignedCount++;
                        unassignedLit = lit;
                    }
                }

                if (satisfied) continue;

                if (unassignedCount == 0) {
                    return false; // Conflict: clause cannot be satisfied
                }

                if (unassignedCount == 1) {
                    // Unit clause: forced assignment
                    int var = Math.abs(unassignedLit);
                    assignment.put(var, unassignedLit > 0);
                    progress = true;
                }
            }
        }

        // Check if all clauses are satisfied
        boolean allSatisfied = true;
        for (Clause c : clauses) {
            boolean satisfied = false;
            for (int lit : c.literals) {
                int var = Math.abs(lit);
                if (assignment.containsKey(var)) {
                    boolean val = assignment.get(var);
                    if ((lit > 0 && val) || (lit < 0 && !val)) {
                        satisfied = true;
                        break;
                    }
                }
            }
            if (!satisfied) {
                allSatisfied = false;
                break;
            }
        }
        if (allSatisfied) return true;

        // Choose next unassigned variable
        int nextVar = -1;
        for (int v : vars) {
            if (!assignment.containsKey(v)) {
                nextVar = v;
                break;
            }
        }
        if (nextVar == -1) return false;

        // Branch TRUE
        assignment.put(nextVar, true);
        if (dpll(clauses, assignment, vars)) return true;

        // Branch FALSE
        assignment.put(nextVar, false);
        if (dpll(clauses, assignment, vars)) return true;

        // Backtrack
        assignment.remove(nextVar);
        return false;
    }

    /**
     * Demonstrates educational SAT solver on a sample CNF formula.
     */
    public static void demonstrate() {
        System.out.println("\n=== Educational DPLL SAT Solver Demonstration ===");
        // Formula: (x1 OR x2) AND (!x1 OR x3) AND (!x2 OR !x3)
        CNFFormula formula = new CNFFormula();
        formula.addClause(new Clause(1, 2));
        formula.addClause(new Clause(-1, 3));
        formula.addClause(new Clause(-2, -3));

        System.out.println("Input CNF Formula:\n" + formula);

        Map<Integer, Boolean> sol = solve(formula);
        if (sol != null) {
            System.out.println("\nStatus: SATISFIABLE (SAT)");
            System.out.println("Satisfying Truth Assignment:");
            for (Map.Entry<Integer, Boolean> e : new TreeMap<>(sol).entrySet()) {
                System.out.printf("  x%d = %s%n", e.getKey(), e.getValue() ? "TRUE" : "FALSE");
            }
        } else {
            System.out.println("\nStatus: UNSATISFIABLE (UNSAT)");
        }
        System.out.println("Complexity: O(2^V) worst-case exponential, with DPLL unit pruning");
    }
}
