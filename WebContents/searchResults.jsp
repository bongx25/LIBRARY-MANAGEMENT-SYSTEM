<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Item, User, java.util.List" %>
<% 
    User user = (User) session.getAttribute("currentUser");
    if (user == null) { response.sendRedirect("login.jsp"); return; }

    List<Item> results = (List<Item>) request.getAttribute("searchResults");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="header">
        <h1 class="chart-link">Chart</h1>
        <a href="<%= user.isAdmin() ? "adminHome.jsp" : "userHome.jsp" %>" class="home-link">Home</a>
        <a href="LogoutServlet" class="logout-link">Log Out</a>
    </div>

    <div class="container">
        <h2>Search Results (Book Availability)</h2>
        
        <% if (results == null || results.isEmpty()) { %>
            <p class="no-results">No books or movies matched your search criteria.</p>
        <% } else { %>
            <form action="bookIssueForm.jsp" method="get">
            <table class="data-table">
                <thead>
                    <tr>
                        <th>Book/Movie Name</th>
                        <th>Author/Director</th>
                        <th>Serial Number</th>
                        <th>Available</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Loop through the dynamic list using Java (JSP scriptlet)
                        for (Item item : results) { 
                    %>
                    <tr>
                        <td><%= item.getName() %></td>
                        <td><%= item.getAuthorName() %></td>
                        <td><%= item.getSerialNo() %></td>
                        <td><%= item.isAvailable() ? "Y" : "N" %></td>
                        <td>
                            <% if (item.isAvailable()) { %>
                                <input type="radio" name="serialNo" value="<%= item.getSerialNo() %>" required>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            
            <div class="form-group" style="margin-top: 20px;">
                <button type="submit" class="btn primary-btn" style="width: 250px;">Issue Selected Item</button>
                <p class="note">Select one radio button for the item you wish to issue.</p>
            </div>
            </form>
        <% } %>
    </div>
</body>
</html>