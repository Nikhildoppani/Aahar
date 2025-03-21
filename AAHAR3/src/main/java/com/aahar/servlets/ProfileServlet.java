package com.aahar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

import com.aahar.daoimplementation.MenuDAOImplementation;
import com.aahar.daoimplementation.UserDAOImplementation;
import com.aahar.model.Menu;
import com.aahar.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.aahar.daoimplementation.UserDAOImplementation;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	int id=(Integer)session.getAttribute("userId");
    	System.out.println("start");
    	UserDAOImplementation userDAOImp=new UserDAOImplementation();
    	List<User> userInfo=userDAOImp.getUserInfo(id);
    	req.setAttribute("user", userInfo);
		RequestDispatcher rd=req.getRequestDispatcher("profile.jsp");
		rd.forward(req, resp);
    }
    
    
}
