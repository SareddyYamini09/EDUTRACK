package model;

/**
 * Represents a scheduled examination in the university.
 */
public class Examination {
    private String examId;
    private String courseCode;
    private String date;
    private String time;
    private String room;
    private int capacity;

    public Examination(String examId, String courseCode, String date, String time, String room, int capacity) {
        this.examId = examId;
        this.courseCode = courseCode;
        this.date = date;
        this.time = time;
        this.room = room;
        this.capacity = capacity;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return String.format("Examination[ID=%-8s, Course=%-8s, Date=%-10s, Time=%-8s, Room=%-10s, Capacity=%d]",
                examId, courseCode, date, time, room, capacity);
    }
}
