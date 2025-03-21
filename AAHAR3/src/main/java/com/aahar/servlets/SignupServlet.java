package com.aahar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet  extends HttpServlet{
	// Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aahar";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "doppani";
    private static final String SQL_QUERY = "SELECT email, phone FROM `user` WHERE `email` = ? OR `phone`=?";
    private static final String USER_INSERT_QUERY="INSERT INTO `user` (name, password, email, phone) VALUES (?,?,?,?)";
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 	String name = req.getParameter("fullname");
	        String email = req.getParameter("email");
	        String phone = req.getParameter("phone");
	        String password = req.getParameter("password");
	        
	        // Regular expressions for validation
	        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$"; // Email must end with @gmail.com
	        String phoneRegex = "^[6789]\\d{9}$"; // Phone must start with 6, 7, 8, or 9 and be 10 digits long

	        if (!Pattern.matches(emailRegex, email)) {
	        	resp.sendRedirect("signup.jsp?error=Invalid email. Must end with @gmail.com");
	            return;
	        }

	        if (!Pattern.matches(phoneRegex, phone)) {
	        	resp.sendRedirect("signup.jsp?error=Invalid phone number. Must be 10 digits and start with 6, 7, 8, or 9");
	            return;
	        }
	        try {
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        	try(Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
	                    PreparedStatement stmt = conn.prepareStatement(SQL_QUERY);
	        			PreparedStatement stmt2 = conn.prepareStatement(USER_INSERT_QUERY);) {
	        		stmt.setString(1, email);
	        		stmt.setString(2, phone);
					ResultSet rs=stmt.executeQuery();
					
					if (rs.next()) { 
						String existingEmail = rs.getString("email");
		                String existingPhone = rs.getString("phone");
		                
		                String errorMessage = "";
		                if (existingEmail.equals(email)) {
		                    errorMessage += "Email already exists. ";
		                }
		                if (existingPhone.equals(phone)) {
		                    errorMessage += "Phone already exists.";
		                }
		                req.setAttribute("errorMessage", errorMessage);
	                    RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
	                    dispatcher.forward(req, resp);
	                } else { // If user does not exist, insert into database
	                    stmt2.setString(1, name);
	                    stmt2.setString(2, password);
	                    stmt2.setString(3, email);
	                    stmt2.setString(4, phone);
	                    stmt2.executeUpdate();

	                    resp.sendRedirect("login.html");
	                }
				} 
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("errorMessage", "An unexpected error occurred. Please try again.");
	            RequestDispatcher dispatcher = req.getRequestDispatcher("signup.jsp");
	            dispatcher.forward(req, resp);
			}
	        
	        
	}
}
