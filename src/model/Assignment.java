package model;

/**
 * Represents a student assignment submission in EduTrack.
 */
public class Assignment {
    private String assignmentId;
    private String courseCode;
    private String title;
    private String description;
    private String submissionDate;
    private String studentId;
    private double marks;

    public Assignment(String assignmentId, String courseCode, String title, String description, String submissionDate, String studentId, double marks) {
        this.assignmentId = assignmentId;
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.submissionDate = submissionDate;
        this.studentId = studentId;
        this.marks = marks;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return String.format("Assignment[ID=%-8s, Course=%-8s, Title=%-25s, Student=%-8s, Marks=%5.1f, Date=%s]",
                assignmentId, courseCode, title, studentId, marks, submissionDate);
    }
}
