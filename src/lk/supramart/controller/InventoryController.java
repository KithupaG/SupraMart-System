/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import lk.supramart.dao.InventoryDAO;
import lk.supramart.dao.InventoryDAOImpl;
import lk.supramart.model.Product;
import lk.supramart.model.InventoryTransaction;
import lk.supramart.model.Sale;
import lk.supramart.model.SaleItem;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Controller class for inventory management operations
 * @author dehemi
 */
public class InventoryController {
    private final InventoryDAO inventoryDAO;
    
    public InventoryController() {
        this.inventoryDAO = new InventoryDAOImpl();
    }
    
    // Product Management Methods
    
    /**
     * Get all products
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return inventoryDAO.getAllProducts();
    }
    
    /**
     * Get product by ID
     * @param productId Product ID
     * @return Product object or null if not found
     */
    public Product getProductById(int productId) {
        return inventoryDAO.getProductById(productId);
    }
    
    /**
     * Search products by name
     * @param productName Product name to search
     * @return Product object or null if not found
     */
    public Product getProductByName(String productName) {
        return inventoryDAO.getProductByName(productName);
    }
    
    /**
     * Get products by category
     * @param categoryId Category ID
     * @return List of products in the category
     */
    public List<Product> getProductsByCategory(int categoryId) {
        return inventoryDAO.getProductsByCategory(categoryId);
    }
    
    /**
     * Get products by branch
     * @param branchId Branch ID
     * @return List of products available in the branch
     */
    public List<Product> getProductsByBranch(int branchId) {
        return inventoryDAO.getProductsByBranch(branchId);
    }
    
    /**
     * Get products with low stock
     * @return List of products that need reordering
     */
    public List<Product> getLowStockProducts() {
        return inventoryDAO.getLowStockProducts();
    }
    
    /**
     * Search products by term
     * @param searchTerm Search term
     * @return List of matching products
     */
    public List<Product> searchProducts(String searchTerm) {
        return inventoryDAO.searchProducts(searchTerm);
    }
    
    /**
     * Add new product
     * @param product Product to add
     * @return true if successful, false otherwise
     */
    public boolean addProduct(Product product) {
        // Validate product data
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return false;
        }
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            return false;
        }
        if (product.getCost() == null || product.getCost().doubleValue() <= 0) {
            return false;
        }
        if (product.getStockQuantity() < 0) {
            return false;
        }
        if (product.getReorderLevel() < 0) {
            return false;
        }
        
        return inventoryDAO.addProduct(product);
    }
    
    /**
     * Update existing product
     * @param product Product to update
     * @return true if successful, false otherwise
     */
    public boolean updateProduct(Product product) {
        // Validate product data
        if (product.getProductId() <= 0) {
            return false;
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return false;
        }
        if (product.getPrice() == null || product.getPrice().doubleValue() <= 0) {
            return false;
        }
        if (product.getCost() == null || product.getCost().doubleValue() <= 0) {
            return false;
        }
        if (product.getStockQuantity() < 0) {
            return false;
        }
        if (product.getReorderLevel() < 0) {
            return false;
        }
        
        return inventoryDAO.updateProduct(product);
    }
    
    /**
     * Delete product
     * @param productId Product ID to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteProduct(int productId) {
        if (productId <= 0) {
            return false;
        }
        
        // First check if product exists
        Product product = inventoryDAO.getProductById(productId);
        if (product == null) {
            return false;
        }
        
        // Check if product has stock
        if (product.getStockQuantity() > 0) {
            // You might want to warn about deleting products with stock
            // For now, we'll allow it but log it
            java.util.logging.Logger.getLogger(InventoryController.class.getName())
                .warning("Deleting product with stock: " + product.getName() + " (Stock: " + product.getStockQuantity() + ")");
        }
        
        return inventoryDAO.deleteProduct(productId);
    }
    
    /**
     * Force delete product by removing all related data first
     * @param productId Product ID to force delete
     * @return true if successful, false otherwise
     */
    public boolean forceDeleteProduct(int productId) {
        if (productId <= 0) {
            return false;
        }
        
        // First check if product exists
        Product product = inventoryDAO.getProductById(productId);
        if (product == null) {
            return false;
        }
        
        // Log the force delete operation
        java.util.logging.Logger.getLogger(InventoryController.class.getName())
            .warning("Force deleting product: " + product.getName() + " (ID: " + productId + ") - this will remove all related data");
        
        return inventoryDAO.forceDeleteProduct(productId);
    }
    
    /**
     * Check if product can be deleted (no foreign key constraints)
     * @param productId Product ID to check
     * @return true if can be deleted, false otherwise
     */
    public boolean canDeleteProduct(int productId) {
        if (productId <= 0) {
            return false;
        }
        
        try {
            return inventoryDAO.canDeleteProduct(productId);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(InventoryController.class.getName())
                .severe("Error checking if product can be deleted: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get detailed information about deletion constraints for a product
     * @param productId Product ID to check
     * @return String describing constraints or "No constraints found"
     */
    public String getDeletionConstraints(int productId) {
        if (productId <= 0) {
            return "Invalid product ID";
        }
        
        try {
            return inventoryDAO.getDeletionConstraints(productId);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(InventoryController.class.getName())
                .severe("Error getting deletion constraints: " + e.getMessage());
            return "Error checking constraints: " + e.getMessage();
        }
    }
    
    /**
     * Update stock quantity
     * @param productId Product ID
     * @param newQuantity New stock quantity
     * @return true if successful, false otherwise
     */
    public boolean updateStockQuantity(int productId, int newQuantity) {
        if (productId <= 0 || newQuantity < 0) {
            return false;
        }
        return inventoryDAO.updateStockQuantity(productId, newQuantity);
    }
    
    // Inventory Transaction Methods
    
    /**
     * Get all inventory transactions
     * @return List of all transactions
     */
    public List<InventoryTransaction> getAllTransactions() {
        return inventoryDAO.getAllTransactions();
    }
    
    /**
     * Get transactions by product
     * @param productId Product ID
     * @return List of transactions for the product
     */
    public List<InventoryTransaction> getTransactionsByProduct(int productId) {
        if (productId <= 0) {
            return null;
        }
        return inventoryDAO.getTransactionsByProduct(productId);
    }
    
    /**
     * Get transactions by branch
     * @param branchId Branch ID
     * @return List of transactions for the branch
     */
    public List<InventoryTransaction> getTransactionsByBranch(int branchId) {
        if (branchId <= 0) {
            return null;
        }
        return inventoryDAO.getTransactionsByBranch(branchId);
    }
    
    /**
     * Get transactions by date range
     * @param startDate Start date
     * @param endDate End date
     * @return List of transactions in the date range
     */
    public List<InventoryTransaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return null;
        }
        return inventoryDAO.getTransactionsByDateRange(startDate, endDate);
    }
    
    /**
     * Add inventory transaction
     * @param transaction Transaction to add
     * @return true if successful, false otherwise
     */
    public boolean addTransaction(InventoryTransaction transaction) {
        // Validate transaction data
        if (transaction.getProductId() <= 0) {
            return false;
        }
        if (transaction.getReason() == null || transaction.getReason().trim().isEmpty()) {
            return false;
        }
        if (transaction.getBranchId() <= 0) {
            return false;
        }
        if (transaction.getDate() == null) {
            transaction.setDate(LocalDateTime.now());
        }
        
        return inventoryDAO.addTransaction(transaction);
    }
    
    // Stock Management Methods
    
    /**
     * Update stock with transaction tracking
     * @param productId Product ID
     * @param quantityChange Quantity change (positive for addition, negative for reduction)
     * @param reason Reason for stock change
     * @param branchId Branch ID
     * @return true if successful, false otherwise
     */
    public boolean updateStock(int productId, int quantityChange, String reason, int branchId) {
        if (productId <= 0 || branchId <= 0) {
            return false;
        }
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        
        return inventoryDAO.updateStock(productId, quantityChange, reason, branchId);
    }
    
    /**
     * Get current stock for a product
     * @param productId Product ID
     * @param branchId Branch ID
     * @return Current stock quantity
     */
    public int getCurrentStock(int productId, int branchId) {
        if (productId <= 0 || branchId <= 0) {
            return 0;
        }
        return inventoryDAO.getCurrentStock(productId, branchId);
    }
    
    /**
     * Get products that need reordering
     * @return List of products needing reorder
     */
    public List<Product> getProductsNeedingReorder() {
        return inventoryDAO.getProductsNeedingReorder();
    }
    
    // Reporting Methods
    
    /**
     * Get stock report
     * @return List of products for stock report
     */
    public List<Product> getStockReport() {
        return inventoryDAO.getStockReport();
    }
    
    /**
     * Get transaction report
     * @return List of transactions for report
     */
    public List<InventoryTransaction> getTransactionReport() {
        return inventoryDAO.getTransactionReport();
    }
    
    // Business Logic Methods
    
    /**
     * Check if product needs reordering
     * @param product Product to check
     * @return true if needs reorder, false otherwise
     */
    public boolean needsReorder(Product product) {
        if (product == null) {
            return false;
        }
        return product.getStockQuantity() <= product.getReorderLevel();
    }
    
    /**
     * Calculate profit margin for a product
     * @param product Product to calculate margin for
     * @return Profit margin percentage
     */
    public double calculateProfitMargin(Product product) {
        if (product == null || product.getPrice() == null || product.getCost() == null) {
            return 0.0;
        }
        
        double price = product.getPrice().doubleValue();
        double cost = product.getCost().doubleValue();
        
        if (cost == 0) {
            return 0.0;
        }
        
        return ((price - cost) / cost) * 100;
    }
    
    /**
     * Calculate total inventory value
     * @param products List of products
     * @return Total inventory value
     */
    public double calculateTotalInventoryValue(List<Product> products) {
        if (products == null) {
            return 0.0;
        }
        
        double totalValue = 0.0;
        for (Product product : products) {
            if (product.getCost() != null && product.getStockQuantity() > 0) {
                totalValue += product.getCost().doubleValue() * product.getStockQuantity();
            }
        }
        
        return totalValue;
    }
    
    /**
     * Get all product categories for combo box
     * @return List of category names
     */
    public List<String> getAllCategories() {
        return inventoryDAO.getAllCategories();
    }
    
    /**
     * Get all branches for combo box
     * @return List of branch names
     */
    public List<String> getAllBranches() {
        return inventoryDAO.getAllBranches();
    }
    
    /**
     * Get all suppliers for combo box
     * @return List of supplier names
     */
    public List<String> getAllSuppliers() {
        return inventoryDAO.getAllSuppliers();
    }
    
    /**
     * Delete multiple products
     * @param productIds List of product IDs to delete
     * @return true if all products were deleted successfully, false otherwise
     */
    public boolean deleteMultipleProducts(List<Integer> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return false;
        }
        
        try {
            return inventoryDAO.deleteMultipleProducts(productIds);
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(InventoryController.class.getName())
                .severe("Error deleting multiple products: " + e.getMessage());
            return false;
        }
    }
    
    // Sales Management Methods
    
    /**
     * Get all sales
     * @return List of all sales
     */
    public List<Sale> getAllSales() {
        return inventoryDAO.getAllSales();
    }
    
    /**
     * Get sales by date range
     * @param startDate Start date
     * @param endDate End date
     * @return List of sales in the date range
     */
    public List<Sale> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return null;
        }
        return inventoryDAO.getSalesByDateRange(startDate, endDate);
    }
    
    /**
     * Get sales by branch
     * @param branchId Branch ID
     * @return List of sales for the branch
     */
    public List<Sale> getSalesByBranch(int branchId) {
        if (branchId <= 0) {
            return null;
        }
        return inventoryDAO.getSalesByBranch(branchId);
    }
    
    /**
     * Get sales by employee
     * @param employeeId Employee ID
     * @return List of sales by the employee
     */
    public List<Sale> getSalesByEmployee(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            return null;
        }
        return inventoryDAO.getSalesByEmployee(employeeId);
    }
    
    /**
     * Search sales by term
     * @param searchTerm Search term
     * @return List of matching sales
     */
    public List<Sale> searchSales(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllSales();
        }
        return inventoryDAO.searchSales(searchTerm);
    }
    
    /**
     * Get sale items for a specific sale
     * @param saleId Sale ID
     * @return List of sale items
     */
    public List<SaleItem> getSaleItems(int saleId) {
        if (saleId <= 0) {
            return null;
        }
        return inventoryDAO.getSaleItems(saleId);
    }
    
    /**
     * Calculate total sales amount
     * @param sales List of sales
     * @return Total sales amount
     */
    public double calculateTotalSalesAmount(List<Sale> sales) {
        if (sales == null) {
            return 0.0;
        }
        
        return sales.stream()
            .mapToDouble(sale -> sale.getTotalAmount() != null ? 
                sale.getTotalAmount().doubleValue() : 0.0)
            .sum();
    }
    
    /**
     * Get sales statistics
     * @return String with sales statistics
     */
    public String getSalesStatistics() {
        List<Sale> allSales = getAllSales();
        if (allSales == null || allSales.isEmpty()) {
            return "No sales data available";
        }
        
        double totalAmount = calculateTotalSalesAmount(allSales);
        int totalSales = allSales.size();
        
        // Calculate average sale amount
        double averageSale = totalSales > 0 ? totalAmount / totalSales : 0.0;
        
        return String.format(
            "Sales Statistics:\n\n" +
            "Total Sales: %d\n" +
            "Total Amount: LKR %.2f\n" +
            "Average Sale: LKR %.2f",
            totalSales, totalAmount, averageSale
        );
    }
}
