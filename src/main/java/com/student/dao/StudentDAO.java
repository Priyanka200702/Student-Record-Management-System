
package com.student.dao;

import com.student.database.DBConnection;
import com.student.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Add a student
    public boolean addStudent(Student student) throws SQLException {

        String sql = "INSERT INTO students "
                + "(roll_no, name, course, student_year, email, phone, gender) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getRollNo());
            statement.setString(2, student.getName());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getYear());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getGender());

            return statement.executeUpdate() > 0;
        }
    }

    // Retrieve all students
    public List<Student> getAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students ORDER BY roll_no";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Student student = new Student(
                        result.getString("roll_no"),
                        result.getString("name"),
                        result.getString("course"),
                        result.getInt("student_year"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("gender")
                );

                students.add(student);
            }
        }

        return students;
    }

    // Update an existing student
    public boolean updateStudent(Student student) throws SQLException {

        String sql = "UPDATE students SET "
                + "name = ?, course = ?, student_year = ?, "
                + "email = ?, phone = ?, gender = ? "
                + "WHERE roll_no = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getCourse());
            statement.setInt(3, student.getYear());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.setString(6, student.getGender());
            statement.setString(7, student.getRollNo());

            return statement.executeUpdate() > 0;
        }
    }

    // Delete a student using roll number
    public boolean deleteStudent(String rollNo) throws SQLException {

        String sql = "DELETE FROM students WHERE roll_no = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, rollNo);

            return statement.executeUpdate() > 0;
        }
    }

    // Search students by roll number
    public List<Student> searchByRollNo(String rollNo) throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students WHERE roll_no LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + rollNo + "%");

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    students.add(new Student(
                            result.getString("roll_no"),
                            result.getString("name"),
                            result.getString("course"),
                            result.getInt("student_year"),
                            result.getString("email"),
                            result.getString("phone"),
                            result.getString("gender")
                    ));
                }
            }
        }

        return students;
    }

    // Search students by name
    public List<Student> searchByName(String name) throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    students.add(new Student(
                            result.getString("roll_no"),
                            result.getString("name"),
                            result.getString("course"),
                            result.getInt("student_year"),
                            result.getString("email"),
                            result.getString("phone"),
                            result.getString("gender")
                    ));
                }
            }
        }

        return students;
    }

    // Search students by course
    public List<Student> searchByCourse(String course) throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students WHERE course LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + course + "%");

            try (ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    students.add(new Student(
                            result.getString("roll_no"),
                            result.getString("name"),
                            result.getString("course"),
                            result.getInt("student_year"),
                            result.getString("email"),
                            result.getString("phone"),
                            result.getString("gender")
                    ));
                }
            }
        }

        return students;
    }


}
