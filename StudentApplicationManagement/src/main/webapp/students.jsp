<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.attendance.dao.*" %>
<%@ page import="com.example.controller.*" %>
<%@ page import="com.example.attendance.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student List</title>
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
        button {
            padding: 5px 10px;
            margin: 5px;
        }
        form {
            display: inline;
        }
        fieldset {
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 15px;
            padding: 10px;
            background-color: #f9f9f9;
        }
        legend {
            font-weight: bold;
            color: #333;
        }
        .message {
            margin-bottom: 20px;
            padding: 10px;
            border-radius: 4px;
            color: white;
        }
        .success {
            background-color: green;
        }
        .error {
            background-color: red;
        }
    </style>
</head>
<body>
    <h2 style="color: green;">Student List</h2>

    <c:if test="${not empty param.success}">
        <div class="message success">${param.success}</div>
    </c:if>
    <c:if test="${not empty param.error}">
        <div class="message error">${param.error}</div>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Course</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.studentName}</td>
                    <td>${student.studentEmail}</td>
                    <td>${student.courseId}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h3 style="color: orange;">Add New Student</h3>
    <form action="students" method="post">
        <fieldset>
            <legend>Student Details</legend>

            <label for="name">Name:</label>
            <input type="text" name="name" required /><br><br>

            <label for="email">Email:</label>
            <input type="email" name="email" required /><br><br>

            <label for="course">Course:</label>
            <input type="text" name="course" required /><br><br>

            <button type="submit" style="color: blue;">Add Student</button>
            
        </fieldset>
    </form>
</body>
</html>
