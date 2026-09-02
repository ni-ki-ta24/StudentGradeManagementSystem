package dao;

import database.DatabaseConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add Student
    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students (name, email, phone, course, semester) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getSemester());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while adding student.");
            e.printStackTrace();
            return false;
        }
    }

    // Get Student by ID
    public Student getStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("course"),
                        resultSet.getInt("semester")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching student.");
            e.printStackTrace();
        }

        return null;
    }

    // Get All Students
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("course"),
                        resultSet.getInt("semester")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching students.");
            e.printStackTrace();
        }

        return students;
    }

    // Update Student
    public boolean updateStudent(Student student) {

        String sql = "UPDATE students SET name = ?, email = ?, phone = ?, " +
                     "course = ?, semester = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());
            statement.setString(4, student.getCourse());
            statement.setInt(5, student.getSemester());
            statement.setInt(6, student.getId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while updating student.");
            e.printStackTrace();
            return false;
        }
    }

    // Delete Student
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while deleting student.");
            e.printStackTrace();
            return false;
        }
    }
}