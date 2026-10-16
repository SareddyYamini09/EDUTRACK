package services;

import data.DataManager;
import m1_string.KMP;
import m3_dp.LevenshteinDistance;
import m6_random_parallel.RandomizedQuickSort;
import model.Student;
import utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service managing Student operations with integrated DSA algorithms:
 * - KMP for student name search
 * - Levenshtein Distance for typo auto-correction
 * - Randomized QuickSort for merit GPA ranking
 */
public class StudentService {
    private final DataManager dataManager;

    public StudentService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Student> getAllStudents() {
        return dataManager.getStudents();
    }

    public void displayAllStudents() {
        List<Student> students = getAllStudents();
        DisplayUtils.printSubHeader("All Registered Students (" + students.size() + ")");
        String[] headers = {"ID", "Name", "Dept", "Sem", "GPA", "Email", "Enrolled Courses"};
        List<String[]> rows = new ArrayList<>();
        for (Student s : students) {
            rows.add(new String[]{
                    s.getStudentId(), s.getName(), s.getDepartment(),
                    String.valueOf(s.getSemester()), String.format("%.2f", s.getGpa()),
                    s.getEmail(), String.join(", ", s.getEnrolledCourses())
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void searchStudent(String query) {
        DisplayUtils.printCard("Student Search with KMP & Levenshtein Correction", "M1 & M3 / CO1 & CO3",
                "KMP: O(n+m), Levenshtein: O(n*m)", "O(m)", "Student Records Inquiry");

        List<Student> students = getAllStudents();
        List<Student> matched = new ArrayList<>();

        // Try exact substring match using KMP
        for (Student s : students) {
            if (!KMP.searchIgnoreCase(s.getName(), query).isEmpty() ||
                !KMP.searchIgnoreCase(s.getStudentId(), query).isEmpty() ||
                !KMP.searchIgnoreCase(s.getDepartment(), query).isEmpty()) {
                matched.add(s);
            }
        }

        if (!matched.isEmpty()) {
            DisplayUtils.printSuccess("Found " + matched.size() + " student(s) matching \"" + query + "\" via KMP:");
            for (Student s : matched) {
                System.out.println("  * " + s);
            }
        } else {
            DisplayUtils.printWarning("No exact matches found for \"" + query + "\".");

            // Apply Levenshtein Distance to find closest student name
            List<String> names = new ArrayList<>();
            for (Student s : students) names.add(s.getName());
            String suggestion = LevenshteinDistance.findClosestMatch(query, names);
            int dist = LevenshteinDistance.compute(query.toLowerCase(), suggestion.toLowerCase());

            System.out.printf("  [?] Did you mean: \"%s\"? (Levenshtein Edit Distance = %d)%n", suggestion, dist);
            for (Student s : students) {
                if (s.getName().equalsIgnoreCase(suggestion)) {
                    System.out.println("  * Suggested Match: " + s);
                    break;
                }
            }
        }
    }

    public void rankStudentsByGPA() {
        DisplayUtils.printCard("Randomized QuickSort Student Ranking", "M6 / CO6",
                "O(n log n) expected", "O(log n) stack", "Merit List & Scholarship Generation");

        List<Student> students = getAllStudents();
        List<RandomizedQuickSort.Record<Student>> records = new ArrayList<>();
        for (Student s : students) {
            records.add(new RandomizedQuickSort.Record<>(s.getStudentId(), s.getName(), s.getGpa(), s));
        }

        // Sort descending
        RandomizedQuickSort.sortRecords(records, false);

        DisplayUtils.printSubHeader("University Academic Merit Honor Roll (Sorted by GPA)");
        String[] headers = {"Rank", "Student ID", "Name", "Department", "GPA"};
        List<String[]> rows = new ArrayList<>();
        int rank = 1;
        for (RandomizedQuickSort.Record<Student> r : records) {
            rows.add(new String[]{
                    String.valueOf(rank++), r.id, r.name, r.data.getDepartment(), String.format("%.2f", r.score)
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void addStudent(Student student) {
        // Prevent duplicate ID
        for (Student s : dataManager.getStudents()) {
            if (s.getStudentId().equalsIgnoreCase(student.getStudentId())) {
                DisplayUtils.printWarning("Student ID " + student.getStudentId() + " already exists!");
                return;
            }
        }
        dataManager.getStudents().add(student);
        dataManager.saveStudents();
        DisplayUtils.printSuccess("Student " + student.getName() + " added successfully.");
    }
}
