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
 * @author dehemi
 */
public class InventoryController {
    private final InventoryDAO inventoryDAO;
    
    public InventoryController() {
        this.inventoryDAO = new InventoryDAOImpl();
    }
    
    // Product Management Methods

    public List<Product> getAllProducts() {
        return inventoryDAO.getAllProducts();
    }

    public Product getProductById(int productId) {
        return inventoryDAO.getProductById(productId);
    }

    public Product getProductByName(String productName) {
        return inventoryDAO.getProductByName(productName);
    }
    
    public List<Product> getProductsByCategory(int categoryId) {
        return inventoryDAO.getProductsByCategory(categoryId);
    }

    public List<Product> getProductsByBranch(int branchId) {
        return inventoryDAO.getProductsByBranch(branchId);
    }

    public List<Product> getLowStockProducts() {
        return inventoryDAO.getLowStockProducts();
    }
    

    public List<Product> searchProducts(String searchTerm) {
        return inventoryDAO.searchProducts(searchTerm);
    }

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

    public boolean updateStockQuantity(int productId, int newQuantity) {
        if (productId <= 0 || newQuantity < 0) {
            return false;
        }
        return inventoryDAO.updateStockQuantity(productId, newQuantity);
    }
    
    // Inventory Transaction Methods
    
    public List<InventoryTransaction> getAllTransactions() {
        return inventoryDAO.getAllTransactions();
    }
    

    public List<InventoryTransaction> getTransactionsByProduct(int productId) {
        if (productId <= 0) {
            return null;
        }
        return inventoryDAO.getTransactionsByProduct(productId);
    }
    
    public List<InventoryTransaction> getTransactionsByBranch(int branchId) {
        if (branchId <= 0) {
            return null;
        }
        return inventoryDAO.getTransactionsByBranch(branchId);
    }

    public List<InventoryTransaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return null;
        }
        return inventoryDAO.getTransactionsByDateRange(startDate, endDate);
    }
    
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

    public boolean updateStock(int productId, int quantityChange, String reason, int branchId) {
        if (productId <= 0 || branchId <= 0) {
            return false;
        }
        if (reason == null || reason.trim().isEmpty()) {
            return false;
        }
        
        return inventoryDAO.updateStock(productId, quantityChange, reason, branchId);
    }
    

    public int getCurrentStock(int productId, int branchId) {
        if (productId <= 0 || branchId <= 0) {
            return 0;
        }
        return inventoryDAO.getCurrentStock(productId, branchId);
    }
    
    public List<Product> getProductsNeedingReorder() {
        return inventoryDAO.getProductsNeedingReorder();
    }
    
    // Reporting Methods
    
    public List<Product> getStockReport() {
        return inventoryDAO.getStockReport();
    }
    
    public List<InventoryTransaction> getTransactionReport() {
        return inventoryDAO.getTransactionReport();
    }
    
    // Business Logic Methods
    
    public boolean needsReorder(Product product) {
        if (product == null) {
            return false;
        }
        return product.getStockQuantity() <= product.getReorderLevel();
    }
    
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

    public List<String> getAllCategories() {
        return inventoryDAO.getAllCategories();
    }
    

    public List<String> getAllBranches() {
        return inventoryDAO.getAllBranches();
    }
    

    public List<String> getAllSuppliers() {
        return inventoryDAO.getAllSuppliers();
    }
    
    // Sales Management Methods
    

    public List<Sale> getAllSales() {
        return inventoryDAO.getAllSales();
    }

    public List<Sale> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return null;
        }
        return inventoryDAO.getSalesByDateRange(startDate, endDate);
    }

    public List<Sale> getSalesByBranch(int branchId) {
        if (branchId <= 0) {
            return null;
        }
        return inventoryDAO.getSalesByBranch(branchId);
    }
    
    public List<Sale> getSalesByEmployee(String employeeId) {
        if (employeeId == null || employeeId.trim().isEmpty()) {
            return null;
        }
        return inventoryDAO.getSalesByEmployee(employeeId);
    }
    

    public List<Sale> searchSales(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return getAllSales();
        }
        return inventoryDAO.searchSales(searchTerm);
    }
    

    public List<SaleItem> getSaleItems(int saleId) {
        if (saleId <= 0) {
            return null;
        }
        return inventoryDAO.getSaleItems(saleId);
    }
    

    public double calculateTotalSalesAmount(List<Sale> sales) {
        if (sales == null) {
            return 0.0;
        }
        
        return sales.stream()
            .mapToDouble(sale -> sale.getTotalAmount() != null ? 
                sale.getTotalAmount().doubleValue() : 0.0)
            .sum();
    }
    

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
