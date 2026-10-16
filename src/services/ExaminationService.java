package services;

import data.DataManager;
import m5_np.SATSolver;
import m5_np.VertexCoverApproximation;
import model.Course;
import model.Examination;
import model.Student;
import utils.DisplayUtils;

import java.util.*;

/**
 * Service managing University Examinations with integrated DSA algorithms:
 * - Graph conflict building for overlapping exam schedules
 * - Vertex Cover 2-Approximation for minimal rescheduling set
 * - SAT Solver for conflict constraint satisfiability
 */
public class ExaminationService {
    private final DataManager dataManager;

    public ExaminationService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Examination> getAllExaminations() {
        return dataManager.getExaminations();
    }

    public void displayAllExaminations() {
        List<Examination> exams = getAllExaminations();
        DisplayUtils.printSubHeader("University Examination Timetable (" + exams.size() + ")");
        String[] headers = {"Exam ID", "Course Code", "Date", "Time Slot", "Room", "Capacity"};
        List<String[]> rows = new ArrayList<>();
        for (Examination e : exams) {
            rows.add(new String[]{
                    e.getExamId(), e.getCourseCode(), e.getDate(),
                    e.getTime(), e.getRoom(), String.valueOf(e.getCapacity())
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void detectAndResolveConflicts() {
        DisplayUtils.printCard("Exam Conflict Minimization via Vertex Cover 2-Approximation", "M5 / CO5",
                "O(V + E)", "O(V + E)", "Examination Conflict Resolution & Timetable Optimization");

        List<Examination> exams = getAllExaminations();
        List<Student> students = dataManager.getStudents();

        List<String> examCodes = new ArrayList<>();
        for (Examination e : exams) examCodes.add(e.getCourseCode());

        // Build conflict edges: two exams conflict if they are on the same date/time OR have common students
        List<String[]> conflictEdges = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++) {
            for (int j = i + 1; j < exams.size(); j++) {
                Examination e1 = exams.get(i);
                Examination e2 = exams.get(j);

                boolean sameTime = e1.getDate().equalsIgnoreCase(e2.getDate()) && e1.getTime().equalsIgnoreCase(e2.getTime());
                boolean sharedStudent = false;

                for (Student s : students) {
                    if (s.getEnrolledCourses().contains(e1.getCourseCode()) && s.getEnrolledCourses().contains(e2.getCourseCode())) {
                        sharedStudent = true;
                        break;
                    }
                }

                if (sameTime || (sharedStudent && e1.getDate().equalsIgnoreCase(e2.getDate()))) {
                    conflictEdges.add(new String[]{e1.getCourseCode(), e2.getCourseCode()});
                }
            }
        }

        System.out.println("Exam Conflict Graph Generated:");
        System.out.println("Total Scheduled Examinations: " + examCodes.size());
        System.out.println("Identified Direct Conflicts:  " + conflictEdges.size());
        for (String[] edge : conflictEdges) {
            System.out.printf("  [Conflict] %s <---> %s (Overlapping schedule/enrolled students)%n", edge[0], edge[1]);
        }

        if (conflictEdges.isEmpty()) {
            DisplayUtils.printSuccess("Zero exam conflicts detected! Timetable is fully conflict-free.");
            return;
        }

        // Apply Vertex Cover 2-Approximation
        VertexCoverApproximation.ApproxResult approx = VertexCoverApproximation.compute2Approximation(examCodes, conflictEdges);

        DisplayUtils.printSubHeader("Recommended Minimum Reschedule Set (Vertex Cover)");
        System.out.println("Exams that must be relocated to new timeslots to clear ALL conflicts:");
        for (String c : approx.vertexCover) {
            System.out.println("  * " + c);
        }
        System.out.printf("Approximate Reschedule Count: %d (Guaranteed <= 2x minimum)%n", approx.approxSize);

        // SAT constraint check
        System.out.println("\nValidating 2-Slot Satisfiability via SAT Solver...");
        SATSolver.CNFFormula cnf = new SATSolver.CNFFormula();
        // Variables: x_i represents Course i in Slot 1 (True) or Slot 2 (False)
        // If conflict between Course i and Course j, cannot be both in Slot 1: (!x_i OR !x_j)
        // and cannot be both in Slot 2: (x_i OR x_j)
        Map<String, Integer> varMap = new HashMap<>();
        int id = 1;
        for (String code : examCodes) varMap.put(code, id++);

        for (String[] edge : conflictEdges) {
            int u = varMap.get(edge[0]);
            int v = varMap.get(edge[1]);
            cnf.addClause(new SATSolver.Clause(-u, -v)); // Not both in Slot 1
            cnf.addClause(new SATSolver.Clause(u, v));   // Not both in Slot 2
        }

        Map<Integer, Boolean> satModel = SATSolver.solve(cnf);
        if (satModel != null) {
            DisplayUtils.printSuccess("2-Timeslot SAT Schedule Found! Exams can be partitioned into 2 distinct slots without conflict.");
        } else {
            DisplayUtils.printWarning("2 slots are insufficient for these conflicts. Requires >= 3 timeslots.");
        }
    }

    public void addExamination(Examination examination) {
        for (Examination e : dataManager.getExaminations()) {
            if (e.getExamId().equalsIgnoreCase(examination.getExamId())) {
                DisplayUtils.printWarning("Examination ID " + examination.getExamId() + " already exists!");
                return;
            }
        }
        dataManager.getExaminations().add(examination);
        dataManager.saveExaminations();
        DisplayUtils.printSuccess("Examination " + examination.getExamId() + " scheduled successfully.");
    }
}
