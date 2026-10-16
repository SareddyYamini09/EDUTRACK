package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an academic course offered by the university.
 */
public class Course {
    private String courseCode;
    private String courseName;
    private String department;
    private int credits;
    private String faculty; // Assigned faculty ID or Name
    private List<String> prerequisites;

    public Course(String courseCode, String courseName, String department, int credits, String faculty, List<String> prerequisites) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.department = department;
        this.credits = credits;
        this.faculty = (faculty != null) ? faculty : "Unassigned";
        this.prerequisites = (prerequisites != null) ? new ArrayList<>(prerequisites) : new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    @Override
    public String toString() {
        return String.format("Course[Code=%-8s, Name=%-30s, Dept=%-5s, Credits=%d, Faculty=%-10s, Prereqs=%s]",
                courseCode, courseName, department, credits, faculty, String.join(";", prerequisites));
    }
}
