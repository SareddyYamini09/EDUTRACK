package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a university faculty member.
 */
public class Faculty {
    private String facultyId;
    private String name;
    private String department;
    private String specialization;
    private List<String> availableCourses;

    public Faculty(String facultyId, String name, String department, String specialization, List<String> availableCourses) {
        this.facultyId = facultyId;
        this.name = name;
        this.department = department;
        this.specialization = specialization;
        this.availableCourses = (availableCourses != null) ? new ArrayList<>(availableCourses) : new ArrayList<>();
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(List<String> availableCourses) {
        this.availableCourses = availableCourses;
    }

    public void addCourse(String courseCode) {
        if (!availableCourses.contains(courseCode)) {
            availableCourses.add(courseCode);
        }
    }

    @Override
    public String toString() {
        return String.format("Faculty[ID=%s, Name=%-18s, Dept=%-5s, Specialization=%-22s, QualifiedCourses=%s]",
                facultyId, name, department, specialization, String.join(";", availableCourses));
    }
}
