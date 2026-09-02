package dao;

import database.DatabaseConnection;
import model.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {

    // Add Grade
    public boolean addGrade(Grade grade) {

        String sql = "INSERT INTO grades (student_id, subject, marks) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, grade.getStudentId());
            statement.setString(2, grade.getSubject());
            statement.setDouble(3, grade.getMarks());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while adding grade.");
            e.printStackTrace();
            return false;
        }
    }

    // Get Grade by ID
    public Grade getGradeById(int id) {

        String sql = "SELECT * FROM grades WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Grade(
                        resultSet.getInt("id"),
                        resultSet.getInt("student_id"),
                        resultSet.getString("subject"),
                        resultSet.getDouble("marks")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching grade.");
            e.printStackTrace();
        }

        return null;
    }

    // Get All Grades
    public List<Grade> getAllGrades() {

        List<Grade> grades = new ArrayList<>();

        String sql = "SELECT * FROM grades";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Grade grade = new Grade(
                        resultSet.getInt("id"),
                        resultSet.getInt("student_id"),
                        resultSet.getString("subject"),
                        resultSet.getDouble("marks")
                );

                grades.add(grade);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching grades.");
            e.printStackTrace();
        }

        return grades;
    }

    // Update Grade
    public boolean updateGrade(Grade grade) {

        String sql = "UPDATE grades SET student_id = ?, subject = ?, marks = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, grade.getStudentId());
            statement.setString(2, grade.getSubject());
            statement.setDouble(3, grade.getMarks());
            statement.setInt(4, grade.getId());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while updating grade.");
            e.printStackTrace();
            return false;
        }
    }

    // Delete Grade
    public boolean deleteGrade(int id) {

        String sql = "DELETE FROM grades WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error while deleting grade.");
            e.printStackTrace();
            return false;
        }
    }

    // Get Grades of a Specific Student
    public List<Grade> getGradesByStudentId(int studentId) {

        List<Grade> grades = new ArrayList<>();

        String sql = "SELECT * FROM grades WHERE student_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Grade grade = new Grade(
                        resultSet.getInt("id"),
                        resultSet.getInt("student_id"),
                        resultSet.getString("subject"),
                        resultSet.getDouble("marks")
                );

                grades.add(grade);
            }

        } catch (SQLException e) {
            System.out.println("Error while fetching student grades.");
            e.printStackTrace();
        }

        return grades;
    }
}