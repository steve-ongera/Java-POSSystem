-- POS System Database Schema
-- Save this file as: src/main/resources/database/schema.sql

-- Create database (run this separately)
-- CREATE DATABASE pos_system;

-- Products table
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50) NOT NULL,
    barcode VARCHAR(50) UNIQUE NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Transactions table
CREATE TABLE transactions (
    id SERIAL PRIMARY KEY,
    subtotal DECIMAL(10,2) NOT NULL,
    tax DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Transaction items table
CREATE TABLE transaction_items (
    id SERIAL PRIMARY KEY,
    transaction_id INTEGER REFERENCES transactions(id),
    product_id INTEGER REFERENCES products(id),
    quantity INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT NULL
);

-- Insert sample products
INSERT INTO products (name, price, category, barcode, stock) VALUES
('Coffee', 3.50, 'Beverages', '001', 50),
('Sandwich', 8.99, 'Food', '002', 25),
('Chips', 2.25, 'Snacks', '003', 100),
('Soda', 1.99, 'Beverages', '004', 75),
('Salad', 6.50, 'Food', '005', 30),
('Cookie', 1.50, 'Snacks', '006', 60),
('Tea', 2.75, 'Beverages', '007', 40),
('Pizza Slice', 4.99, 'Food', '008', 20),
('Energy Drink', 3.25, 'Beverages', '009', 35),
('Burger', 12.50, 'Food', '010', 15);