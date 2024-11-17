package com.example.UserAccessManagementSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;


public class SoftwareServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String softwareName = request.getParameter("softwareName");
    String description = request.getParameter("description");
    String accessLevels = request.getParameter("accessLevels");

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/useraccessmanagement", "root", "moin")) {
      PreparedStatement stmt = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?,?,?)");
      stmt.setString(1, softwareName);
      stmt.setString(2, description);
      stmt.setString(3, accessLevels);
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("createSoftware.jsp");
  }
}