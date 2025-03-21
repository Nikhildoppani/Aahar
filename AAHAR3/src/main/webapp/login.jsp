<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aahar - Login</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="profileError.css">
</head>

<body>
    <div class="container">
        <div class="form-container">
           <!-- HTML -->
            <a href="">
                <div class="login-container">
                    <img src="images/logo.jpg" alt="Logo" class="login-logo" >
                </div>
            </a>

            <p class="subtitle">Sign in to your account</p>
			<div class="message-container">
				   <%
				    String errorMessage = (String) session.getAttribute("emailerror");
				    if (errorMessage != null) { 
				%>
				    <div class="error-message"><%= errorMessage %></div>
				<%
				        session.removeAttribute("emailerror"); // Clear message after displaying
				    } 
				%>
			 </div>
            <form class="login-form" action="login" method="post">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="email" id="email" required placeholder="Enter your email" name="email">
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" required placeholder="Enter your password" name="password">
                </div>

                <div class="form-options">
                    <label class="remember-me">
                        <input type="checkbox" id="remember">
                        <span>Remember me</span>
                    </label>
                    <a href="#" class="forgot-password">Forgot password?</a>
                </div>

                <button type="submit" class="submit-btn">Sign in</button>
            </form>

            <p class="switch-page">
                Don't have an account? <a href="signup.jsp">Sign up</a>
            </p>
        </div>
    </div>
</body>
</html>