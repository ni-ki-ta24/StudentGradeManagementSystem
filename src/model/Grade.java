package model;

public class Grade {

    private int id;
    private int studentId;
    private String subject;
    private double marks;

    // Default Constructor
    public Grade() {
    }

    // Parameterized Constructor
    public Grade(int id, int studentId, String subject, double marks) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    // Constructor without ID
    public Grade(int studentId, String subject, double marks) {
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subject='" + subject + '\'' +
                ", marks=" + marks +
                '}';
    }
}