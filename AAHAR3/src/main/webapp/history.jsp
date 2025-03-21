<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.aahar.model.OrderHistory" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Order History - Aahar Food Delivery</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <link rel="stylesheet" href="history.css">
</head>
<body>
  <header class="header">
    <div class="header-content">
      <a href="home" class="back-button">←</a>
      <div class="header-text">
        <h1>Order History</h1>
        <p>Your past orders</p>
      </div>
    </div>
  </header>

  <main class="container">
    <div class="history-container">
    <%
    List<OrderHistory> getAllOrders=(List<OrderHistory>)request.getAttribute("orders");
    for(OrderHistory o:getAllOrders){
    %>
      <!-- Order Card -->
      <div class="order-card">
        <div class="order-date">
          <div class="date"><%= o.getDay() %></div>
          <div class="month"><%= o.getMonth() %></div>
          <div class="year"><%= o.getYear() %></div>
          <div class="time"><%= o.getTime() %></div>
        </div>
        <div class="order-details">
          <h3>Order </h3>
          <div class="items">
            <div class="item">
              <div class="item-info">
                <span class="item-name"><%= o.getOrdered_items() %></span>
              </div>
            </div>
          </div>
          <div class="delivery-status delivered">
            <i class="fas fa-check-circle"></i> <%= o.getStatus() %>
          </div>
        </div>
        <div class="order-amount">
          <div class="amount">₹<%= o.getTotal_amount() %></div>
          <div class="amount-breakdown">
            <span>Items: ₹<%= o.getItems_price() %></span>
            <span>Tax: ₹<%= o.getTAX() %></span>
            <span>Delivery: ₹<%= o.getDelivery() %></span>
          </div>
        </div>
      </div>
	<%
    }
	%>
     
     


    </div>
  </main>
</body>
</html>