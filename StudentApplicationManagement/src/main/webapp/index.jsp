<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Attendance Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 100px;
        }
        h1 {
            font-size: 36px;
            color: #333;
        }
        .container {
            margin: auto;
            width: 50%;
        }
        .button {
            background-color: #4CAF50;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
            border: none;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Welcome to the Attendance Management System</h1>
    <div class="container">
        <a href="students.jsp" class="button">Manage Students</a>
        <a href="add-student.jsp" class="button">Push More Details</a>
        <a href="markAttendance" class="button">Mark Attendance</a>
        <a href="viewAttendance" class="button">view Attendance</a>
        
    </div>
</body>
</html>
