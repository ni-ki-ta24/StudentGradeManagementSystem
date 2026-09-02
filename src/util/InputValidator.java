package util;

public class InputValidator {

    // Validate Student Name
    public static boolean isValidName(String name) {
        return name != null &&
               !name.trim().isEmpty() &&
               name.matches("[a-zA-Z ]+");
    }

    // Validate Email
    public static boolean isValidEmail(String email) {
        return email != null &&
               email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Validate Phone
    public static boolean isValidPhone(String phone) {
        return phone != null &&
               phone.matches("\\d{10}");
    }

    // Validate Semester
    public static boolean isValidSemester(int semester) {
        return semester >= 1 && semester <= 8;
    }

    // Validate Marks
    public static boolean isValidMarks(double marks) {
        return marks >= 0 && marks <= 100;
    }
}