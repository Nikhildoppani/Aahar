package com.aahar.servlets;

import java.io.IOException;
import com.aahar.daoimplementation.RestaurantDAOImplementation;

import java.util.List;


import com.aahar.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("servelet called");
		RestaurantDAOImplementation	restaurantDAO=new RestaurantDAOImplementation();
	
	List<Restaurant> allrestaurants =restaurantDAO.getAllRestaurants();
	System.out.println("Fetched Restaurants: " + (allrestaurants != null ? allrestaurants.size() : "null"));
		req.setAttribute("restaurants", allrestaurants);
		RequestDispatcher rd=req.getRequestDispatcher("home.jsp");
		rd.forward(req, resp);
	}
}
