package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add-student")
public class AddStudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String course = request.getParameter("course");

        // Validate input
        String errorMessage = validateInput(id, name, email, course);

        if (errorMessage != null) {
            // If there's an error, send it back to the JSP page
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("add-student.jsp");
            dispatcher.forward(request, response);
        } else {
            // Add student to the database
            try (Connection connection = getConnection()) {
                String sql = "INSERT INTO students (id, name, email, course) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, id);
                    statement.setString(2, name);
                    statement.setString(3, email);
                    statement.setString(4, course);

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        // Redirect to the student list page if the insertion is successful
                        response.sendRedirect("students");
                    } else {
                        // If insertion failed, return to the form with an error message
                        request.setAttribute("errorMessage", "Error adding student. Please try again.");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("add-student.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Database error: " + e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("add-student.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    private String validateInput(String id, String name, String email, String course) {
        if (id == null || id.isEmpty() || 
            name == null || name.isEmpty() ||
            email == null || email.isEmpty() || 
            course == null || course.isEmpty()) {
            return "All fields are required.";
        } else if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            return "Invalid email format.";
        }
        return null;
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root", "Eswaraiah");
    }
}
