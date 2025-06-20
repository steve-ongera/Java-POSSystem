package com.pos.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utility class for currency formatting
 * Save as: src/main/java/com/pos/util/CurrencyFormatter.java
 */
public class CurrencyFormatter {
    
    private static final DecimalFormat CURRENCY_FORMAT = new DecimalFormat("$#,##0.00");
    private static final DecimalFormat SIMPLE_FORMAT = new DecimalFormat("#,##0.00");
    private static final NumberFormat PERCENTAGE_FORMAT = NumberFormat.getPercentInstance(Locale.US);
    
    static {
        PERCENTAGE_FORMAT.setMinimumFractionDigits(1);
        PERCENTAGE_FORMAT.setMaximumFractionDigits(2);
    }
    
    /**
     * Format amount as currency with dollar sign
     * @param amount Amount to format
     * @return Formatted currency string (e.g., "$12.50")
     */
    public static String formatCurrency(double amount) {
        return CURRENCY_FORMAT.format(amount);
    }
    
    /**
     * Format amount as currency without dollar sign
     * @param amount Amount to format
     * @return Formatted number string (e.g., "12.50")
     */
    public static String formatAmount(double amount) {
        return SIMPLE_FORMAT.format(amount);
    }
    
    /**
     * Format percentage value
     * @param rate Rate as decimal (e.g., 0.08 for 8%)
     * @return Formatted percentage string (e.g., "8.0%")
     */
    public static String formatPercentage(double rate) {
        return PERCENTAGE_FORMAT.format(rate);
    }
    
    /**
     * Parse currency string to double
     * @param currencyString String like "$12.50" or "12.50"
     * @return Parsed double value
     * @throws NumberFormatException if string cannot be parsed
     */
    public static double parseCurrency(String currencyString) throws NumberFormatException {
        if (currencyString == null || currencyString.trim().isEmpty()) {
            return 0.0;
        }
        
        // Remove currency symbols and whitespace
        String cleanString = currencyString.trim()
                                         .replace("$", "")
                                         .replace(",", "");
        
        return Double.parseDouble(cleanString);
    }
    
    /**
     * Validate if string is a valid currency format
     * @param currencyString String to validate
     * @return true if valid currency format, false otherwise
     */
    public static boolean isValidCurrency(String currencyString) {
        try {
            parseCurrency(currencyString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Round amount to 2 decimal places
     * @param amount Amount to round
     * @return Rounded amount
     */
    public static double roundCurrency(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
    
    /**
     * Calculate percentage of amount
     * @param amount Base amount
     * @param percentage Percentage as decimal (e.g., 0.08 for 8%)
     * @return Calculated percentage amount
     */
    public static double calculatePercentage(double amount, double percentage) {
        return roundCurrency(amount * percentage);
    }
    
    /**
     * Format currency for receipt printing (fixed width)
     * @param amount Amount to format
     * @param width Total width of the field
     * @return Right-aligned formatted currency string
     */
    public static String formatReceiptCurrency(double amount, int width) {
        String formatted = formatCurrency(amount);
        return String.format("%" + width + "s", formatted);
    }
    
    /**
     * Format item line for receipt
     * @param itemName Name of the item
     * @param amount Amount
     * @param nameWidth Width for item name
     * @param amountWidth Width for amount
     * @return Formatted line for receipt
     */
    public static String formatReceiptLine(String itemName, double amount, 
                                         int nameWidth, int amountWidth) {
        String truncatedName = itemName.length() > nameWidth ? 
                              itemName.substring(0, nameWidth - 3) + "..." : itemName;
        
        String formattedAmount = formatCurrency(amount);
        
        return String.format("%-" + nameWidth + "s%" + amountWidth + "s", 
                           truncatedName, formattedAmount);
    }
}