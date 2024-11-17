package com.example.UserAccessManagementSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class RequestServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("username");
    String softwareName = request.getParameter("softwareName");
    String accessType = request.getParameter("accessType");
    String reason = request.getParameter("reason");

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin")) {
      PreparedStatement stmt = conn.prepareStatement("SELECT id FROM users WHERE username =?");
      stmt.setString(1, username);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        int userId = rs.getInt("id");

        PreparedStatement stmt2 = conn.prepareStatement("SELECT id FROM software WHERE name =?");
        stmt2.setString(1, softwareName);
        ResultSet rs2 = stmt2.executeQuery();

        if (rs2.next()) {
          int softwareId = rs2.getInt("id");

          PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?,?,?,?, 'Pending')");
          stmt3.setInt(1, userId);
          stmt3.setInt(2, softwareId);
          stmt3.setString(3, accessType);
          stmt3.setString(4, reason);
          stmt3.executeUpdate();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("requestAccess.jsp");
  }
}