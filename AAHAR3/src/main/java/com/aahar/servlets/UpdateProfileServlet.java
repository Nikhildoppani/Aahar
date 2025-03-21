package com.aahar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aahar.utility.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// Database connection details
    private static final String SQL_QUERY = "UPDATE `user` SET `name`=?,`address`=? WHERE `user_id`=?";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String address=req.getParameter("address");
		HttpSession session = req.getSession();
    	Integer id=(Integer)session.getAttribute("userId");
    	// Check if user is logged in
    			if (id == null) {
    				resp.sendRedirect("login.jsp");
    				return;
    			}

    			// Check if name or address is empty
    			if (name == null || name.trim().isEmpty() || address == null || address.trim().isEmpty()) {
    				session.setAttribute("error", "Name and Address cannot be empty!");
    				String referer = req.getHeader("Referer");
    				resp.sendRedirect(referer);
    				return;
    			}
		System.out.println(name+" "+address);
		try(Connection connection=DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(SQL_QUERY);) {
			prepareStatement.setString(1, name);
			prepareStatement.setString(2, address);
			prepareStatement.setInt(3, id);
			prepareStatement.executeUpdate();	
			
			session.setAttribute("success", "Profile updated successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String referer = req.getHeader("Referer");
	    if (referer != null) {
	        resp.sendRedirect(referer);
	    } else {
	        resp.sendRedirect("profile.jsp"); // Fallback in case there's no referer
	        session.setAttribute("error", "Database error. Please try again.");
	    }
	}
}
