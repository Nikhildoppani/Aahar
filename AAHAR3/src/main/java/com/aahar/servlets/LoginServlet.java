package com.aahar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aahar";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "doppani";
    private static final String SQL_QUERY = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String SQL_UPDATE_LOGIN_TIME_QUERY = "update user set last_login_date=now() where user_id=?";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
                PreparedStatement stmt2 = conn.prepareStatement(SQL_UPDATE_LOGIN_TIME_QUERY)) {
                
                stmt.setString(1, email);
                stmt.setString(2, password);
                HttpSession session = request.getSession();
                
                try (ResultSet rs = stmt.executeQuery()) {
                
                    if (rs.next()) {
                        // Login successful, redirect to index.html
//                    	HttpSession session = request.getSession();
                    	String dbMail=rs.getString("email");
                    	String dbPassword=rs.getString("password");
                    	System.out.println(dbMail+" "+dbPassword);
                    	if(dbMail.equals(email) && dbPassword.equals(password)) {
                    		int id=rs.getInt("user_id");
                        	stmt2.setInt(1, id);
                        	session.setAttribute("userId", id);
                        	stmt2.executeUpdate();
                        	response.sendRedirect("home");
                    	}
                    	else {
                            // Login failed, redirect back to login with an error message
                        	 session.setAttribute("emailerror", "Invalid email or password!");
                            response.sendRedirect("login.jsp?error=1");
                        }
                        //response.sendRedirect("home");
                    } 
                    else {
                        // Login failed, redirect back to login with an error message
                    	 session.setAttribute("emailerror", "Invalid email or password!");
                        response.sendRedirect("login.jsp?error=1");
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
