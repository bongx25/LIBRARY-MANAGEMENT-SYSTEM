<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<% 
    String serialNo = request.getParameter("serialNo");
    if (serialNo == null || serialNo.isEmpty()) { 
        response.sendRedirect("bookAvailable.jsp"); 
        return; 
    }
    String today = LocalDate.now().toString();
    
    String error = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Confirm Book Issue</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="header">
        <h1 class="chart-link">Chart</h1>
        <a href="adminHome.jsp" class="home-link">Home</a>
        <a href="LogoutServlet" class="logout-link">Log Out</a>
    </div>

    <div class="container">
        <h2>Book Issue Confirmation (Book Issue.csv)</h2>
        
        <% if (error != null) { %>
            <p class="error-message"><%= error %></p>
        <% } %>
        
        <form action="ConfirmIssueServlet" method="post" class="form-grid">
            
            <p>You are issuing: **<%= serialNo %>**</p>
            <input type="hidden" name="serialNo" value="<%= serialNo %>">
            
            <div class="form-group">
                <label for="memberId">Enter Membership ID (e.g., 101):</label>
                <input type="text" id="memberId" name="memberId" required>
            </div>
            
            <div class="form-group">
                <label for="issueDate">Issue Date:</label>
                <input type="date" id="issueDate" name="issueDate" value="<%= today %>" required>
            </div>
            
            <div class="form-group">
                <label for="remarks">Remarks (Non Mandatory):</label>
                <textarea id="remarks" name="remarks"></textarea>
            </div>
            
            <button type="submit" class="btn primary-btn full-width">Confirm Issue</button>
        </form>
    </div>
</body>
</html>