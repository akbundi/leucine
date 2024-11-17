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
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin")) {
      PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username =? AND password =?");
      stmt.setString(1, username);
      stmt.setString(2, password);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("role", rs.getString("role"));

        if (rs.getString("role").equals("Employee")) {
          response.sendRedirect("requestAccess.jsp");
        } else if (rs.getString("role").equals("Manager")) {
          response.sendRedirect("pendingRequests.jsp");
        } else if (rs.getString("role").equals("Admin")) {
          response.sendRedirect("createSoftware.jsp");
        }
      } else {
        response.sendRedirect("login.jsp");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}