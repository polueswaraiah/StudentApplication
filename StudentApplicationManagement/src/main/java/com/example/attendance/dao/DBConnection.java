package com.example.attendance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static synchronized Connection getConnection() {
        if (connection == null || isConnectionClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root", "Eswaraiah");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static boolean isConnectionClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    // Optional: Method to close the connection explicitly (if needed)
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
