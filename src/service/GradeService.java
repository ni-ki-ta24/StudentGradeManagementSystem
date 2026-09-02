package service;

import dao.GradeDAO;
import dao.StudentDAO;
import model.Grade;
import model.Student;

import java.util.List;

public class GradeService {

    private GradeDAO gradeDAO;
    private StudentDAO studentDAO;

    public GradeService() {
        gradeDAO = new GradeDAO();
        studentDAO = new StudentDAO();
    }

    // Add Grade
    public boolean addGrade(Grade grade) {

        if (grade.getMarks() < 0 || grade.getMarks() > 100) {
            System.out.println("Marks must be between 0 and 100.");
            return false;
        }

        Student student = studentDAO.getStudentById(grade.getStudentId());

        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }

        return gradeDAO.addGrade(grade);
    }

    // Calculate Average
    public double calculateAverage(int studentId) {

        List<Grade> grades = gradeDAO.getGradesByStudentId(studentId);

        if (grades.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (Grade grade : grades) {
            total += grade.getMarks();
        }

        return total / grades.size();
    }

    // Calculate Total Marks
    public double calculateTotalMarks(int studentId) {

        List<Grade> grades = gradeDAO.getGradesByStudentId(studentId);

        double total = 0;

        for (Grade grade : grades) {
            total += grade.getMarks();
        }

        return total;
    }

    // Get Grade Letter
    public String calculateGrade(double marks) {

        if (marks >= 90) {
            return "A+";
        } else if (marks >= 80) {
            return "A";
        } else if (marks >= 70) {
            return "B";
        } else if (marks >= 60) {
            return "C";
        } else if (marks >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    // Student Report
    public void generateStudentReport(int studentId) {

        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        List<Grade> grades = gradeDAO.getGradesByStudentId(studentId);

        if (grades.isEmpty()) {
            System.out.println("No grades found for this student.");
            return;
        }

        System.out.println("\n========== STUDENT REPORT ==========");
        System.out.println("Student ID : " + student.getId());
        System.out.println("Name       : " + student.getName());
        System.out.println("Email      : " + student.getEmail());
        System.out.println("Course     : " + student.getCourse());
        System.out.println("Semester   : " + student.getSemester());

        System.out.println("\n----- Subject Wise Marks -----");

        for (Grade grade : grades) {
            System.out.println(
                    grade.getSubject() +
                    " : " + grade.getMarks() +
                    " | Grade: " + calculateGrade(grade.getMarks())
            );
        }

        double total = calculateTotalMarks(studentId);
        double average = calculateAverage(studentId);

        System.out.println("\nTotal Marks   : " + total);
        System.out.println("Average Marks : " + String.format("%.2f", average));
        System.out.println("Overall Grade : " + calculateGrade(average));

        System.out.println("====================================");
    }
}