package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student enrolled in the university platform.
 */
public class Student {
    private String studentId;
    private String name;
    private String department;
    private int semester;
    private String email;
    private double gpa;
    private List<String> enrolledCourses;

    public Student(String studentId, String name, String department, int semester, String email, double gpa, List<String> enrolledCourses) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.semester = semester;
        this.email = email;
        this.gpa = gpa;
        this.enrolledCourses = (enrolledCourses != null) ? new ArrayList<>(enrolledCourses) : new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void enrollCourse(String courseCode) {
        if (!enrolledCourses.contains(courseCode)) {
            enrolledCourses.add(courseCode);
        }
    }

    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name=%-18s, Dept=%-5s, Sem=%d, GPA=%.2f, Email=%-24s, Courses=%s]",
                studentId, name, department, semester, gpa, email, String.join(";", enrolledCourses));
    }
}
