package com.pos.model;

import javafx.beans.property.*;

/**
 * CartItem model class
 * Save as: src/main/java/com/pos/model/CartItem.java
 */
public class CartItem {
    private final Product product;
    private final IntegerProperty quantity;
    private final DoubleProperty total;
    
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.total = new SimpleDoubleProperty(product.getPrice() * quantity);
        
        // Update total when quantity changes
        this.quantity.addListener((obs, oldVal, newVal) -> {
            this.total.set(product.getPrice() * newVal.intValue());
        });
    }
    
    // Product getter
    public Product getProduct() { 
        return product; 
    }
    
    // Quantity property methods
    public int getQuantity() { 
        return quantity.get(); 
    }
    
    public void setQuantity(int quantity) { 
        this.quantity.set(quantity); 
    }
    
    public IntegerProperty quantityProperty() { 
        return quantity; 
    }
    
    // Total property methods
    public double getTotal() { 
        return total.get(); 
    }
    
    public DoubleProperty totalProperty() { 
        return total; 
    }
    
    // Convenience methods
    public void increaseQuantity() {
        setQuantity(getQuantity() + 1);
    }
    
    public void decreaseQuantity() {
        if (getQuantity() > 1) {
            setQuantity(getQuantity() - 1);
        }
    }
    
    // For easy access to product properties in TableView
    public String getProductName() {
        return product.getName();
    }
    
    public double getProductPrice() {
        return product.getPrice();
    }
    
    public String getProductBarcode() {
        return product.getBarcode();
    }
    
    @Override
    public String toString() {
        return String.format("CartItem{product=%s, quantity=%d, total=%.2f}",
                product.getName(), getQuantity(), getTotal());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CartItem cartItem = (CartItem) obj;
        return product.equals(cartItem.product);
    }
    
    @Override
    public int hashCode() {
        return product.hashCode();
    }
}