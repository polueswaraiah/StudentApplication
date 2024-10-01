package com.example.attendance.dao;

import com.example.attendance.model.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Use java.sql.Date for SQL operations
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AttendanceDAO {
    private static final Logger logger = Logger.getLogger(AttendanceDAO.class.getName());

    // Method to mark attendance
    public void markAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendance (student_id, attendance_date, status) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, attendance.getStudentId());
            ps.setDate(2, new Date(attendance.getAttendanceDate().getTime())); // Ensure date is correctly formatted for SQL
            ps.setString(3, attendance.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.severe("Error marking attendance: " + e.getMessage());
            throw new RuntimeException("Failed to mark attendance", e);
        }
    }

    // Method to retrieve all attendance records
    public List<Attendance> getAllAttendance() {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT * FROM attendance";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int attendanceId = rs.getInt("attendance_id");
                int studentId = rs.getInt("student_id");
                Date attendanceDate = rs.getDate("attendance_date");
                String status = rs.getString("status");

                Attendance attendance = new Attendance(attendanceId, studentId, attendanceDate, status);
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving attendance: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve attendance", e);
        }
        return attendanceList;
    }

    // Method to retrieve attendance records by student ID
    public List<Attendance> getAttendanceByStudent(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int attendanceId = rs.getInt("attendance_id");
                    Date attendanceDate = rs.getDate("attendance_date");
                    String status = rs.getString("status");

                    Attendance attendance = new Attendance(attendanceId, studentId, attendanceDate, status);
                    attendanceList.add(attendance);
                }
            }
        } catch (SQLException e) {
            logger.severe("Error retrieving attendance for student ID " + studentId + ": " + e.getMessage());
            throw new RuntimeException("Failed to retrieve attendance for student", e);
        }
        return attendanceList;
    }
}
