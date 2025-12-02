<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="User" %>
<% 
    User user = (User) session.getAttribute("currentUser");
    if (user == null) { response.sendRedirect("login.jsp"); return; }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Availability Search</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="header">
        <h1 class="chart-link">Chart</h1>
        <a href="<%= user.isAdmin() ? "adminHome.jsp" : "userHome.jsp" %>" class="home-link">Home</a>
        <a href="LogoutServlet" class="logout-link">Log Out</a>
    </div>

    <div class="container">
        <h2>Book Availability Search (Book Available.csv)</h2>
        
        <% 
            String error = (String) request.getAttribute("errorMessage");
            if (error != null) {
        %>
            <p class="error-message"><%= error %></p>
        <%
            }
        %>
        
        <form action="ItemSearchServlet" method="post" class="form-grid">
            <div class="form-group">
                <label for="bookName">Enter Book/Movie Name:</label>
                <input type="text" id="bookName" name="bookName" placeholder="Search by name">
            </div>
            
            <div class="form-group">
                <label for="author">Enter Author/Director:</label>
                <input type="text" id="author" name="author" placeholder="Search by author">
            </div>
            
            <button type="submit" class="btn primary-btn full-width">Search Books/Movies</button>
        </form>
    </div>
</body>
</html>