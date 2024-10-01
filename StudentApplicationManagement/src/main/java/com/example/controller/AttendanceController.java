package com.example.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.attendance.dao.AttendanceDAO;
import com.example.attendance.dao.StudentDAO;
import com.example.attendance.model.Attendance;
import com.example.attendance.model.Student;

@WebServlet("/markAttendance") // Updated to match JSP form action
public class AttendanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			StudentDAO studentDAO = new StudentDAO();
			List<Student> students = studentDAO.getAllStudents();

			request.setAttribute("students", students);

			request.getRequestDispatcher("/markAttendance.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			response.sendRedirect("students?error=An unexpected error occurred");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int studentId = Integer.parseInt(request.getParameter("studentId"));

			AttendanceDAO attendanceDAO = new AttendanceDAO();

			for (String paramName : request.getParameterMap().keySet()) {
				if (paramName.startsWith("date_")) {

					String[] parts = paramName.split("_");
					int id = Integer.parseInt(parts[1]);

					if (id == studentId) {

						String dateStr = request.getParameter(paramName);
						String status = request.getParameter("status_" + id);

						if (dateStr == null || dateStr.trim().isEmpty()) {
							throw new IllegalArgumentException("Date is required for student ID: " + id);
						}

						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

						Attendance attendance = new Attendance(0, id, date, status);

						attendanceDAO.markAttendance(attendance);
					}
				}
			}

			response.sendRedirect("students?success=Attendance marked successfully");
		} catch (NumberFormatException e) {
			e.printStackTrace();

			response.sendRedirect("students?error=Invalid student ID format");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

			response.sendRedirect("students?error=" + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();

			response.sendRedirect("students?error=Invalid date format");
		} catch (Exception e) {
			e.printStackTrace();

			response.sendRedirect("students?error=An unexpected error occurred");
		}
	}
}