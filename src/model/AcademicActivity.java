package model;

/**
 * Represents an academic activity event performed by a student.
 */
public class AcademicActivity {
    private String activityId;
    private String studentId;
    private String activityType;
    private String timestamp;
    private double value;

    public AcademicActivity(String activityId, String studentId, String activityType, String timestamp, double value) {
        this.activityId = activityId;
        this.studentId = studentId;
        this.activityType = activityType;
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Activity[ID=%-8s, Student=%-8s, Type=%-20s, Value=%6.1f, Time=%s]",
                activityId, studentId, activityType, value, timestamp);
    }
}
