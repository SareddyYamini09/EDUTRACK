package services;

import data.DataManager;
import m3_dp.MatrixChainMultiplication;
import m6_random_parallel.BlellochScan;
import m6_random_parallel.BrentTheorem;
import m6_random_parallel.ParallelReduce;
import m6_random_parallel.ReservoirSampling;
import model.AcademicActivity;
import model.Assignment;
import model.Student;
import utils.DisplayUtils;

import java.util.*;

/**
 * Service managing Academic Analytics Dashboard with integrated DSA algorithms:
 * - Parallel Reduce for computing real-time GPA and marks metrics
 * - Blelloch Scan for prefix activity progression
 * - Reservoir Sampling for auditing telemetry data streams
 * - Brent's Theorem for capacity modeling
 */
public class AnalyticsService {
    private final DataManager dataManager;

    public AnalyticsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void displayDashboard() {
        DisplayUtils.printHeader("EduTrack Academic Analytics Dashboard");

        List<Student> students = dataManager.getStudents();
        List<Assignment> assignments = dataManager.getAssignments();
        List<AcademicActivity> activities = dataManager.getActivities();

        // 1. Parallel Reduce on Student GPAs
        double[] gpas = new double[students.size()];
        for (int i = 0; i < students.size(); i++) {
            gpas[i] = students.get(i).getGpa();
        }
        ParallelReduce.AggregateResult gpaStats = ParallelReduce.reduce(gpas);

        // 2. Parallel Reduce on Assignment Marks
        double[] marks = new double[assignments.size()];
        for (int i = 0; i < assignments.size(); i++) {
            marks[i] = assignments.get(i).getMarks();
        }
        ParallelReduce.AggregateResult markStats = ParallelReduce.reduce(marks);

        // 3. Most Active Student
        Map<String, Integer> studentActivityCount = new HashMap<>();
        for (AcademicActivity a : activities) {
            studentActivityCount.put(a.getStudentId(), studentActivityCount.getOrDefault(a.getStudentId(), 0) + 1);
        }
        String topStudentId = "N/A";
        int maxActs = 0;
        for (Map.Entry<String, Integer> entry : studentActivityCount.entrySet()) {
            if (entry.getValue() > maxActs) {
                maxActs = entry.getValue();
                topStudentId = entry.getKey();
            }
        }
        String topStudentName = topStudentId;
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(topStudentId)) {
                topStudentName = s.getName() + " (" + topStudentId + ")";
                break;
            }
        }

        // 4. Most Popular Course (by enrollment count)
        Map<String, Integer> courseEnrollment = new HashMap<>();
        for (Student s : students) {
            for (String c : s.getEnrolledCourses()) {
                courseEnrollment.put(c, courseEnrollment.getOrDefault(c, 0) + 1);
            }
        }
        String topCourse = "N/A";
        int maxEnrolled = 0;
        for (Map.Entry<String, Integer> entry : courseEnrollment.entrySet()) {
            if (entry.getValue() > maxEnrolled) {
                maxEnrolled = entry.getValue();
                topCourse = entry.getKey();
            }
        }

        System.out.println("===========================================================================");
        System.out.printf("  Total Students Registered:   %-10d Total Faculty Members:     %d%n",
                students.size(), dataManager.getFaculty().size());
        System.out.printf("  Total Courses Offered:       %-10d Total Scheduled Exams:     %d%n",
                dataManager.getCourses().size(), dataManager.getExaminations().size());
        System.out.printf("  Total Assignment Records:    %-10d Campus Resources Count:   %d%n",
                assignments.size(), dataManager.getResources().size());
        System.out.printf("  Total Academic Activities:   %-10d%n", activities.size());
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("  Average University GPA:      %-10.2f Highest Recorded GPA:       %.2f%n",
                gpaStats.average, gpaStats.max);
        System.out.printf("  Minimum Recorded GPA:        %-10.2f GPA Standard Deviation:     [Computed]%n",
                gpaStats.min);
        System.out.printf("  Average Assignment Score:    %-10.2f Highest Assignment Mark:    %.2f%n",
                markStats.average, markStats.max);
        System.out.printf("  Most Active Student:         %s (%d logged activities)%n",
                topStudentName, maxActs);
        System.out.printf("  Most Popular Course:         %s (%d students enrolled)%n",
                topCourse, maxEnrolled);
        System.out.println("===========================================================================");

        // 5. Blelloch Parallel Scan Demonstration on Student Daily Workload
        System.out.println("\n[+] Cumulative Student Engagement Trend (via Blelloch Scan):");
        int sampleDays = Math.min(activities.size(), 8);
        int[] dailyHours = new int[sampleDays];
        for (int i = 0; i < sampleDays; i++) {
            dailyHours[i] = (int) Math.max(1, activities.get(i).getValue() / 10);
        }
        BlellochScan.ScanResult scanResult = BlellochScan.scan(dailyHours);
        System.out.printf("  Input Activity Values:   %s%n", Arrays.toString(dailyHours));
        System.out.printf("  Cumulative Engagements:  %s%n", Arrays.toString(scanResult.inclusiveScan));

        // 6. Reservoir Sampling on Live Activity Stream
        System.out.println("\n[+] Audited Telemetry Events (via Reservoir Sampling k=3):");
        List<AcademicActivity> sampleEvents = ReservoirSampling.sample(activities, 3);
        for (AcademicActivity a : sampleEvents) {
            System.out.printf("  * Event %s | Student %s | Action: %-22s | Time: %s%n",
                    a.getActivityId(), a.getStudentId(), a.getActivityType(), a.getTimestamp());
        }

        // 7. Parallel Scheduling Insights via Brent's Theorem
        System.out.println("\n[+] Batch Pipeline Scheduling Insights (via Brent's Theorem):");
        BrentTheorem.AnalysisResult brent = BrentTheorem.analyze(5000, 40, Runtime.getRuntime().availableProcessors());
        System.out.printf("  Cores: %d | Time Bound: %.1f units | Theoretical Speedup: %.2fx | Efficiency: %.1f%%%n",
                brent.processors, brent.theoreticalTime, brent.speedup, brent.efficiency * 100.0);
    }
}
