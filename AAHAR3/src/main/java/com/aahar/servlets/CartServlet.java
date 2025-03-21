package com.aahar.servlets;
import java.io.IOException;

import com.aahar.daoimplementation.MenuDAOImplementation;
import com.aahar.model.*;
import com.aahar.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		
		String action=req.getParameter("action");
		System.out.println(req.getParameter("restaurantId"));
		int newRestaurantId=Integer.parseInt(req.getParameter("restaurantId").trim());
		Integer currentRestaurantId=(Integer) session.getAttribute("restaurantId");
		
		if(cart==null || newRestaurantId !=currentRestaurantId) {
			cart=new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		
		
		System.out.println(action);
		if(action.equals("add")) {
			addItemToCart(req, cart);
		}
		else if(action.equals("update")){
			updateCartItem(req, cart);
		}
		else if(action.equals("remove")) {
			removeItemFromCart(req, cart);
		}
		resp.sendRedirect(req.getHeader("Referer"));
		//resp.sendRedirect("cart.jsp");
	}
	private void addItemToCart(HttpServletRequest req,Cart cart){
		int itemId=Integer.parseInt(req.getParameter("menuId"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		int restaurantId=Integer.parseInt(req.getParameter("restaurantId").trim());
		MenuDAO menuDAO=new MenuDAOImplementation();
		Menu menuItem=menuDAO.getMenu(itemId);
		System.out.println("The menu cart servlet: "+ menuItem);
		
		if(menuItem!=null) {
			CartItem item=new CartItem(menuItem.getMenuId(),menuItem.getItemname(),menuItem.getDiscription(),menuItem.getPrice(),quantity,restaurantId,menuItem.getImgPath());
			cart.addItem(item);
		}
	}
	
	private void updateCartItem(HttpServletRequest req,Cart cart) {
		int itemId=Integer.parseInt(req.getParameter("menuId"));
		int quantity=Integer.parseInt(req.getParameter("quantity"));
		
		cart.updateItem(itemId, quantity);
	}
	
	private void removeItemFromCart(HttpServletRequest req,Cart cart) {
		int itemId=Integer.parseInt(req.getParameter("menuId"));
		cart.removeItem(itemId);
	}
}
