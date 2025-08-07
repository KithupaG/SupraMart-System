/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import lk.supramart.connection.MySQL;
import lk.supramart.model.Product;
import lk.supramart.model.InventoryTransaction;
import lk.supramart.util.LoggerUtil;
import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of InventoryDAO interface
 * @author dehemi
 */
public class InventoryDAOImpl implements InventoryDAO {
    
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                products.add(product);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching products: " + ex.getMessage());
        }
        return products;
    }
    
    @Override
    public Product getProductById(int productId) {
        String query = "SELECT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id " +
                      "WHERE p.product_id = ?";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, productId);
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                return product;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching product by ID: " + ex.getMessage());
        }
        return null;
    }
    
    @Override
    public Product getProductByName(String productName) {
        String query = "SELECT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id " +
                      "WHERE p.name LIKE ?";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, "%" + productName + "%");
            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                return product;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching product by name: " + ex.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id " +
                      "WHERE p.category_id = ?";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, categoryId);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                products.add(product);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching products by category: " + ex.getMessage());
        }
        return products;
    }
    
    @Override
    public List<Product> getProductsByBranch(int branchId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT DISTINCT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id " +
                      "INNER JOIN mydb.branches_has_products bhp ON p.product_id = bhp.products_product_id " +
                      "WHERE bhp.branches_branch_id = ?";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, branchId);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                products.add(product);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching products by branch: " + ex.getMessage());
        }
        return products;
    }
    
    @Override
    public List<Product> getLowStockProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id " +
                      "WHERE p.stock_quantity <= p.reorder_level";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                products.add(product);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching low stock products: " + ex.getMessage());
        }
        return products;
    }
    
    @Override
    public List<Product> searchProducts(String searchTerm) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT p.*, pc.category_name FROM supramart.products p " +
                      "LEFT JOIN supramart.product_categories pc ON p.category_id = pc.category_id " +
                      "WHERE p.name LIKE ? OR pc.category_name LIKE ?";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, "%" + searchTerm + "%", "%" + searchTerm + "%");
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setCost(rs.getBigDecimal("cost"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedOn(rs.getTimestamp("added_on").toLocalDateTime());
                products.add(product);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error searching products: " + ex.getMessage());
        }
        return products;
    }
    
    @Override
    public boolean addProduct(Product product) {
        String query = "INSERT INTO supramart.products (name, category_id, price, cost, stock_quantity, reorder_level) " +
                      "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            int rows = MySQL.executePreparedIUD(query, 
                product.getName(), 
                product.getCategoryId(), 
                product.getPrice(), 
                product.getCost(), 
                product.getStockQuantity(), 
                product.getReorderLevel());
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error adding product: " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateProduct(Product product) {
        String query = "UPDATE supramart.products SET name = ?, category_id = ?, price = ?, " +
                      "cost = ?, stock_quantity = ?, reorder_level = ? WHERE product_id = ?";
        
        try {
            int rows = MySQL.executePreparedIUD(query, 
                product.getName(), 
                product.getCategoryId(), 
                product.getPrice(), 
                product.getCost(), 
                product.getStockQuantity(), 
                product.getReorderLevel(), 
                product.getProductId());
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error updating product: " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean deleteProduct(int productId) {
        // First check if product exists
        Product product = getProductById(productId);
        if (product == null) {
            LoggerUtil.Log.warning(InventoryDAOImpl.class, "Product with ID " + productId + " not found for deletion");
            return false;
        }
        
        // Check for foreign key constraints - check if product is referenced in inventory_transactions
        String checkTransactionsQuery = "SELECT COUNT(*) FROM supramart.inventory_transactions WHERE product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkTransactionsQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "Cannot delete product " + productId + " - has inventory transactions");
                return false;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error checking inventory transactions: " + ex.getMessage());
            return false;
        }
        
        // Check if product is referenced in branches_has_products
        String checkBranchProductsQuery = "SELECT COUNT(*) FROM mydb.branches_has_products WHERE products_product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkBranchProductsQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "Cannot delete product " + productId + " - is assigned to branches");
                return false;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error checking branch products: " + ex.getMessage());
            return false;
        }
        
        // If no constraints, proceed with deletion
        String query = "DELETE FROM supramart.products WHERE product_id = ?";
        
        try {
            int rows = MySQL.executePreparedIUD(query, productId);
            if (rows > 0) {
                LoggerUtil.Log.info(InventoryDAOImpl.class, "Successfully deleted product: " + product.getName() + " (ID: " + productId + ")");
                return true;
            } else {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "No rows affected when deleting product " + productId);
                return false;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error deleting product: " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateStockQuantity(int productId, int newQuantity) {
        String query = "UPDATE supramart.products SET stock_quantity = ? WHERE product_id = ?";
        
        try {
            int rows = MySQL.executePreparedIUD(query, newQuantity, productId);
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error updating stock quantity: " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean canDeleteProduct(int productId) {
        // First check if product exists
        Product product = getProductById(productId);
        if (product == null) {
            return false;
        }
        
        // Check for foreign key constraints - check if product is referenced in inventory_transactions
        String checkTransactionsQuery = "SELECT COUNT(*) FROM supramart.inventory_transactions WHERE product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkTransactionsQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error checking inventory transactions: " + ex.getMessage());
            return false;
        }
        
        // Check if product is referenced in branches_has_products
        String checkBranchProductsQuery = "SELECT COUNT(*) FROM mydb.branches_has_products WHERE products_product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkBranchProductsQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error checking branch products: " + ex.getMessage());
            return false;
        }
        
        // Check if product is used in sales (if sales table exists)
        String checkSalesQuery = "SELECT COUNT(*) FROM supramart.sales WHERE product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkSalesQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                return false;
            }
        } catch (SQLException ex) {
            // Sales table might not exist, so we'll ignore this error
            LoggerUtil.Log.fine(InventoryDAOImpl.class, "Sales table check skipped: " + ex.getMessage());
        }
        
        return true;
    }
    
    @Override
    public String getDeletionConstraints(int productId) {
        StringBuilder constraints = new StringBuilder();
        
        // Check if product exists
        Product product = getProductById(productId);
        if (product == null) {
            return "Product not found";
        }
        
        // Check for inventory transactions
        String checkTransactionsQuery = "SELECT COUNT(*) FROM supramart.inventory_transactions WHERE product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkTransactionsQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                constraints.append("• Has inventory transactions\n");
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error checking inventory transactions: " + ex.getMessage());
        }
        
        // Check if product is referenced in branches_has_products
        String checkBranchProductsQuery = "SELECT COUNT(*) FROM mydb.branches_has_products WHERE products_product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkBranchProductsQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                constraints.append("• Assigned to branches\n");
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error checking branch products: " + ex.getMessage());
        }
        
        // Check if product is used in sales (if sales table exists)
        String checkSalesQuery = "SELECT COUNT(*) FROM supramart.sales WHERE product_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(checkSalesQuery, productId);
            if (rs.next() && rs.getInt(1) > 0) {
                constraints.append("• Used in sales transactions\n");
            }
        } catch (SQLException ex) {
            // Sales table might not exist, so we'll ignore this error
            LoggerUtil.Log.fine(InventoryDAOImpl.class, "Sales table check skipped: " + ex.getMessage());
        }
        
        // Check if product has stock
        if (product.getStockQuantity() > 0) {
            constraints.append("• Has stock quantity: ").append(product.getStockQuantity()).append("\n");
        }
        
        return constraints.length() > 0 ? constraints.toString() : "No constraints found";
    }
    
    @Override
    public boolean deleteMultipleProducts(List<Integer> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return false;
        }
        
        boolean allDeleted = true;
        List<String> failedProducts = new ArrayList<>();
        
        for (Integer productId : productIds) {
            if (productId != null && productId > 0) {
                Product product = getProductById(productId);
                if (product != null) {
                    if (canDeleteProduct(productId)) {
                        boolean success = deleteProduct(productId);
                        if (!success) {
                            allDeleted = false;
                            failedProducts.add(product.getName() + " (ID: " + productId + ")");
                        }
                    } else {
                        allDeleted = false;
                        failedProducts.add(product.getName() + " (ID: " + productId + ") - has constraints");
                    }
                } else {
                    allDeleted = false;
                    failedProducts.add("Unknown product (ID: " + productId + ")");
                }
            }
        }
        
        if (!allDeleted && !failedProducts.isEmpty()) {
            LoggerUtil.Log.warning(InventoryDAOImpl.class, 
                "Some products could not be deleted: " + String.join(", ", failedProducts));
        }
        
        return allDeleted;
    }
    
    @Override
    public List<InventoryTransaction> getAllTransactions() {
        List<InventoryTransaction> transactions = new ArrayList<>();
        String query = "SELECT it.*, p.name as product_name, b.branch_name FROM supramart.inventory_transactions it " +
                      "LEFT JOIN supramart.products p ON it.product_id = p.product_id " +
                      "LEFT JOIN mydb.branches b ON it.branches_branch_id = b.branch_id " +
                      "ORDER BY it.date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                InventoryTransaction transaction = new InventoryTransaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setProductName(rs.getString("product_name"));
                transaction.setQuantityChange(rs.getInt("quantity_change"));
                transaction.setReason(rs.getString("reason"));
                transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
                transaction.setBranchId(rs.getInt("branches_branch_id"));
                transaction.setBranchName(rs.getString("branch_name"));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching transactions: " + ex.getMessage());
        }
        return transactions;
    }
    
    @Override
    public List<InventoryTransaction> getTransactionsByProduct(int productId) {
        List<InventoryTransaction> transactions = new ArrayList<>();
        String query = "SELECT it.*, p.name as product_name, b.branch_name FROM supramart.inventory_transactions it " +
                      "LEFT JOIN supramart.products p ON it.product_id = p.product_id " +
                      "LEFT JOIN mydb.branches b ON it.branches_branch_id = b.branch_id " +
                      "WHERE it.product_id = ? ORDER BY it.date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, productId);
            while (rs.next()) {
                InventoryTransaction transaction = new InventoryTransaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setProductName(rs.getString("product_name"));
                transaction.setQuantityChange(rs.getInt("quantity_change"));
                transaction.setReason(rs.getString("reason"));
                transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
                transaction.setBranchId(rs.getInt("branches_branch_id"));
                transaction.setBranchName(rs.getString("branch_name"));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching transactions by product: " + ex.getMessage());
        }
        
    }
    
    @Override
    public List<InventoryTransaction> getTransactionsByBranch(int branchId) {
        List<InventoryTransaction> transactions = new ArrayList<>();
        String query = "SELECT it.*, p.name as product_name, b.branch_name FROM supramart.inventory_transactions it " +
                      "LEFT JOIN supramart.products p ON it.product_id = p.product_id " +
                      "LEFT JOIN mydb.branches b ON it.branches_branch_id = b.branch_id " +
                      "WHERE it.branches_branch_id = ? ORDER BY it.date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, branchId);
            while (rs.next()) {
                InventoryTransaction transaction = new InventoryTransaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setProductName(rs.getString("product_name"));
                transaction.setQuantityChange(rs.getInt("quantity_change"));
                transaction.setReason(rs.getString("reason"));
                transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
                transaction.setBranchId(rs.getInt("branches_branch_id"));
                transaction.setBranchName(rs.getString("branch_name"));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching transactions by branch: " + ex.getMessage());
        }
        return transactions;
    }
    
    @Override
    public List<InventoryTransaction> getTransactionsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<InventoryTransaction> transactions = new ArrayList<>();
        String query = "SELECT it.*, p.name as product_name, b.branch_name FROM supramart.inventory_transactions it " +
                      "LEFT JOIN supramart.products p ON it.product_id = p.product_id " +
                      "LEFT JOIN mydb.branches b ON it.branches_branch_id = b.branch_id " +
                      "WHERE it.date BETWEEN ? AND ? ORDER BY it.date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, startDate, endDate);
            while (rs.next()) {
                InventoryTransaction transaction = new InventoryTransaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setProductName(rs.getString("product_name"));
                transaction.setQuantityChange(rs.getInt("quantity_change"));
                transaction.setReason(rs.getString("reason"));
                transaction.setDate(rs.getTimestamp("date").toLocalDateTime());
                transaction.setBranchId(rs.getInt("branches_branch_id"));
                transaction.setBranchName(rs.getString("branch_name"));
                transactions.add(transaction);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching transactions by date range: " + ex.getMessage());
        }
        return transactions;
    }
    
    @Override
    public boolean addTransaction(InventoryTransaction transaction) {
        String query = "INSERT INTO supramart.inventory_transactions (product_id, quantity_change, reason, date, branches_branch_id) " +
                      "VALUES (?, ?, ?, ?, ?)";
        
        try {
            int rows = MySQL.executePreparedIUD(query, 
                transaction.getProductId(), 
                transaction.getQuantityChange(), 
                transaction.getReason(), 
                transaction.getDate(), 
                transaction.getBranchId());
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error adding transaction: " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean updateStock(int productId, int quantityChange, String reason, int branchId) {
        // First add the transaction
        InventoryTransaction transaction = new InventoryTransaction();
        transaction.setProductId(productId);
        transaction.setQuantityChange(quantityChange);
        transaction.setReason(reason);
        transaction.setDate(LocalDateTime.now());
        transaction.setBranchId(branchId);
        
        if (!addTransaction(transaction)) {
            return false;
        }
        
        // Then update the product stock
        String query = "UPDATE supramart.products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";
        
        try {
            int rows = MySQL.executePreparedIUD(query, quantityChange, productId);
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error updating stock: " + ex.getMessage());
            return false;
        }
    }
    
    @Override
    public int getCurrentStock(int productId, int branchId) {
        String query = "SELECT stock_quantity FROM supramart.products WHERE product_id = ?";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, productId);
            if (rs.next()) {
                return rs.getInt("stock_quantity");
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error getting current stock: " + ex.getMessage());
        }
        return 0;
    }
    
    @Override
    public List<Product> getProductsNeedingReorder() {
        return getLowStockProducts();
    }
    
    @Override
    public List<Product> getStockReport() {
        return getAllProducts();
    }
    
    @Override
    public List<InventoryTransaction> getTransactionReport() {
        return getAllTransactions();
    }
    
    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String query = "SELECT category_name FROM supramart.product_categories ORDER BY category_name";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                categories.add(rs.getString("category_name"));
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching categories: " + ex.getMessage());
        }
        return categories;
    }
    
    @Override
    public List<String> getAllBranches() {
        List<String> branches = new ArrayList<>();
        String query = "SELECT branch_name FROM mydb.branches ORDER BY branch_name";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                branches.add(rs.getString("branch_name"));
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching branches: " + ex.getMessage());
        }
        return branches;
    }
    
    @Override
    public List<String> getAllSuppliers() {
        List<String> suppliers = new ArrayList<>();
        String query = "SELECT supplier_name FROM supramart.suppliers ORDER BY supplier_name";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                suppliers.add(rs.getString("supplier_name"));
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching suppliers: " + ex.getMessage());
        }
        return suppliers;
    }
}
