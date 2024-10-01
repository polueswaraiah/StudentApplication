package com.example.controller;

import com.example.attendance.dao.AttendanceDAO;
import com.example.attendance.model.Attendance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewAttendance")
public class ViewAttendanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        AttendanceDAO attendanceDAO = new AttendanceDAO();
	        List<Attendance> attendanceList = attendanceDAO.getAllAttendance(); // Fetching all records
	        request.setAttribute("attendanceList", attendanceList); // Setting attribute name to be consistent
	        RequestDispatcher dispatcher = request.getRequestDispatcher("viewAttendance.jsp");
	        dispatcher.forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.jsp");
	    }
	}
}
