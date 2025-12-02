<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Management System Login</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="header">
        <h1 class="chart-link">Chart</h1>
    </div>
    <div class="login-container container">
        <h2>Library Management System</h2>
        <% 
            String error = (String) request.getAttribute("errorMessage");
            if (error != null) {
        %>
            <p class="error-message"><%= error %></p>
        <%
            }
        %>
        
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="userId">User ID:</label>
                <input type="text" id="userId" name="userId" value="user" required> 
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="user" required> 
            </div>
            <button type="submit" class="btn primary-btn">Login</button>
        </form>
    </div>
</body>
</html>