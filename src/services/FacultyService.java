package services;

import data.DataManager;
import m4_flow.BipartiteMatching;
import m4_flow.KonigTheorem;
import model.Course;
import model.Faculty;
import utils.DisplayUtils;

import java.util.*;

/**
 * Service managing Faculty operations with integrated DSA algorithms:
 * - Bipartite Matching for optimal faculty-course teaching assignments
 * - König's Theorem for verifying min vertex cover coverage
 */
public class FacultyService {
    private final DataManager dataManager;

    public FacultyService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Faculty> getAllFaculty() {
        return dataManager.getFaculty();
    }

    public void displayAllFaculty() {
        List<Faculty> facultyList = getAllFaculty();
        DisplayUtils.printSubHeader("University Faculty Directory (" + facultyList.size() + ")");
        String[] headers = {"ID", "Name", "Department", "Specialization", "Qualified Courses"};
        List<String[]> rows = new ArrayList<>();
        for (Faculty f : facultyList) {
            rows.add(new String[]{
                    f.getFacultyId(), f.getName(), f.getDepartment(),
                    f.getSpecialization(), String.join(", ", f.getAvailableCourses())
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void allocateFacultyToCourses() {
        DisplayUtils.printCard("Faculty Course Allocation via Bipartite Matching", "M4 / CO4",
                "O(V * E)", "O(V)", "Automatic Course-Teacher Allocation");

        List<Faculty> facultyList = getAllFaculty();
        List<Course> courseList = dataManager.getCourses();

        List<String> fNames = new ArrayList<>();
        Map<String, List<String>> qual = new HashMap<>();

        for (Faculty f : facultyList) {
            fNames.add(f.getName());
            qual.put(f.getName(), f.getAvailableCourses());
        }

        List<String> cCodes = new ArrayList<>();
        for (Course c : courseList) {
            cCodes.add(c.getCourseCode());
        }

        BipartiteMatching.MatchingResult result = BipartiteMatching.computeMaxMatching(fNames, cCodes, qual);

        DisplayUtils.printSubHeader("Optimized Teaching Allocations (Max Matching Size = " + result.maxMatchingSize + ")");
        String[] headers = {"Course Code", "Assigned Faculty Professor"};
        List<String[]> rows = new ArrayList<>();
        for (Course c : courseList) {
            String assigned = result.courseToFaculty.get(c.getCourseCode());
            rows.add(new String[]{
                    c.getCourseCode() + " (" + c.getCourseName() + ")",
                    (assigned != null ? assigned : "[Unassigned - Needs Additional Qualified Staff]")
            });
        }
        DisplayUtils.printTable(headers, rows);

        // Run König's theorem demonstration verification
        DisplayUtils.printInfo("Verifying minimum oversight committee via König's Theorem...");
        KonigTheorem.KonigResult konigRes = KonigTheorem.computeCover(fNames, cCodes, qual);
        System.out.println("  * Maximum Matching Size:     " + konigRes.matching.size());
        System.out.println("  * Minimum Vertex Cover Size: " + konigRes.minVertexCover.size());
        System.out.println("  * König's Duality Validated: " + (konigRes.sizeVerified ? "YES (|M| == |C|)" : "NO"));
    }

    public void addFaculty(Faculty faculty) {
        for (Faculty f : dataManager.getFaculty()) {
            if (f.getFacultyId().equalsIgnoreCase(faculty.getFacultyId())) {
                DisplayUtils.printWarning("Faculty ID " + faculty.getFacultyId() + " already exists!");
                return;
            }
        }
        dataManager.getFaculty().add(faculty);
        dataManager.saveFaculty();
        DisplayUtils.printSuccess("Faculty " + faculty.getName() + " added successfully.");
    }
}
