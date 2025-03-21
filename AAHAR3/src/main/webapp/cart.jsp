<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.aahar.model.Cart,com.aahar.model.CartItem " %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cart - Aahar Food Delivery</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <link rel="stylesheet" href="cart.css">
</head>
<body>
  <header class="header">
    <div class="header-content">
     
    <%
	    String previousPage = (String) session.getAttribute("previousPage");
	    if (previousPage == null) {
	        previousPage = "home.jsp"; // Default to home.jsp if session is empty
	    }
	%>
      <a  href="<%= previousPage %>" class="back-button">←</a>
      <div class="header-text">
        <h1>Your Cart</h1>
        <p>Review your order</p>
      </div>
    </div>
  </header>

  <main class="container">
    <div class="cart-container">
      <div class="cart-items">
        <!-- Cart Item 1 -->
        <%
        	Cart cart=(Cart) session.getAttribute("cart");
        	Integer restaurantId=(Integer) session.getAttribute("restaurantId");
        
        	if(cart !=null && !cart.getItems().isEmpty()){
        		
        		for(CartItem item:cart.getItems().values()){
        		
        %>
        <div class="cart-item">
          <img src="<%= item.getImgPath() %>" 
               alt="Butter Chicken" 
               class="item-image">
          <div class="item-details">
            <h3><%= item.getItemName() %></h3>
            <p class="item-description"><%= item.getItemDescription() %></p>
            <div class="item-price">₹<%= item.getItemPrice() %></div>
          </div>
          <div class="quantity-controls">
          	<form action="cart" method="post">
          		<input type="hidden" name="restaurantId" value=" <%= item.getRestaurantId()%>">
          		<input type="hidden" name="menuId" value="<%= item.getItemId() %>">
          		<input type="hidden" name="action" value="update">
          		<input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
          		<button type="submit" class="quantity-btn minus">-</button>
          	</form>
            <span class="quantity"><%= item.getQuantity() %></span>
            <form action="cart" method="post">
            	<input type="hidden" name="restaurantId" value=" <%= item.getRestaurantId()%>">
          		<input type="hidden" name="menuId" value="<%= item.getItemId() %>">
          		<input type="hidden" name="action" value="update">
          		<input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
          		<button type="submit" class="quantity-btn plus">+</button>
          	</form>
          </div>
          <form action="cart" method="post">
          	<input type="hidden" name="restaurantId" value=" <%= item.getRestaurantId()%>">
          	<input type="hidden" name="menuId" value="<%= item.getItemId() %>">
          	<input type="hidden" name="action" value="remove">
          	<button type="submit" class="remove-btn">
            <i class="fas fa-trash"></i>
          	</button>
          </form>
          
        </div>
		<%
        		}
        		 
        	}
		%>
        

      <div class="cart-summary">
        <h3>Order Summary</h3>
            <%
		        double subtotal = 0.0;
		        double taxRate = 0.05; // 5% tax
		        double deliveryCharge = 40.0;
		    
		        if (cart != null && !cart.getItems().isEmpty()) {
		            for (CartItem item : cart.getItems().values()) {
		                subtotal += item.getItemPrice() * item.getQuantity();
		            }
		        }
		    
		        double tax = subtotal * taxRate;
		        double total = subtotal + tax + deliveryCharge;
   			 %>
   			
        <div class="summary-item">
          <span>Subtotal</span>
          <span class="subtotal">₹<%= String.format("%.2f", subtotal) %></span>
        </div>
        <div class="summary-item">
          <span>Tax (5%)</span>
          <span class="tax">₹<%= String.format("%.2f", tax) %></span>
        </div>
        <div class="summary-item">
          <span>Delivery Charge</span>
          <span class="delivery">₹<%= String.format("%.2f", deliveryCharge) %></span>
        </div>
        <div class="summary-item total">
          <span>Total</span>
          <span class="total-amount">₹<%= String.format("%.2f", total) %></span>
        </div>
        <%
		    session.setAttribute("subtotal", subtotal);
		    session.setAttribute("tax", tax);
		    session.setAttribute("deliveryCharge", deliveryCharge);
		    session.setAttribute("total", total);
		%>
        <%
		    boolean isCartEmpty = (cart == null || cart.getItems().isEmpty());
		%>
		
		<a href="payment.jsp" class="order-now <%= isCartEmpty ? "disabled" : "" %>"
		   <%= isCartEmpty ? "style='pointer-events: none; opacity: 0.5;'" : "" %>>
		   Proceed to Payment
		</a>
      </div>
    </div>
  </main>
  
  
</body>
</html>