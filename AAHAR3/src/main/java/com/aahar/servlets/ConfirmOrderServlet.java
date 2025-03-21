package com.aahar.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aahar.model.Cart;
import com.aahar.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/confirmOrder")
public class ConfirmOrderServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aahar";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "doppani";

    private static final String INSERT_ORDERS = "INSERT INTO `orders`(`user_id`, `restaurant_id`, `total_amount`) VALUES(?, ?, ?)";
    private static final String LAST_ORDER_ID = "SELECT MAX(`order_id`) FROM `orders`";
    private static final String INSERT_ORDER_ITEMS = "INSERT INTO `order_item`(`order_id`, `menu_id`, `quantity`) VALUES (?, ?, ?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");
        int userId = (Integer) session.getAttribute("userId");
        double total = (double) session.getAttribute("total");

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            resp.sendRedirect("cart.jsp?error=Cart is empty");
            return;
        }

        Connection conn = null;
        PreparedStatement insertOrderStmt = null;
        PreparedStatement lastOrderIdStmt = null;
        PreparedStatement insertOrderItemStmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            conn.setAutoCommit(false); // Start transaction

            // 1️⃣ Insert order into `orders` table
            insertOrderStmt = conn.prepareStatement(INSERT_ORDERS);
            insertOrderStmt.setInt(1, userId);
            insertOrderStmt.setInt(2, restaurantId);
            insertOrderStmt.setDouble(3, total);
            insertOrderStmt.executeUpdate();

            // 2️⃣ Retrieve the last inserted `order_id`
            lastOrderIdStmt = conn.prepareStatement(LAST_ORDER_ID);
            rs = lastOrderIdStmt.executeQuery();

            int lastOrderId = 0;
            if (rs.next()) {
                lastOrderId = rs.getInt(1);
            }

            if (lastOrderId == 0) {
                throw new SQLException("Failed to retrieve last order ID");
            }

            // 3️⃣ Insert cart items into `order_item` table
            insertOrderItemStmt = conn.prepareStatement(INSERT_ORDER_ITEMS);
            for (CartItem item : cart.getItems().values()) {
                insertOrderItemStmt.setInt(1, lastOrderId);
                insertOrderItemStmt.setInt(2, item.getItemId());
                insertOrderItemStmt.setInt(3, item.getQuantity());
                insertOrderItemStmt.addBatch();
            }

            // Execute batch insert for order items
            insertOrderItemStmt.executeBatch();

            // 4️⃣ Commit transaction (ensures all operations succeed together)
            conn.commit();

            // Clear cart after successful order
            session.removeAttribute("cart");

            // Redirect to confirmation page
            resp.sendRedirect("history");

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback if any error occurs
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            resp.sendRedirect("cart.jsp?error=Order failed");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (insertOrderStmt != null) insertOrderStmt.close();
                if (lastOrderIdStmt != null) lastOrderIdStmt.close();
                if (insertOrderItemStmt != null) insertOrderItemStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
