<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.aahar.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile - Aahar Food Delivery</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  <link rel="stylesheet" href="profile.css">
  <link rel="stylesheet" href="profileError.css">
</head>
<body>
  <header class="header">
    <div class="header-content">
      <a href="home" class="back-button">←</a>
      <div class="header-text">
        <h1>Profile</h1>
        <p>Manage your account</p>
      </div>
      <a href="login.jsp" class="logout-button">
        <i class="fas fa-sign-out-alt"></i>
        <span>Logout</span>
      </a>
    </div>
    <style>
	 
	</style>
  </header>
	<div class="message-container">
    <%
        String errorMessage = (String) session.getAttribute("error");
        String successMessage = (String) session.getAttribute("success");

        if (errorMessage != null) { 
    %>
        <div class="error-message"><%= errorMessage %></div>
    <%
            session.removeAttribute("error"); // Clear error message after displaying
        } 

        if (successMessage != null) { 
    %>
        <div class="success-message"><%= successMessage %></div>
    <%
            session.removeAttribute("success"); // Clear success message after displaying
        } 
    %>
  </div>
  <main class="container">
    <div class="profile-container">
      <!-- Logo Section -->
      <div class="logo-section">
        <div class="logo">
          <img src="images/Logo_bg.png" alt="Logo" class="login-logo">
        </div>
      </div>

      <!-- Profile Information -->
      <div class="profile-info">
        <!-- Username (Non-editable) -->
        <%
        List<User> userInfo = (List<User>)request.getAttribute("user");
      	for(User u :userInfo){
        %>
        <form id="updateProfileForm" action="UpdateProfileServlet" method="POST">
        <div class="info-item">
          <label>Username</label>
          <div class="value-container">
            <span class="value">@<%= u.getUsername() %></span>
          </div>
        </div>

        <!-- Name (Editable) -->
        <div class="info-item">
          <label>Name</label>
          <div class="value-container editable">
            <input type="text" name="name" value="<%= u.getName() %>" class="edit-input" id="nameInput" readonly>
            <button type="button" class="edit-btn" onclick="toggleEdit(this, 'nameInput')">
              <i class="fas fa-pencil"></i>
            </button>
          </div>
        </div>

        <!-- Phone (Editable) -->
        <div class="info-item">
          <label>Phone Number</label>
          <div class="value-container editable">
            <input type="tel" value="<%= u.getPhone() %>" class="edit-input" id="phoneInput" readonly>
            <!-- <button class="edit-btn" onclick="toggleEdit(this, 'phoneInput')">
              <i class="fas fa-pencil"></i> -->
            </button>
          </div>
        </div>

        <!-- Email (Non-editable) -->
        <div class="info-item">
          <label>Email</label>
          <div class="value-container">
            <span class="value"><%= u.getEmail() %></span>
          </div>
        </div>

        <!-- Address (Editable) -->
        <div class="info-item">
          <label>Address</label>
          <div class="value-container editable">
            <textarea class="edit-input address" id="addressInput" name="address" readonly><%= u.getAddress() %></textarea>
            <button type="button" class="edit-btn" onclick="toggleEdit(this, 'addressInput')">
              <i class="fas fa-pencil"></i>
            </button>
          </div>
        </div>
	
        <!-- Action Buttons -->
        <div class="action-buttons">
          <button type="button" class="cancel-btn" onclick="cancelChanges()">Cancel</button>
          <button type="submit" class="save-btn" onclick="saveChanges(event)">Save Changes</button>
        </div>
        </form>
      </div>
     	
      <%
      	}
	%>
    </div>
  </main>

  <script>
  // Store original values for cancel functionality
  let originalValues = {
    nameInput: document.getElementById('nameInput').value,
    phoneInput: document.getElementById('phoneInput').value,
    addressInput: document.getElementById('addressInput').value
  };

  let editingActive = false;

  function toggleEdit(button, inputId) {
    const input = document.getElementById(inputId);
    const icon = button.querySelector('i');

    if (input.readOnly) {
      // Enable editing
      input.readOnly = false;
      input.focus();
      icon.classList.remove('fa-pencil');
      icon.classList.add('fa-check');
      input.classList.add('editing');
      editingActive = true;
    } else {
      // Disable editing
      input.readOnly = true;
      icon.classList.remove('fa-check');
      icon.classList.add('fa-pencil');
      input.classList.remove('editing');
    }
  }

  /*function cancelChanges() {
    // Restore original values
    for (let inputId in originalValues) {
      const input = document.getElementById(inputId);
      input.value = originalValues[inputId];
      input.readOnly = true;

      // Reset edit button icons
      const button = input.parentElement.querySelector('.edit-btn');
      const icon = button.querySelector('i');
      icon.classList.remove('fa-check');
      icon.classList.add('fa-pencil');
      input.classList.remove('editing');
    }
    editingActive = false;
  }*/
  function cancelChanges() {
	  location.reload(); // Refresh the page
	}

  function saveChanges(event) {
    event.preventDefault(); // Prevent default form submission

    if (!editingActive) {
      alert('No changes to save');
      return;
    }


    // Submit form only if changes were made
    document.getElementById("updateProfileForm").submit();
  }
</script>

</body>
</html>