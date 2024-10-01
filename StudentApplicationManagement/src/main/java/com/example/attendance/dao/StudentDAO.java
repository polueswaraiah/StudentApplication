package com.example.attendance.dao;

import com.example.attendance.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getConnection();
            String query = "SELECT * FROM students";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String studentName = resultSet.getString("student_name");
                String studentEmail = resultSet.getString("student_email");
                String courseId = resultSet.getString("course_id");

                Student student = new Student(studentId, studentName, studentEmail, courseId);
                students.add(student);
            }
        } finally {
           
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return students;
    }

    public void addStudent(Student student) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            String query = "INSERT INTO students (student_name, student_email, course_id) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, student.getStudentName());
            statement.setString(2, student.getStudentEmail());
            statement.setString(3, student.getCourseId());

            statement.executeUpdate();
        } finally {
            // Ensure resources are closed
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}
