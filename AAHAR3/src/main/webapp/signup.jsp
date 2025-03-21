<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aahar - Sign Up</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <div class="form-container">
            <a href="index.html">
                <div class="login-container">
                    <img src="images/logo.jpg" alt="Logo" class="login-logo">
                </div>
            </a>
            <p class="subtitle">Create your account</p>
            <script>
		        const urlParams = new URLSearchParams(window.location.search);
		        const errorMessage = urlParams.get("error");
		
		        if (errorMessage) {
		            document.write("<p style='color: red;'>" + decodeURIComponent(errorMessage) + "</p>");
		        }
		    </script>
			<%-- Display error message if it exists --%>
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <p style="color: red; text-align: center;"><%= errorMessage %></p>
            <% } %>
            <form class="signup-form" action="SignupServlet" method="post">
                <div class="form-group">
                    <label for="fullname">Full Name</label>
                    <input type="text" id="fullname" name="fullname" required placeholder="Enter your full name">
                </div>

                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" id="email" name="email" required placeholder="Enter your email">
                </div>

                <div class="form-group">
                    <label for="phone">Phone Number</label>
                    <input type="tel" id="phone" name="phone" required placeholder="Enter your phone number">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required placeholder="Create a password">
                    <p class="password-hint">Must be at least 8 characters long</p>
                </div>

                <div class="terms">
                    <label>
                        <input type="checkbox" required>
                        <span>I agree to the <a href="#">Terms of Service</a> and <a href="#">Privacy Policy</a></span>
                    </label>
                </div>

                <button type="submit" class="submit-btn">Create Account</button>
            </form>

            <p class="switch-page">
                Already have an account? <a href="login.jsp">Sign in</a>
            </p>
        </div>
    </div>
</body>
</html>