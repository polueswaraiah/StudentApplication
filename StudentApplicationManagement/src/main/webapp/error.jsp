<%@ page isErrorPage="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            color: #721c24;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            background-color: #ffffff;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #721c24;
        }
        p {
            margin: 10px 0;
        }
        .error-message {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Error</h1>
        <p class="error-message">
            <%= request.getParameter("message") != null ? request.getParameter("message") : "unexpected error occurred." %>
        </p>

        <p><a href="index.jsp">Return to Home</a></p>
    </div>
</body>
</html>
