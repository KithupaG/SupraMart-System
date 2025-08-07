/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import lk.supramart.model.Product;
import lk.supramart.model.InventoryTransaction;
import java.util.List;

/**
 * Data Access Object interface for inventory management
 * @author dehemi
 */
public interface InventoryDAO {
    
    // Product Management
    List<Product> getAllProducts();
    Product getProductById(int productId);
    Product getProductByName(String productName);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductsByBranch(int branchId);
    List<Product> getLowStockProducts();
    List<Product> searchProducts(String searchTerm);
    
    // Product CRUD Operations
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(int productId);
    boolean updateStockQuantity(int productId, int newQuantity);
    boolean canDeleteProduct(int productId);
    String getDeletionConstraints(int productId);
    boolean deleteMultipleProducts(List<Integer> productIds);
    
    // Inventory Transaction Management
    List<InventoryTransaction> getAllTransactions();
    List<InventoryTransaction> getTransactionsByProduct(int productId);
    List<InventoryTransaction> getTransactionsByBranch(int branchId);
    List<InventoryTransaction> getTransactionsByDateRange(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    boolean addTransaction(InventoryTransaction transaction);
    
    // Stock Management
    boolean updateStock(int productId, int quantityChange, String reason, int branchId);
    int getCurrentStock(int productId, int branchId);
    List<Product> getProductsNeedingReorder();
    
    // Reporting
    List<Product> getStockReport();
    List<InventoryTransaction> getTransactionReport();
    
    // Combo Box Data Methods
    List<String> getAllCategories();
    List<String> getAllBranches();
    List<String> getAllSuppliers();
}
