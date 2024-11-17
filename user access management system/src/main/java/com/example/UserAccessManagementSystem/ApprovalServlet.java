package com.example.UserAccessManagementSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ApprovalServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int requestId = Integer.parseInt(request.getParameter("requestId"));
    String status = request.getParameter("status");

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin")) {
      PreparedStatement stmt = conn.prepareStatement("UPDATE requests SET status =? WHERE id =?");
      stmt.setString(1, status);
      stmt.setInt(2, requestId);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("pendingRequests.jsp");
  }
}