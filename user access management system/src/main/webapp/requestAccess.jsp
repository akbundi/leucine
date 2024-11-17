<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.Statement" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Access</title>
</head>
<body>
    <h1>Request Access</h1>
    <form method="post" action="request">
        <label for="softwareName">Software Name:</label><br>


        <select id="softwareName" name="softwareName"><br>

            <%
                // Connect to the database and retrieve the software names
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name FROM software");
                while (rs.next()) {
            %>
                    <option value="<%= rs.getString("name") %>"><%= rs.getString("name") %></option>
            <%}%>
        </select>

        <label for="accessType">Access Type:</label><br>


        <select id="accessType" name="accessType"><br>

            <option value="Read">Read</option>
            <option value="Write">Write</option>
            <option value="Admin">Admin</option>

        </select>

        <label for="reason">Reason for Request:</label><br>


        <textarea id="reason" name="reason"></textarea><br>


        <input type="submit" value="Request Access">
    </form>
</body>
</html>