package com.example.UserAccessManagementSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin")) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password, role) VALUES (?,?, 'Employee')");
      stmt.setString(1, username);
      stmt.setString(2, password);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("login.jsp");
  }
}