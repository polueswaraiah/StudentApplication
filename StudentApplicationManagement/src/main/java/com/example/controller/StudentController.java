package com.example.controller;

import com.example.attendance.dao.StudentDAO;
import com.example.attendance.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           
            StudentDAO studentDAO = new StudentDAO();
            List<Student> students = studentDAO.getAllStudents();

            request.setAttribute("students", students);

            request.getRequestDispatcher("/students.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
           
            response.sendRedirect("error.jsp?message=Unable to retrieve students");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve form data
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String course = request.getParameter("course");

            
            Student student = new Student(name, email, course);

            
            StudentDAO studentDAO = new StudentDAO();
            studentDAO.addStudent(student);

            
            response.sendRedirect("students?success=Student added successfully");
        } catch (NumberFormatException e) {
            e.printStackTrace();
           
            response.sendRedirect("students?error=Invalid ID format");
        } catch (Exception e) {
            e.printStackTrace();
           
            response.sendRedirect("students?error=An error occurred while adding the student");
        }
    }
}
