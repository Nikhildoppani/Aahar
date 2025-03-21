<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,com.aahar.model.Restaurant"  %>
<%@ page import="java.util.*,com.aahar.model.Cart,com.aahar.model.CartItem " %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aahar Food Delivery</title>
    <link rel="stylesheet" href="index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style type="text/css">
    	.cart-icon-wrapper {
    position: relative;
  }
  
  .cart-count {
    position: absolute;
    top: -8px;
    right: 45px;
    background-color: #ff5722;
    color: white;
    font-size: 0.75rem;
    font-weight: bold;
    padding: 0.125rem 0.375rem;
    border-radius: 9999px;
    min-width: 1.25rem;
    height: 1.25rem;
    display: flex;
    align-items: center;
    justify-content: center;
  }
    	
    </style>
</head>
<body>
    <header>
        <nav>
            <div class="left-section">
                <div class="logo">
                    <a href="">
                        <img src="images/logo.jpg" alt="Aahar Logo">
                    </a>
                </div>
                <div class="location">
                    <i class="fas fa-map-marker-alt"></i>
                    <span>Select Location</span>

                    <select id="locationSelect">
                        <option value="new-york">Bangalore</option>
                        <option value="los-angeles">Hyderabad</option>
                        <option value="chicago">Chicago</option>
                        <option value="miami">Chennai</option>
                        <option value="san-francisco">Mumbai</option>
                    </select>
                </div>
            </div>
            <div class="right-section">
                <div class="search">
                    <input type="text" placeholder="Search for restaurants, cuisine...">
                    <i class="fas fa-search"></i>
                </div>
                <a href="profile" class="login">
                    <i class="fas fa-user"></i>
                    <span>Profile</span>
                </a>
                <a href="history" class="login">
                  <i class="fa-solid fa-clock-rotate-left"></i>
                    <span>History</span>
                </a>
                <!--<a href="cart.jsp" onclick="setPreviousPage('home.jsp')" class="cart">
                    <i class="fas fa-shopping-cart"></i>
                    <span>Cart</span> 
                </a>-->
                <% session.setAttribute("previousPage", "home"); %>
                <a href="cart.jsp"  class="cart">
			      <%
			        	Cart cart=(Cart) session.getAttribute("cart");
			      		int cartItemCount=(cart != null) ? cart.getCartSize() : 0;
			      		System.out.println(cartItemCount);
			        %>
			        <div class="cart-icon-wrapper">
			          <i class="fas fa-shopping-cart"></i>
			          <span class="cart-count"><%= cartItemCount %></span>
			          <span>Cart</span>
			        </div>
			      </a>
            </div>
        </nav>
    </header>

    <main>
        <h2>Best Offers</h2>
        <div class="banner"></div>
        
        <script>
            const images = document.querySelectorAll('.banner img');
            let currentIndex = 0;
        
            function changeImage() {
                images[currentIndex].classList.remove('active');
                currentIndex = (currentIndex + 1) % images.length;
                images[currentIndex].classList.add('active');
            }
        
            // Change the image every 3 seconds
            setInterval(changeImage, 3000);
            
            
        </script>
        
        <h2>Popular Restaurants</h2>
        <div class="restaurant-grid">
        <%
        List<Restaurant> restaurant=(List<Restaurant>)request.getAttribute("restaurants");
        	for(Restaurant r:restaurant){
        %>
            <div class="restaurant-card">
                <img src="<%=r.getImgPath() %>" alt="Indian Food">
                <h3><%= r.getName() %></h3>
                <div class="cuisine-tags">
                    <span class="cuisine-tag"><%= r.getCuisineType() %></span>
                </div>
                <p><span class="rating"><%= r.getRating() %> ★</span> • 500+ ratings</p>
                <p><i class="far fa-clock"></i> <%= r.getEta() %> min • <i class="fas fa-rupee-sign"></i> 200 for two</p>
                <a href="menu?restaurantId=<%= r.getRestaurantId()%>" onclick="setPreviousPage('home.jsp')"class="menu-btn">View Menu</a>
            </div>
            
        <%
        	}
        %>
            
        </div>
    </main>

    <script>
        // Hide header on scroll down, show on scroll up
        let lastScroll = 0;
        window.addEventListener('scroll', () => {
            const currentScroll = window.pageYOffset;
            const header = document.querySelector('header');
            
            if (currentScroll <= 0) {
                header.classList.remove('scroll-up');
                return;
            }
            
            if (currentScroll > lastScroll && !header.classList.contains('scroll-down')) {
                header.classList.remove('scroll-up');
                header.classList.add('scroll-down');
            } else if (currentScroll < lastScroll && header.classList.contains('scroll-down')) {
                header.classList.remove('scroll-down');
                header.classList.add('scroll-up');
            }
            lastScroll = currentScroll;
        });
    </script>

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