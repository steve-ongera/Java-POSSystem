package com.pos.database;

import com.pos.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Product Data Access Object for database operations
 * Save as: src/main/java/com/pos/database/ProductDAO.java
 */
public class ProductDAO {
    
    /**
     * Get all products from database
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price, category, barcode, stock FROM products ORDER BY name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("barcode"),
                    rs.getInt("stock")
                );
                products.add(product);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving products: " + e.getMessage());
            e.printStackTrace();
        }
        
        return products;
    }
    
    /**
     * Find product by barcode
     * @param barcode Product barcode
     * @return Product object or null if not found
     */
    public Product findByBarcode(String barcode) {
        String sql = "SELECT id, name, price, category, barcode, stock FROM products WHERE barcode = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, barcode);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("barcode"),
                    rs.getInt("stock")
                );
            }
            
        } catch (SQLException e) {
            System.err.println("Error finding product by barcode: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Add new product to database
     * @param product Product to add
     * @return true if successful, false otherwise
     */
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, category, barcode, stock) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getCategory());
            stmt.setString(4, product.getBarcode());
            stmt.setInt(5, product.getStock());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Get the generated ID and set it to the product
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Update product stock
     * @param productId Product ID
     * @param newStock New stock quantity
     * @return true if successful, false otherwise
     */
    public boolean updateStock(int productId, int newStock) {
        String sql = "UPDATE products SET stock = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, newStock);
            stmt.setInt(2, productId);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating product stock: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Delete product by ID
     * @param productId Product ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
        }
        
        return false;
    }
    
    /**
     * Get products by category
     * @param category Product category
     * @return List of products in the category
     */
    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price, category, barcode, stock FROM products WHERE category = ? ORDER BY name";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("barcode"),
                    rs.getInt("stock")
                );
                products.add(product);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving products by category: " + e.getMessage());
            e.printStackTrace();
        }
        
        return products;
    }
}