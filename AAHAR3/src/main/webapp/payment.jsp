<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.aahar.model.User,com.aahar.daoimplementation.UserDAOImplementation" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Payment - Aahar Food Delivery</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <link rel="stylesheet" href="payment.css">
</head>
<body>
  <header class="header">
    <div class="header-content">
      <a href="javascript:history.back()" class="back-button">←</a>
      <div class="header-text">
        <h1>Payment</h1>
        <p>Complete your order</p>
      </div>
    </div>
  </header>

  <main class="container">
    <div class="payment-container">
    <%
    	int userId=(Integer) session.getAttribute("userId");
	    double subtotal = (double) session.getAttribute("subtotal");
	    double tax = (double) session.getAttribute("tax");
	    double deliveryCharge = (double) session.getAttribute("deliveryCharge");
	    double total = (double) session.getAttribute("total");
	%>
      <!-- Order Summary -->
      <div class="order-summary">
        <h2>Order Summary</h2>
        <div class="summary-items">
          <div class="summary-item">
            <span>Subtotal</span>
            <span>₹<%= String.format("%.2f", subtotal) %></span>
          </div>
          <div class="summary-item">
            <span>Tax (5%)</span>
            <span>₹<%= String.format("%.2f", tax)%></span>
          </div>
          <div class="summary-item">
            <span>Delivery Charge</span>
            <span>₹<%= String.format("%.2f", deliveryCharge) %></span>
          </div>
          <div class="summary-item total">
            <span>Total</span>
            <span>₹<%= String.format("%.2f", total) %></span>
          </div>
        </div>
      </div>

      <!-- Delivery Address -->
      <div class="delivery-address">
      <%
      	UserDAOImplementation userDAOImp=new UserDAOImplementation();
      	User userInfo=userDAOImp.getUser(userId);
      %>
        <div class="section-header">
          <h2>Delivery Address</h2>
          <a href="#" class="change-link">Change</a>
        </div>
        <div class="address-card">
          <div class="address-icon">
            <i class="fas fa-map-marker-alt"></i>
          </div>
          <div class="address-details">
            <h3>Home</h3>
            <p><%= userInfo.getAddress() %></p>
          </div>
        </div>
      </div>

      <!-- Payment Methods -->
      <div class="payment-methods">
        <h2>Payment Method</h2>
        <div class="payment-options">
          <label class="payment-option">
            <input type="radio" name="payment-method" value="card" checked>
            <div class="option-content">
              <div class="option-icon">
                <i class="fas fa-credit-card"></i>
              </div>
              <div class="option-details">
                <h3>Credit/Debit Card</h3>
                <p>Pay securely with your card</p>
              </div>
            </div>
          </label>

          <label class="payment-option">
            <input type="radio" name="payment-method" value="upi">
            <div class="option-content">
              <div class="option-icon">
                <i class="fas fa-mobile-alt"></i>
              </div>
              <div class="option-details">
                <h3>UPI</h3>
                <p>Pay using UPI apps</p>
              </div>
            </div>
          </label>

          <label class="payment-option">
            <input type="radio" name="payment-method" value="cod">
            <div class="option-content">
              <div class="option-icon">
                <i class="fas fa-money-bill-wave"></i>
              </div>
              <div class="option-details">
                <h3>Cash on Delivery</h3>
                <p>Pay when your order arrives</p>
              </div>
            </div>
          </label>
        </div>
      </div>

      <!-- Card Details Form -->
      <div class="card-details" id="card-details">
        <h2>Card Details</h2>
        <form class="card-form">
          <div class="form-group">
            <label for="card-number">Card Number</label>
            <div class="input-with-icon">
              <input type="text" id="card-number" placeholder="1234 5678 9012 3456" maxlength="19">
              <div class="card-icons">
                <i class="fab fa-cc-visa"></i>
                <i class="fab fa-cc-mastercard"></i>
                <i class="fab fa-cc-amex"></i>
              </div>
            </div>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label for="expiry">Expiry Date</label>
              <input type="text" id="expiry" placeholder="MM/YY" maxlength="5">
            </div>
            <div class="form-group">
              <label for="cvv">CVV</label>
              <input type="text" id="cvv" placeholder="123" maxlength="3">
            </div>
          </div>

          <div class="form-group">
            <label for="name-on-card">Name on Card</label>
            <input type="text" id="name-on-card" placeholder="John Doe">
          </div>

          <div class="save-card">
            <label class="checkbox-container">
              <input type="checkbox" checked>
              <span class="checkmark"></span>
              Save this card for future payments
            </label>
          </div>
        </form>
      </div>

      <!-- UPI Details -->
      <div class="upi-details" id="upi-details" style="display: none;">
        <h2>UPI Payment</h2>
        <form class="upi-form">
          <div class="form-group">
            <label for="upi-id">UPI ID</label>
            <input type="text" id="upi-id" placeholder="yourname@upi">
          </div>
          <div class="upi-apps">
            <h3>Or pay using</h3>
            <div class="upi-app-options">
              <div class="upi-app">
                <i class="fab fa-google-pay"></i>
                <span>Google Pay</span>
              </div>
              <div class="upi-app">
                <i class="fas fa-money-bill-transfer"></i>
                <span>PhonePe</span>
              </div>
              <div class="upi-app">
                <i class="fas fa-wallet"></i>
                <span>Paytm</span>
              </div>
            </div>
          </div>
        </form>
      </div>

      <!-- Place Order Button -->
      <form action="confirmOrder" method="post">
      	<button  class="place-order-btn">
	        Place Order <span class="order-total">₹<%= String.format("%.2f", total) %></span>
	      </button>
      </form>
      
    </div>
  </main>

  <script>
    // Simple script to toggle between payment methods
    document.addEventListener('DOMContentLoaded', function() {
      const paymentOptions = document.querySelectorAll('input[name="payment-method"]');
      const cardDetails = document.getElementById('card-details');
      const upiDetails = document.getElementById('upi-details');

      paymentOptions.forEach(option => {
        option.addEventListener('change', function() {
          if (this.value === 'card') {
            cardDetails.style.display = 'block';
            upiDetails.style.display = 'none';
          } else if (this.value === 'upi') {
            cardDetails.style.display = 'none';
            upiDetails.style.display = 'block';
          } else {
            cardDetails.style.display = 'none';
            upiDetails.style.display = 'none';
          }
        });
      });

      // Format card number with spaces
      const cardNumberInput = document.getElementById('card-number');
      if (cardNumberInput) {
        cardNumberInput.addEventListener('input', function(e) {
          let value = e.target.value.replace(/\s+/g, '');
          if (value.length > 0) {
            value = value.match(new RegExp('.{1,4}', 'g')).join(' ');
          }
          e.target.value = value;
        });
      }

      // Format expiry date with slash
      const expiryInput = document.getElementById('expiry');
      if (expiryInput) {
        expiryInput.addEventListener('input', function(e) {
          let value = e.target.value.replace(/\//g, '');
          if (value.length > 2) {
            value = value.substring(0, 2) + '/' + value.substring(2);
          }
          e.target.value = value;
        });
      }
    });
  </script>
</body>
</html>