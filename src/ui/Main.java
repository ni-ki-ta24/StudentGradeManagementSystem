package ui;

import dao.GradeDAO;
import dao.StudentDAO;
import model.Grade;
import model.Student;
import service.GradeService;
import util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static StudentDAO studentDAO = new StudentDAO();
    private static GradeDAO gradeDAO = new GradeDAO();
    private static GradeService gradeService = new GradeService();

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("     STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        while (true) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    addGrade();
                    break;

                case 7:
                    viewStudentGrades();
                    break;

                case 8:
                    updateGrade();
                    break;

                case 9:
                    deleteGrade();
                    break;

                case 10:
                    generateStudentReport();
                    break;

                case 11:
                    System.out.println("\nThank you for using Student Grade Management System!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // ================= MENU =================

    private static void displayMenu() {

        System.out.println("\n--------------- MAIN MENU ----------------");

        System.out.println("1.  Add Student");
        System.out.println("2.  View All Students");
        System.out.println("3.  Search Student");
        System.out.println("4.  Update Student");
        System.out.println("5.  Delete Student");

        System.out.println("6.  Add Grade");
        System.out.println("7.  View Student Grades");
        System.out.println("8.  Update Grade");
        System.out.println("9.  Delete Grade");

        System.out.println("10. Generate Student Report");

        System.out.println("11. Exit");

        System.out.println("------------------------------------------");
    }

    // ================= ADD STUDENT =================

    private static void addStudent() {

        System.out.println("\n========== ADD STUDENT ==========");

        String name;

        while (true) {

            name = readString("Enter student name: ");

            if (InputValidator.isValidName(name)) {
                break;
            }

            System.out.println("Invalid name. Please enter letters only.");
        }

        String email;

        while (true) {

            email = readString("Enter email: ");

            if (InputValidator.isValidEmail(email)) {
                break;
            }

            System.out.println("Invalid email format.");
        }

        String phone;

        while (true) {

            phone = readString("Enter phone number: ");

            if (InputValidator.isValidPhone(phone)) {
                break;
            }

            System.out.println("Phone number must contain exactly 10 digits.");
        }

        String course = readString("Enter course: ");

        int semester;

        while (true) {

            semester = readInt("Enter semester (1-8): ");

            if (InputValidator.isValidSemester(semester)) {
                break;
            }

            System.out.println("Semester must be between 1 and 8.");
        }

        Student student = new Student(
                name,
                email,
                phone,
                course,
                semester
        );

        boolean result = studentDAO.addStudent(student);

        if (result) {
            System.out.println("\nStudent added successfully!");
        } else {
            System.out.println("\nFailed to add student.");
        }
    }

    // ================= VIEW ALL STUDENTS =================

    private static void viewAllStudents() {

        System.out.println("\n========== ALL STUDENTS ==========");

        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {

            System.out.println("------------------------------------------");

            System.out.println("ID       : " + student.getId());
            System.out.println("Name     : " + student.getName());
            System.out.println("Email    : " + student.getEmail());
            System.out.println("Phone    : " + student.getPhone());
            System.out.println("Course   : " + student.getCourse());
            System.out.println("Semester : " + student.getSemester());
        }

        System.out.println("------------------------------------------");
    }

    // ================= SEARCH STUDENT =================

    private static void searchStudent() {

        System.out.println("\n========== SEARCH STUDENT ==========");

        int id = readInt("Enter student ID: ");

        Student student = studentDAO.getStudentById(id);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println("\nStudent Details:");

        System.out.println("ID       : " + student.getId());
        System.out.println("Name     : " + student.getName());
        System.out.println("Email    : " + student.getEmail());
        System.out.println("Phone    : " + student.getPhone());
        System.out.println("Course   : " + student.getCourse());
        System.out.println("Semester : " + student.getSemester());
    }

    // ================= UPDATE STUDENT =================

    private static void updateStudent() {

        System.out.println("\n========== UPDATE STUDENT ==========");

        int id = readInt("Enter student ID to update: ");

        Student existingStudent = studentDAO.getStudentById(id);

        if (existingStudent == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println("\nEnter new details:");

        String name = readString("Enter name: ");

        while (!InputValidator.isValidName(name)) {

            System.out.println("Invalid name.");
            name = readString("Enter name again: ");
        }

        String email = readString("Enter email: ");

        while (!InputValidator.isValidEmail(email)) {

            System.out.println("Invalid email.");
            email = readString("Enter email again: ");
        }

        String phone = readString("Enter phone: ");

        while (!InputValidator.isValidPhone(phone)) {

            System.out.println("Phone must contain exactly 10 digits.");
            phone = readString("Enter phone again: ");
        }

        String course = readString("Enter course: ");

        int semester = readInt("Enter semester (1-8): ");

        while (!InputValidator.isValidSemester(semester)) {

            System.out.println("Semester must be between 1 and 8.");
            semester = readInt("Enter semester again: ");
        }

        Student updatedStudent = new Student(
                id,
                name,
                email,
                phone,
                course,
                semester
        );

        boolean result = studentDAO.updateStudent(updatedStudent);

        if (result) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Failed to update student.");
        }
    }

    // ================= DELETE STUDENT =================

    private static void deleteStudent() {

        System.out.println("\n========== DELETE STUDENT ==========");

        int id = readInt("Enter student ID to delete: ");

        Student student = studentDAO.getStudentById(id);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student: " + student.getName());

        String confirmation =
                readString("Are you sure you want to delete? (yes/no): ");

        if (confirmation.equalsIgnoreCase("yes")) {

            boolean result = studentDAO.deleteStudent(id);

            if (result) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Failed to delete student.");
            }

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }

    // ================= ADD GRADE =================

    private static void addGrade() {

        System.out.println("\n========== ADD GRADE ==========");

        int studentId = readInt("Enter student ID: ");

        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student: " + student.getName());

        String subject = readString("Enter subject: ");

        double marks;

        while (true) {

            marks = readDouble("Enter marks (0-100): ");

            if (InputValidator.isValidMarks(marks)) {
                break;
            }

            System.out.println("Marks must be between 0 and 100.");
        }

        Grade grade = new Grade(
                studentId,
                subject,
                marks
        );

        boolean result = gradeService.addGrade(grade);

        if (result) {

            System.out.println("\nGrade added successfully!");
            System.out.println("Grade: " +
                    gradeService.calculateGrade(marks));

        } else {

            System.out.println("Failed to add grade.");
        }
    }

    // ================= VIEW GRADES =================

    private static void viewStudentGrades() {

        System.out.println("\n========== STUDENT GRADES ==========");

        int studentId = readInt("Enter student ID: ");

        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {

            System.out.println("Student not found.");
            return;
        }

        List<Grade> grades =
                gradeDAO.getGradesByStudentId(studentId);

        if (grades.isEmpty()) {

            System.out.println("No grades found.");
            return;
        }

        System.out.println("\nStudent: " + student.getName());

        for (Grade grade : grades) {

            System.out.println("--------------------------------");

            System.out.println("Grade ID : " + grade.getId());
            System.out.println("Subject  : " + grade.getSubject());
            System.out.println("Marks    : " + grade.getMarks());
            System.out.println("Grade    : " +
                    gradeService.calculateGrade(grade.getMarks()));
        }
    }

    // ================= UPDATE GRADE =================

    private static void updateGrade() {

        System.out.println("\n========== UPDATE GRADE ==========");

        int gradeId = readInt("Enter grade ID: ");

        Grade existingGrade = gradeDAO.getGradeById(gradeId);

        if (existingGrade == null) {

            System.out.println("Grade not found.");
            return;
        }

        String subject = readString("Enter new subject: ");

        double marks;

        while (true) {

            marks = readDouble("Enter new marks (0-100): ");

            if (InputValidator.isValidMarks(marks)) {
                break;
            }

            System.out.println("Marks must be between 0 and 100.");
        }

        Grade updatedGrade = new Grade(
                gradeId,
                existingGrade.getStudentId(),
                subject,
                marks
        );

        boolean result = gradeDAO.updateGrade(updatedGrade);

        if (result) {

            System.out.println("Grade updated successfully!");

        } else {

            System.out.println("Failed to update grade.");
        }
    }

    // ================= DELETE GRADE =================

    private static void deleteGrade() {

        System.out.println("\n========== DELETE GRADE ==========");

        int gradeId = readInt("Enter grade ID: ");

        Grade grade = gradeDAO.getGradeById(gradeId);

        if (grade == null) {

            System.out.println("Grade not found.");
            return;
        }

        System.out.println("Subject: " + grade.getSubject());
        System.out.println("Marks: " + grade.getMarks());

        String confirmation =
                readString("Are you sure? (yes/no): ");

        if (confirmation.equalsIgnoreCase("yes")) {

            boolean result = gradeDAO.deleteGrade(gradeId);

            if (result) {

                System.out.println("Grade deleted successfully!");

            } else {

                System.out.println("Failed to delete grade.");
            }

        } else {

            System.out.println("Delete operation cancelled.");
        }
    }

    // ================= STUDENT REPORT =================

    private static void generateStudentReport() {

        System.out.println("\n========== STUDENT REPORT ==========");

        int studentId = readInt("Enter student ID: ");

        gradeService.generateStudentReport(studentId);
    }

    // ================= INPUT METHODS =================

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }
}