<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add New Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        h2 {
            color: #333;
        }
        form {
            margin: auto;
            width: 300px;
            text-align: left;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            background-color: #f9f9f9;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        .back-link {
            margin-top: 20px;
            display: block;
            text-decoration: none;
            color: #007BFF;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .error {
            color: red;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <h2>Add New Student</h2>

    <% 
        // Retrieve the error message from the request (if any)
        String errorMessage = (String) request.getAttribute("errorMessage"); 
    %>
    <% if (errorMessage != null) { %>
        <div class="error"><%= errorMessage %></div>
    <% } %>

    <form action="students" method="post">
        <label for="student_id">ID:</label>
        <input type="text" name="id" id="student_id" required /><br><br>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="course">Course:</label>
        <input type="text" id="course" name="course" required>

        <button type="submit">Add Student</button>
    </form>

    <a href="students" class="back-link">Back to Student List</a>
</body>
</html>
