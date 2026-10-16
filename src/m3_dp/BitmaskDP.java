package m3_dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Input: N candidate courses with credits, academic utility values, prerequisite bitmasks, and max credit limit C
// Algorithm: Bitmask Dynamic Programming over 2^N subset states with prerequisite validation
// Output: Optimal subset of courses maximizing academic value within credit constraints
// Time Complexity: O(2^N * N)
// Space Complexity: O(2^N)
// EduTrack Application: Student degree planning, selecting optimal feasible course combinations under prerequisites and credit limits
public class BitmaskDP {

    public static class CourseOption {
        public final String code;
        public final String name;
        public final int credits;
        public final int value; // Academic value / career weight
        public final int prereqMask;

        public CourseOption(String code, String name, int credits, int value, int prereqMask) {
            this.code = code;
            this.name = name;
            this.credits = credits;
            this.value = value;
            this.prereqMask = prereqMask;
        }
    }

    public static class PlanResult {
        public final int bestMask;
        public final int totalCredits;
        public final int totalValue;
        public final List<CourseOption> selectedCourses;

        public PlanResult(int bestMask, int totalCredits, int totalValue, List<CourseOption> selectedCourses) {
            this.bestMask = bestMask;
            this.totalCredits = totalCredits;
            this.totalValue = totalValue;
            this.selectedCourses = selectedCourses;
        }
    }

    /**
     * Finds the optimal feasible course combination within maxCredits.
     */
    public static PlanResult optimizeCourses(List<CourseOption> courses, int maxCredits) {
        int n = courses.size();
        int totalStates = 1 << n;

        int[] dpValue = new int[totalStates];
        int[] dpCredits = new int[totalStates];
        boolean[] feasible = new boolean[totalStates];

        Arrays.fill(dpValue, -1);
        dpValue[0] = 0;
        dpCredits[0] = 0;
        feasible[0] = true;

        int bestMask = 0;
        int maxVal = 0;

        for (int mask = 0; mask < totalStates; mask++) {
            if (!feasible[mask]) continue;

            // Check if this feasible subset is our best so far
            if (dpCredits[mask] <= maxCredits && dpValue[mask] > maxVal) {
                maxVal = dpValue[mask];
                bestMask = mask;
            }

            // Try adding next course
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {
                    // Check if prerequisites of course i are met in current mask
                    int prereq = courses.get(i).prereqMask;
                    if ((mask & prereq) == prereq) {
                        int nextMask = mask | (1 << i);
                        int nextCredits = dpCredits[mask] + courses.get(i).credits;
                        int nextVal = dpValue[mask] + courses.get(i).value;

                        if (nextCredits <= maxCredits) {
                            if (nextVal > dpValue[nextMask]) {
                                dpValue[nextMask] = nextVal;
                                dpCredits[nextMask] = nextCredits;
                                feasible[nextMask] = true;
                            }
                        }
                    }
                }
            }
        }

        List<CourseOption> selected = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((bestMask & (1 << i)) != 0) {
                selected.add(courses.get(i));
            }
        }

        return new PlanResult(bestMask, dpCredits[bestMask], maxVal, selected);
    }

    /**
     * Demonstrates Bitmask DP on a university curriculum planning problem.
     */
    public static void demonstrate() {
        System.out.println("\n=== Bitmask DP Feasible Course Selection Demonstration ===");
        int maxCredits = 12;

        List<CourseOption> catalog = new ArrayList<>();
        // Index 0: JAVA201 (no prereq)
        catalog.add(new CourseOption("JAVA201", "Core Java OOP", 3, 40, 0));
        // Index 1: DSA301 (prereq JAVA201 -> bit 0)
        catalog.add(new CourseOption("DSA301", "Data Structures", 4, 60, 1 << 0));
        // Index 2: DBMS201 (no prereq)
        catalog.add(new CourseOption("DBMS201", "Database Systems", 3, 35, 0));
        // Index 3: OS301 (prereq DSA301 -> bit 1)
        catalog.add(new CourseOption("OS301", "Operating Systems", 4, 55, 1 << 1));
        // Index 4: AI101 (no prereq)
        catalog.add(new CourseOption("AI101", "Intro to AI", 3, 45, 0));
        // Index 5: AI201 (prereq AI101 -> bit 4)
        catalog.add(new CourseOption("AI201", "Advanced AI", 4, 70, 1 << 4));

        System.out.println("Available Courses (Candidate Pool, Max Credits: " + maxCredits + "):");
        for (int i = 0; i < catalog.size(); i++) {
            CourseOption c = catalog.get(i);
            System.out.printf("  [%d] %-8s | Credits: %d | Academic Value: %2d | Prereq Mask: 0b%s%n",
                    i, c.code, c.credits, c.value, Integer.toBinaryString(c.prereqMask));
        }

        PlanResult result = optimizeCourses(catalog, maxCredits);

        System.out.println("\nOptimal Feasible Course Selection:");
        System.out.printf("Bitmask State: 0b%s (Decimal: %d)%n", Integer.toBinaryString(result.bestMask), result.bestMask);
        System.out.println("Selected Courses:");
        for (CourseOption c : result.selectedCourses) {
            System.out.printf("  * %-8s: %-25s (Credits: %d, Value: %d)%n", c.code, c.name, c.credits, c.value);
        }
        System.out.printf("Total Credits: %d / %d%n", result.totalCredits, maxCredits);
        System.out.printf("Total Academic Value Achieved: %d%n", result.totalValue);
        System.out.println("Complexity: O(2^N * N) = O(" + (1 << catalog.size()) * catalog.size() + ") operations");
    }
}
