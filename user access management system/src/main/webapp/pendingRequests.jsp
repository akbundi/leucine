<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Requests</title>
</head>
<body>
    <h1>Pending Requests</h1>
    <table border="2">
        <tr>
            <th>Employee ID</th>
            <th>Software Name</th>
            <th>Access Type</th>
            <th>Reason for Request</th>
            <th>Action</th>
        </tr>
        <%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.Statement" %>
        <%
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT r.id, u.id, s.name, r.access_type, r.reason FROM requests r INNER JOIN users u ON r.user_id = u.id INNER JOIN software s ON r.software_id = s.id WHERE r.status = 'Pending'");
            while (rs.next()) {
        %>
                <tr>
                    <td><%= rs.getInt("u.id") %></td>
                    <td><%= rs.getString("s.name") %></td>
                    <td><%= rs.getString("r.access_type") %></td>
                    <td><%= rs.getString("r.reason") %></td>
                    <td>
                        <form method="post" action="approval">
                            <input type="hidden" name="requestId" value="<%= rs.getInt("r.id") %>">
                            <input type="submit" value="Approve">
                        </form>
                        <form method="post" action="approval">
                            <input type="hidden" name="requestId" value="<%= rs.getInt("r.id") %>">
                            <input type="submit" value="Reject">
                        </form>
                    </td>
                </tr>
        <%}%>
    </table>
</body>
</html>
		