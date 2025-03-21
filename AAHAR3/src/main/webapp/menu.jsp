<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.aahar.model.Menu" %>
<%@ page import="java.util.*,com.aahar.model.Cart,com.aahar.model.CartItem " %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Aahar Food Delivery</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <link rel="stylesheet" href="menu.css">
  <link rel="stylesheet" href="index.css">
</head>
<body>
  <header class="header">
    <div class="header-content">
      <a href="home"  class="back-button">←</a>
      <div class="header-text">
        <h1>Aahar</h1>
        <p>Delicious food delivered to your doorstep</p>
      </div>
      <% 
      Integer restaurantIds=(Integer) session.getAttribute("restaurantId"); 
      session.setAttribute("previousPage", "menu?restaurantId=" + restaurantIds); %>
      <a href="cart.jsp"  class="cart-icon">
      <%
        	Cart cart=(Cart) session.getAttribute("cart");
      		int cartItemCount=(cart != null) ? cart.getCartSize() : 0;
      		System.out.println(cartItemCount);
        %>
        <div class="cart-icon-wrapper">
          <i class="fas fa-shopping-cart"></i>
          <span class="cart-count"><%= cartItemCount %></span>
        </div>
      </a>
    </div>
  </header>

  <main class="container">
    <h2>Our Menu</h2>
    <div class="menu-items">
      <!-- Butter Chicken -->
      <%
      	 cart = (Cart) session.getAttribute("cart"); // Retrieve cart from session
      	Integer restaurantId = (Integer) session.getAttribute("restaurantId");
      	 
      	List<Menu> menuList = (List<Menu>)request.getAttribute("menus");
      	for(Menu m :menuList){
      		 CartItem cartItem = (cart != null) ? cart.getItems().get(m.getMenuId()) : null;
             int quantity = (cartItem != null) ? cartItem.getQuantity() : 0;
      %>
      <div class="menu-item" data-id="1" data-name="Butter Chicken" data-price="299">
        <img src="<%= m.getImgPath() %>" 
             alt="Butter Chicken" 
             class="item-image">
        <div class="item-details">
          <div class="item-header">
            <h3 class="item-name"><%= m.getItemname() %></h3>
            <span class="item-rating">
              <span class="star">★</span>
              <%= m.getRatings() %>
            </span>
          </div>
          <p class="item-description"><%= m.getDiscription() %></p>
          <div class="item-footer">
            <span class="item-price">₹<%= m.getPrice() %></span>
            <span class="availability available"> 
            		   <%= m.getIsAvailable().equals("yes") ? "Available" : "Not Available" %>
            </span>
          </div>
        </div>
        <div class="cart-actions">
        	<% if (quantity == 0) { %>
        	<form action="cart" method="post">
            <input type="hidden" name="restaurantId" value=" <%= m.getRestaurantId()%>">
            <input type="hidden" name="menuId" value="<%= m.getMenuId()%>">
            <input type="hidden" name="quantity" value="1" min="1">
            <input type="hidden" name="action" value="add">
            <button type="submit" class="add-to-cart">Add to Cart</button>
          </form>
         	<% } else { %>
          <div class="quantity-controls">
                <form action="cart" method="post">
                    <input type="hidden" name="restaurantId" value="<%= m.getRestaurantId() %>">
                    <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="quantity" value="<%= quantity - 1 %>">
                    <button type="submit" class="quantity-btn minus">-</button>
                </form>
                <span class="quantity"><%= quantity %></span>
                <form action="cart" method="post">
                    <input type="hidden" name="restaurantId" value="<%= m.getRestaurantId() %>">
                    <input type="hidden" name="menuId" value="<%= m.getMenuId() %>">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="quantity" value="<%= quantity + 1 %>">
                    <button type="submit" class="quantity-btn plus">+</button>
                </form>
            </div>
        <% } %>
        </div>
      </div>

      <%
      	}
      %>

      

      
    </div>
  </main>
  <footer>
        
    <div class="footer-content">
      <!-- Brand Section -->
      <div class="footer-section">
        <h3 class="brand">Aahar</h3>
        <p class="brand-description">
          Delivering happiness with every meal. Experience the best food delivery service in your city.
        </p>
        <div class="social-links">
          <a href="#" class="social-link">
            <img src="https://raw.githubusercontent.com/simple-icons/simple-icons/develop/icons/facebook.svg" alt="Facebook">
          </a>
          <a href="#" class="social-link">
              <img src="images/twitter (1).png" alt="Twitter">
          </a>
          <a href="#" class="social-link">
            <img src="https://raw.githubusercontent.com/simple-icons/simple-icons/develop/icons/instagram.svg" alt="Instagram">
          </a>
          <a href="#" class="social-link">
            <img src="https://raw.githubusercontent.com/simple-icons/simple-icons/develop/icons/youtube.svg" alt="YouTube">
          </a>
        </div>
      </div>

      <!-- Quick Links -->
      <div class="footer-section">
        <h4>Quick Links</h4>
        <ul class="footer-links">
          <li><a href="#">Home</a></li>
          <li><a href="#">About Us</a></li>
          <li><a href="#">Menu</a></li>
          <li><a href="#">Restaurants</a></li>
          <li><a href="#">Contact</a></li>
        </ul>
      </div>

      <!-- Contact Info -->
      <div class="footer-section">
        <h4>Contact Us</h4>
        <ul class="contact-info">
          <li>
            <img src="https://raw.githubusercontent.com/simple-icons/simple-icons/develop/icons/googlemaps.svg" alt="Location">
            <span>123 Food Street, Foodie City, FC 12345</span>
          </li>
          <li>
            <img src="images/phone.png" alt="Phone">
            <span>+1 234 567 8900</span>
          </li>
          <li>
            <img src="images/support.png" alt="Email">
            <span>support@aahar.com</span>
          </li>
        </ul>
      </div>

      <!-- Newsletter -->
      <div class="footer-section">
        <h4>Newsletter</h4>
        <p>Subscribe to our newsletter for offers and updates</p>
        <form class="newsletter-form">
          <input type="email" placeholder="Enter your email" required>
          <button type="submit">Subscribe</button>
        </form>
      </div>
    </div>

    <!-- Bottom Bar -->
    <div class="footer-bottom">
      <p>&copy; 2025 Aahar. All rights reserved.</p>
    </div>
  </footer>
	
  
</body>


</html>