<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.attendance.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Attendance</title>
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
    </style>
</head>
<body>
    <h2>Attendance Records</h2>

    <c:if test="${not empty attendanceList}">
        <table>
            <tr>
                <th>Student ID</th>
                <th>Date</th>
                <th>Status</th>
            </tr>
            <c:forEach var="attendance" items="${attendanceList}">
                <tr>
                    <td>${attendance.studentId}</td>
                    <td>${attendance.attendanceDate}</td>
                    <td>${attendance.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty attendanceList}">
        <p>No attendance records found.</p>
    </c:if>

    <br>
    <a href="students">Back to Students</a>
</body>
</html>
