package services;

import data.DataManager;
import m1_string.KMP;
import m1_string.RabinKarp;
import m3_dp.BitmaskDP;
import model.Course;
import utils.DisplayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service managing Course curriculum operations with integrated DSA algorithms:
 * - KMP and Rabin-Karp for course lookup
 * - Bitmask DP for optimal credit course selection under prerequisite constraints
 */
public class CourseService {
    private final DataManager dataManager;

    public CourseService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Course> getAllCourses() {
        return dataManager.getCourses();
    }

    public void displayAllCourses() {
        List<Course> courses = getAllCourses();
        DisplayUtils.printSubHeader("University Course Catalog (" + courses.size() + ")");
        String[] headers = {"Code", "Course Name", "Dept", "Credits", "Lead Faculty", "Prerequisites"};
        List<String[]> rows = new ArrayList<>();
        for (Course c : courses) {
            rows.add(new String[]{
                    c.getCourseCode(), c.getCourseName(), c.getDepartment(),
                    String.valueOf(c.getCredits()), c.getFaculty(),
                    c.getPrerequisites().isEmpty() ? "None" : String.join(", ", c.getPrerequisites())
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void searchCourses(String query) {
        DisplayUtils.printCard("Course Search via KMP & Rabin-Karp", "M1 / CO1",
                "KMP: O(n+m), Rabin-Karp: O(n+m)", "O(m) & O(1)", "Course Catalog Lookup");

        List<Course> courses = getAllCourses();
        List<Course> kmpMatches = new ArrayList<>();
        List<Course> rkMatches = new ArrayList<>();

        for (Course c : courses) {
            // Search course name via KMP
            if (!KMP.searchIgnoreCase(c.getCourseName(), query).isEmpty() ||
                !KMP.searchIgnoreCase(c.getDepartment(), query).isEmpty()) {
                kmpMatches.add(c);
            }
            // Search course code via Rabin-Karp
            if (!RabinKarp.search(c.getCourseCode().toLowerCase(), query.toLowerCase()).isEmpty()) {
                rkMatches.add(c);
            }
        }

        System.out.println("\nSearch Results for Query: \"" + query + "\"");
        if (kmpMatches.isEmpty() && rkMatches.isEmpty()) {
            DisplayUtils.printWarning("No courses found matching \"" + query + "\".");
            return;
        }

        List<Course> combined = new ArrayList<>(kmpMatches);
        for (Course c : rkMatches) {
            if (!combined.contains(c)) combined.add(c);
        }

        DisplayUtils.printSuccess("Found " + combined.size() + " course(s):");
        for (Course c : combined) {
            System.out.printf("  * %-8s | %-32s | %d Credits | Faculty: %s%n",
                    c.getCourseCode(), c.getCourseName(), c.getCredits(), c.getFaculty());
        }
    }

    public void planOptimalCourses(int creditLimit) {
        DisplayUtils.printCard("Optimal Course Selection via Bitmask DP", "M3 / CO3",
                "O(2^N * N)", "O(2^N)", "Degree Planning Under Prerequisite & Credit Constraints");

        List<Course> courses = getAllCourses();
        // Limit to first 12 courses for bitmask representation
        int limit = Math.min(courses.size(), 12);
        Map<String, Integer> codeToIdx = new HashMap<>();
        for (int i = 0; i < limit; i++) {
            codeToIdx.put(courses.get(i).getCourseCode(), i);
        }

        List<BitmaskDP.CourseOption> options = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            Course c = courses.get(i);
            int prereqMask = 0;
            for (String p : c.getPrerequisites()) {
                Integer pIdx = codeToIdx.get(p);
                if (pIdx != null) {
                    prereqMask |= (1 << pIdx);
                }
            }
            int value = c.getCredits() * 15 + (c.getDepartment().equals("AI") || c.getDepartment().equals("CSE") ? 10 : 5);
            options.add(new BitmaskDP.CourseOption(c.getCourseCode(), c.getCourseName(), c.getCredits(), value, prereqMask));
        }

        BitmaskDP.PlanResult plan = BitmaskDP.optimizeCourses(options, creditLimit);

        DisplayUtils.printSubHeader("Optimal Semester Course Schedule (Credit Cap: " + creditLimit + ")");
        System.out.println("Selected Bitmask State: 0b" + Integer.toBinaryString(plan.bestMask));
        System.out.println("Total Credits Allocated: " + plan.totalCredits + " / " + creditLimit);
        System.out.println("Academic Value Score:   " + plan.totalValue);

        String[] headers = {"Course Code", "Course Name", "Credits", "Prerequisites Satisfied"};
        List<String[]> rows = new ArrayList<>();
        for (BitmaskDP.CourseOption opt : plan.selectedCourses) {
            rows.add(new String[]{opt.code, opt.name, String.valueOf(opt.credits), "YES"});
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void addCourse(Course course) {
        for (Course c : dataManager.getCourses()) {
            if (c.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                DisplayUtils.printWarning("Course Code " + course.getCourseCode() + " already exists!");
                return;
            }
        }
        dataManager.getCourses().add(course);
        dataManager.saveCourses();
        DisplayUtils.printSuccess("Course " + course.getCourseCode() + " added successfully.");
    }
}
