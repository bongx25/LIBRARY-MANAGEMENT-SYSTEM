<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="User" %>
<% 
    User user = (User) session.getAttribute("currentUser");
    if (user == null || !user.isAdmin()) { response.sendRedirect("login.jsp"); return; }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Home Page</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="header">
        <h1 class="chart-link">Chart</h1>
        <h1 class="chart-link">Welcome, Admin <%= user.getName() %>!</h1>
        <a href="LogoutServlet" class="logout-link">Log Out</a>
    </div>

    <div class="container module-links">
        <h2>Admin Dashboard (Admin Home Page.csv)</h2>
        <div class="modules">
            <a href="maintenance.jsp" class="btn primary-btn">MAINTENANCE</a> 
            <a href="reports.jsp" class="btn primary-btn">REPORTS</a> 
            <a href="transactions.jsp" class="btn primary-btn">TRANSACTIONS</a>
        </div>
        <p style="margin-top: 30px;">(Access to all modules: Maintenance, Reports, Transactions)</p>
    </div>
</body>
</html>