<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    String message = (String) request.getAttribute("message");
    if (message == null) message = "Transaction completeld successfully.";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="header">
        <h1 class="chart-link">Chart</h1>
        <a href="adminHome.jsp" class="home-link">Home</a>
        <a href="LogoutServlet" class="logout-link">Log Out</a>
    </div>

    <div class="container">
        <h2>Confirmation (Confirmation.csv)</h2>
        <p class="success-message" style="color: green; font-size: 1.2em; text-align: center;">
            **<%= message %>**
        </p>
    </div>
</body>
</html>