package com.pos.model;

import javafx.beans.property.*;

/**
 * Product model class
 * Save as: src/main/java/com/pos/model/Product.java
 */
public class Product {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty price;
    private final StringProperty category;
    private final StringProperty barcode;
    private final IntegerProperty stock;
    
    // Constructor for new products (without ID)
    public Product(String name, double price, String category, String barcode, int stock) {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleStringProperty(category);
        this.barcode = new SimpleStringProperty(barcode);
        this.stock = new SimpleIntegerProperty(stock);
    }
    
    // Constructor for existing products (with ID from database)
    public Product(int id, String name, double price, String category, String barcode, int stock) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.category = new SimpleStringProperty(category);
        this.barcode = new SimpleStringProperty(barcode);
        this.stock = new SimpleIntegerProperty(stock);
    }
    
    // ID property methods
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }
    
    // Name property methods
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }
    
    // Price property methods
    public double getPrice() { return price.get(); }
    public void setPrice(double price) { this.price.set(price); }
    public DoubleProperty priceProperty() { return price; }
    
    // Category property methods
    public String getCategory() { return category.get(); }
    public void setCategory(String category) { this.category.set(category); }
    public StringProperty categoryProperty() { return category; }
    
    // Barcode property methods
    public String getBarcode() { return barcode.get(); }
    public void setBarcode(String barcode) { this.barcode.set(barcode); }
    public StringProperty barcodeProperty() { return barcode; }
    
    // Stock property methods
    public int getStock() { return stock.get(); }
    public void setStock(int stock) { this.stock.set(stock); }
    public IntegerProperty stockProperty() { return stock; }
    
    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=%.2f, category='%s', barcode='%s', stock=%d}",
                getId(), getName(), getPrice(), getCategory(), getBarcode(), getStock());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return getId() == product.getId();
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}