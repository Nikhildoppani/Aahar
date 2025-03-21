// Store cart items in localStorage
/*let cart = JSON.parse(localStorage.getItem('cart')) || {};



// Initialize the menu page
/*function initMenuPage() {
  const menuItems = document.querySelectorAll('.menu-item');
  
  menuItems.forEach(item => {
    const itemId = item.dataset.id;
   // const addToCartBtn = item.querySelector('.add-to-cart');
    const quantityControls = item.querySelector('.quantity-controls');
    const quantityDisplay = item.querySelector('.quantity');
    const plusBtn = item.querySelector('.plus');
    const minusBtn = item.querySelector('.minus');

    // Restore cart state
    if (cart[itemId]) {
      addToCartBtn.style.display = 'none';
      quantityControls.style.display = 'flex';
      quantityDisplay.textContent = cart[itemId];
    }

    addToCartBtn.addEventListener('click', () => {
      cart[itemId] = 1;
      addToCartBtn.style.display = 'none';
      quantityControls.style.display = 'flex';
      quantityDisplay.textContent = cart[itemId];
      saveCart();
      updateCartCount();
    });

    plusBtn?.addEventListener('click', () => {
      cart[itemId] = (cart[itemId] || 0) + 1;
      quantityDisplay.textContent = cart[itemId];
      saveCart();
      updateCartCount();
    });

    minusBtn?.addEventListener('click', () => {
      cart[itemId] = (cart[itemId] || 0) - 1;
      if (cart[itemId] <= 0) {
        delete cart[itemId];
        addToCartBtn.style.display = 'block';
        quantityControls.style.display = 'none';
      } else {
        quantityDisplay.textContent = cart[itemId];
      }
      saveCart();
      updateCartCount();
    });
  });

  updateCartCount();
}
*/
// Initialize the cart page
/*function initCartPage() {
  const cartItems = document.querySelector('.cart-items');
  const subtotalElement = document.querySelector('.subtotal');
  const taxElement = document.querySelector('.tax');
  const totalElement = document.querySelector('.total-amount');
  const orderNowBtn = document.querySelector('.order-now');

  if (!cartItems) return;*/

  /* Get all menu items
  const menuItems = [
    {
      id: 1,
      name: "Butter Chicken",
      description: "Tender chicken cooked in rich, creamy tomato sauce",
      price: 299,
      image: "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?auto=format&fit=crop&w=300&q=80"
    },
    {
      id: 2,
      name: "Paneer Tikka",
      description: "Grilled cottage cheese with spices and vegetables",
      price: 249,
      image: "https://images.unsplash.com/photo-1567188040759-fb8a883dc6d8?auto=format&fit=crop&w=300&q=80"
    },
    {
      id: 3,
      name: "Biryani",
      description: "Fragrant rice dish with aromatic spices and herbs",
      price: 349,
      image: "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?auto=format&fit=crop&w=300&q=80"
    }
  ];*/

  // Clear existing items
  /*cartItems.innerHTML = '';

  // Calculate totals
  let subtotal = 0;
  
  // Add items to cart page
  /*Object.entries(cart).forEach(([itemId, quantity]) => {
    const item = menuItems.find(item => item.id === parseInt(itemId));
    if (!item) return;

    subtotal += item.price * quantity;

    const itemElement = document.createElement('div');
    itemElement.className = 'menu-item';
    itemElement.innerHTML = `
      <img src="${item.image}" alt="${item.name}" class="item-image">
      <div class="item-details">
        <div class="item-header">
          <h3 class="item-name">${item.name}</h3>
        </div>
        <p class="item-description">${item.description}</p>
        <div class="item-footer">
          <span class="item-price">₹${item.price}</span>
        </div>
      </div>
      <div class="cart-actions">
        <div class="quantity-controls">
          <button class="quantity-btn minus" data-id="${item.id}">-</button>
          <span class="quantity">${quantity}</span>
          <button class="quantity-btn plus" data-id="${item.id}">+</button>
        </div>
      </div>
    `;

    cartItems.appendChild(itemElement);
  });*/

  // Add event listeners for quantity buttons in cart
 /* cartItems.addEventListener('click', (e) => {
    if (e.target.classList.contains('quantity-btn')) {
      const itemId = e.target.dataset.id;
      if (e.target.classList.contains('plus')) {
        cart[itemId]++;
      } else if (e.target.classList.contains('minus')) {
        cart[itemId]--;
        if (cart[itemId] <= 0) {
          delete cart[itemId];
        }
      }
      saveCart();
      initCartPage(); // Refresh the cart page
    }
  });*/

  // Update summary
 /* const tax = subtotal * 0.05;
  const deliveryCharge = 40;
  const total = subtotal + tax + deliveryCharge;

  subtotalElement.textContent = `₹${subtotal}`;
  taxElement.textContent = `₹${tax.toFixed(2)}`;
  totalElement.textContent = `₹${total.toFixed(2)}`;

  // Order now button
  /*orderNowBtn?.addEventListener('click', () => {
    cart = {};
    saveCart();
  });*/
}

// Save cart to localStorage
/*function saveCart() {
  localStorage.setItem('cart', JSON.stringify(cart));
}*/

// Initialize the appropriate page
/*if (document.querySelector('.menu-items')) {
  initMenuPage();
} else if (document.querySelector('.cart-items')) {
  initCartPage();
}*/
