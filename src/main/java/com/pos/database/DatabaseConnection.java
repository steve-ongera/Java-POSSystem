package com.pos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Database connection utility class
 * Save as: src/main/java/com/pos/database/DatabaseConnection.java
 */
public class DatabaseConnection {
    
    // Database configuration - UPDATE THESE WITH YOUR POSTGRESQL SETTINGS
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/pos_system";
    private static final String DB_USERNAME = "postgres";  // Change to your PostgreSQL username
    private static final String DB_PASSWORD = "password";  // Change to your PostgreSQL password
    
    private static Connection connection = null;
    
    // Private constructor to prevent instantiation
    private DatabaseConnection() {}
    
    /**
     * Get database connection
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load PostgreSQL JDBC driver
                Class.forName("org.postgresql.Driver");
                
                // Create connection
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("Database connected successfully!");
            }
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            System.err.println("Make sure PostgreSQL is running and database 'pos_system' exists");
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Close database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection!");
            e.printStackTrace();
        }
    }
    
    /**
     * Test database connection
     * @return true if connection successful, false otherwise
     */
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}