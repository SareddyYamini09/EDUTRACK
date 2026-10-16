package data;

import model.*;
import utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataManager handles persistent CSV storage and automatic fallback to SampleData.
 * Ensures the application is resilient against missing or corrupted files.
 */
public class DataManager {
    private static DataManager instance;

    private List<Student> students;
    private List<Faculty> faculty;
    private List<Course> courses;
    private List<Assignment> assignments;
    private List<Examination> examinations;
    private List<Resource> resources;
    private List<AcademicActivity> activities;

    private final String dataDir = "data" + File.separator;

    private DataManager() {
        students = new ArrayList<>();
        faculty = new ArrayList<>();
        courses = new ArrayList<>();
        assignments = new ArrayList<>();
        examinations = new ArrayList<>();
        resources = new ArrayList<>();
        activities = new ArrayList<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void initialize() {
        loadData();
    }

    public void loadData() {
        // 1. Students
        List<String[]> sRows = FileUtils.readCsv(dataDir + "students.csv");
        if (sRows.isEmpty() || sRows.size() <= 1) {
            students = SampleData.getInitialStudents();
            saveStudents();
        } else {
            students.clear();
            for (int i = 1; i < sRows.size(); i++) {
                String[] r = sRows.get(i);
                if (r.length >= 7) {
                    List<String> enrolled = r[6].isEmpty() ? new ArrayList<>() : Arrays.asList(r[6].split(";"));
                    students.add(new Student(r[0], r[1], r[2], Integer.parseInt(r[3]), r[4], Double.parseDouble(r[5]), enrolled));
                }
            }
        }

        // 2. Faculty
        List<String[]> fRows = FileUtils.readCsv(dataDir + "faculty.csv");
        if (fRows.isEmpty() || fRows.size() <= 1) {
            faculty = SampleData.getInitialFaculty();
            saveFaculty();
        } else {
            faculty.clear();
            for (int i = 1; i < fRows.size(); i++) {
                String[] r = fRows.get(i);
                if (r.length >= 5) {
                    List<String> cList = r[4].isEmpty() ? new ArrayList<>() : Arrays.asList(r[4].split(";"));
                    faculty.add(new Faculty(r[0], r[1], r[2], r[3], cList));
                }
            }
        }

        // 3. Courses
        List<String[]> cRows = FileUtils.readCsv(dataDir + "courses.csv");
        if (cRows.isEmpty() || cRows.size() <= 1) {
            courses = SampleData.getInitialCourses();
            saveCourses();
        } else {
            courses.clear();
            for (int i = 1; i < cRows.size(); i++) {
                String[] r = cRows.get(i);
                if (r.length >= 6) {
                    List<String> prereqs = r[5].isEmpty() ? new ArrayList<>() : Arrays.asList(r[5].split(";"));
                    courses.add(new Course(r[0], r[1], r[2], Integer.parseInt(r[3]), r[4], prereqs));
                }
            }
        }

        // 4. Assignments
        List<String[]> aRows = FileUtils.readCsv(dataDir + "assignments.csv");
        if (aRows.isEmpty() || aRows.size() <= 1) {
            assignments = SampleData.getInitialAssignments();
            saveAssignments();
        } else {
            assignments.clear();
            for (int i = 1; i < aRows.size(); i++) {
                String[] r = aRows.get(i);
                if (r.length >= 7) {
                    assignments.add(new Assignment(r[0], r[1], r[2], r[3], r[4], r[5], Double.parseDouble(r[6])));
                }
            }
        }

        // 5. Examinations
        List<String[]> eRows = FileUtils.readCsv(dataDir + "examinations.csv");
        if (eRows.isEmpty() || eRows.size() <= 1) {
            examinations = SampleData.getInitialExaminations();
            saveExaminations();
        } else {
            examinations.clear();
            for (int i = 1; i < eRows.size(); i++) {
                String[] r = eRows.get(i);
                if (r.length >= 6) {
                    examinations.add(new Examination(r[0], r[1], r[2], r[3], r[4], Integer.parseInt(r[5])));
                }
            }
        }

        // 6. Resources
        List<String[]> resRows = FileUtils.readCsv(dataDir + "resources.csv");
        if (resRows.isEmpty() || resRows.size() <= 1) {
            resources = SampleData.getInitialResources();
            saveResources();
        } else {
            resources.clear();
            for (int i = 1; i < resRows.size(); i++) {
                String[] r = resRows.get(i);
                if (r.length >= 6) {
                    resources.add(new Resource(r[0], r[1], r[2], r[3], Integer.parseInt(r[4]), Boolean.parseBoolean(r[5])));
                }
            }
        }

        // 7. Activities
        List<String[]> actRows = FileUtils.readCsv(dataDir + "activities.csv");
        if (actRows.isEmpty() || actRows.size() <= 1) {
            activities = SampleData.getInitialActivities();
            saveActivities();
        } else {
            activities.clear();
            for (int i = 1; i < actRows.size(); i++) {
                String[] r = actRows.get(i);
                if (r.length >= 5) {
                    activities.add(new AcademicActivity(r[0], r[1], r[2], r[3], Double.parseDouble(r[4])));
                }
            }
        }
    }

    public void saveAll() {
        saveStudents();
        saveFaculty();
        saveCourses();
        saveAssignments();
        saveExaminations();
        saveResources();
        saveActivities();
    }

    public void saveStudents() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"studentId", "name", "department", "semester", "email", "GPA", "enrolledCourses"});
        for (Student s : students) {
            rows.add(new String[]{s.getStudentId(), s.getName(), s.getDepartment(), String.valueOf(s.getSemester()),
                    s.getEmail(), String.valueOf(s.getGpa()), String.join(";", s.getEnrolledCourses())});
        }
        FileUtils.writeCsv(dataDir + "students.csv", rows);
    }

    public void saveFaculty() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"facultyId", "name", "department", "specialization", "availableCourses"});
        for (Faculty f : faculty) {
            rows.add(new String[]{f.getFacultyId(), f.getName(), f.getDepartment(), f.getSpecialization(),
                    String.join(";", f.getAvailableCourses())});
        }
        FileUtils.writeCsv(dataDir + "faculty.csv", rows);
    }

    public void saveCourses() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"courseCode", "courseName", "department", "credits", "faculty", "prerequisites"});
        for (Course c : courses) {
            rows.add(new String[]{c.getCourseCode(), c.getCourseName(), c.getDepartment(), String.valueOf(c.getCredits()),
                    c.getFaculty(), String.join(";", c.getPrerequisites())});
        }
        FileUtils.writeCsv(dataDir + "courses.csv", rows);
    }

    public void saveAssignments() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"assignmentId", "courseCode", "title", "description", "submissionDate", "studentId", "marks"});
        for (Assignment a : assignments) {
            rows.add(new String[]{a.getAssignmentId(), a.getCourseCode(), a.getTitle(), a.getDescription(),
                    a.getSubmissionDate(), a.getStudentId(), String.valueOf(a.getMarks())});
        }
        FileUtils.writeCsv(dataDir + "assignments.csv", rows);
    }

    public void saveExaminations() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"examId", "courseCode", "date", "time", "room", "capacity"});
        for (Examination e : examinations) {
            rows.add(new String[]{e.getExamId(), e.getCourseCode(), e.getDate(), e.getTime(), e.getRoom(), String.valueOf(e.getCapacity())});
        }
        FileUtils.writeCsv(dataDir + "examinations.csv", rows);
    }

    public void saveResources() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"resourceId", "resourceName", "resourceType", "location", "capacity", "availability"});
        for (Resource r : resources) {
            rows.add(new String[]{r.getResourceId(), r.getResourceName(), r.getResourceType(), r.getLocation(),
                    String.valueOf(r.getCapacity()), String.valueOf(r.isAvailable())});
        }
        FileUtils.writeCsv(dataDir + "resources.csv", rows);
    }

    public void saveActivities() {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"activityId", "studentId", "activityType", "timestamp", "value"});
        for (AcademicActivity a : activities) {
            rows.add(new String[]{a.getActivityId(), a.getStudentId(), a.getActivityType(), a.getTimestamp(), String.valueOf(a.getValue())});
        }
        FileUtils.writeCsv(dataDir + "activities.csv", rows);
    }

    public List<Student> getStudents() { return students; }
    public List<Faculty> getFaculty() { return faculty; }
    public List<Course> getCourses() { return courses; }
    public List<Assignment> getAssignments() { return assignments; }
    public List<Examination> getExaminations() { return examinations; }
    public List<Resource> getResources() { return resources; }
    public List<AcademicActivity> getActivities() { return activities; }
}
