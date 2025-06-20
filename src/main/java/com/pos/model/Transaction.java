package com.pos.model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.time.LocalDateTime;

/**
 * Transaction model class
 * Save as: src/main/java/com/pos/model/Transaction.java
 */
public class Transaction {
    private final IntegerProperty id;
    private final ObservableList<CartItem> items;
    private final LocalDateTime timestamp;
    private final DoubleProperty subtotal;
    private final DoubleProperty tax;
    private final DoubleProperty total;
    private final StringProperty paymentMethod;
    private final DoubleProperty taxRate;
    
    // Constructor for new transaction
    public Transaction() {
        this.id = new SimpleIntegerProperty(0);
        this.items = FXCollections.observableArrayList();
        this.timestamp = LocalDateTime.now();
        this.subtotal = new SimpleDoubleProperty(0.0);
        this.tax = new SimpleDoubleProperty(0.0);
        this.total = new SimpleDoubleProperty(0.0);
        this.paymentMethod = new SimpleStringProperty("Cash");
        this.taxRate = new SimpleDoubleProperty(0.08); // 8% tax rate
        
        // Listen for changes in cart items and recalculate totals
        this.items.addListener((ListChangeListener<CartItem>) change -> calculateTotals());
    }
    
    // Constructor for existing transaction (from database)
    public Transaction(int id, double subtotal, double tax, double total, 
                      String paymentMethod, LocalDateTime timestamp) {
        this.id = new SimpleIntegerProperty(id);
        this.items = FXCollections.observableArrayList();
        this.timestamp = timestamp;
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.tax = new SimpleDoubleProperty(tax);
        this.total = new SimpleDoubleProperty(total);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.taxRate = new SimpleDoubleProperty(0.08);
    }
    
    // ID property methods
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }
    
    // Items management
    public ObservableList<CartItem> getItems() { 
        return items; 
    }
    
    public void addItem(CartItem item) {
        // Check if product already exists in cart
        for (CartItem existingItem : items) {
            if (existingItem.getProduct().equals(item.getProduct())) {
                // Increase quantity instead of adding new item
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // Add new item if product not found
        items.add(item);
        
        // Listen to quantity changes for recalculation
        item.quantityProperty().addListener((obs, oldVal, newVal) -> calculateTotals());
    }
    
    public void removeItem(CartItem item) {
        items.remove(item);
    }
    
    public void clearItems() {
        items.clear();
    }
    
    // Timestamp getter
    public LocalDateTime getTimestamp() { 
        return timestamp; 
    }
    
    // Subtotal property methods
    public double getSubtotal() { return subtotal.get(); }
    public DoubleProperty subtotalProperty() { return subtotal; }
    
    // Tax property methods
    public double getTax() { return tax.get(); }
    public DoubleProperty taxProperty() { return tax; }
    
    // Total property methods
    public double getTotal() { return total.get(); }
    public DoubleProperty totalProperty() { return total; }
    
    // Payment method property methods
    public String getPaymentMethod() { return paymentMethod.get(); }
    public void setPaymentMethod(String method) { paymentMethod.set(method); }
    public StringProperty paymentMethodProperty() { return paymentMethod; }
    
    // Tax rate property methods
    public double getTaxRate() { return taxRate.get(); }
    public void setTaxRate(double rate) { taxRate.set(rate); }
    public DoubleProperty taxRateProperty() { return taxRate; }
    
    // Calculate totals based on cart items
    private void calculateTotals() {
        double sub = items.stream()
                         .mapToDouble(CartItem::getTotal)
                         .sum();
        
        subtotal.set(sub);
        tax.set(sub * taxRate.get());
        total.set(sub + tax.get());
    }
    
    // Utility methods
    public int getTotalItemCount() {
        return items.stream()
                   .mapToInt(CartItem::getQuantity)
                   .sum();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void recalculateTotals() {
        calculateTotals();
    }
    
    @Override
    public String toString() {
        return String.format("Transaction{id=%d, items=%d, subtotal=%.2f, tax=%.2f, total=%.2f, payment=%s, time=%s}",
                getId(), items.size(), getSubtotal(), getTax(), getTotal(), 
                getPaymentMethod(), timestamp.toString());
    }
}