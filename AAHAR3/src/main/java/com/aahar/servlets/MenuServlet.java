package com.aahar.servlets;

import java.io.IOException;
import java.util.List;

import com.aahar.daoimplementation.MenuDAOImplementation;
import com.aahar.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("menu called");
		int rid=Integer.parseInt(req.getParameter("restaurantId"));

		MenuDAOImplementation menuDAOImp=new MenuDAOImplementation();
		List<Menu> menuList=menuDAOImp.getAllMenus(rid);
		System.out.println("Fetched menuList: " + (menuList != null ? menuList.size() : "null"));
		System.out.println(menuList);
		req.setAttribute("menus", menuList);
		RequestDispatcher rd=req.getRequestDispatcher("menu.jsp");
		rd.forward(req, resp);
	}
}
