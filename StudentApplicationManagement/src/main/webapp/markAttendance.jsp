<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.example.attendance.dao.*" %>
<%@ page import="com.example.controller.*" %>
<%@ page import="com.example.attendance.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Mark Attendance</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

form {
	display: inline;
}

button {
	padding: 5px 10px;
	margin: 5px;
}

.message {
	margin-bottom: 20px;
	padding: 10px;
	border-radius: 4px;
	color: white;
	background-color: green;
}

.error {
	background-color: red;
}
</style>
</head>
<body>
	<h2 style="color: magenta;">Mark Attendance for Students</h2>

	<c:if test="${not empty param.success}">
		<div class="message">${param.success}</div>
	</c:if>
	<c:if test="${not empty param.error}">
		<div class="message error">${param.error}</div>
	</c:if>

	<form action="markAttendance" method="post">
		
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Course</th>
				<th>Date</th>
				<th>Status</th>
				<th>Action</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="student" items="${students}">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.studentName}</td>
					<td>${student.studentEmail}</td>
					<td>${student.courseId}</td>
					<td><input type="date" name="attendanceDate" required /></td>
	
					<td><select name="status" required>
							<option value="Present">Present</option>
							<option value="Absent">Absent</option>


					</select></td>
					<td><input type="hidden" name="studentId"
						value="${student.studentId}" />
						<button>Mark Attendance</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</form>
</body>
</html>
