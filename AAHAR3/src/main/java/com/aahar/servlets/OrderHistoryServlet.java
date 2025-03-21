package com.aahar.servlets;

import java.io.IOException;
import java.util.List;

import com.aahar.daoimplementation.OrdersDAOImplementation;
import com.aahar.model.OrderHistory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/history")
public class OrderHistoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int id=(Integer)session.getAttribute("userId");
		
		OrdersDAOImplementation orderDAOImp=new OrdersDAOImplementation();
		List<OrderHistory> getAllOrders=orderDAOImp.getAllOrders(id);
		req.setAttribute("orders", getAllOrders);
		RequestDispatcher rd=req.getRequestDispatcher("history.jsp");
		rd.forward(req, resp);
	}
}
