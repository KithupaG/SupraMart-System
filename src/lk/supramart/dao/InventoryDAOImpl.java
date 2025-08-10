/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import lk.supramart.connection.MySQL;
import lk.supramart.model.Product;
import lk.supramart.model.InventoryTransaction;
import lk.supramart.model.Sale;
import lk.supramart.model.SaleItem;
import lk.supramart.util.LoggerUtil;
import java.sql.*;
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.setId(rs.getInt("product_id"));
                product.setProductName(rs.getString("name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setCategoryName(rs.getString("category_name"));
                product.setPrice(rs.getDouble("price"));
                product.setCost(rs.getDouble("cost"));
                product.setStock(rs.getInt("stock_quantity"));
                product.setReorderLevel(rs.getInt("reorder_level"));
                product.setAddedDateTime(rs.getTimestamp("added_on"));
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
                product.getProductName(), 
                product.getCategoryId(), 
                product.getPrice(), 
                product.getCost(), 
                product.getStock(), 
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
                product.getProductName(), 
                product.getCategoryId(), 
                product.getPrice(), 
                product.getCost(), 
                product.getStock(), 
                product.getReorderLevel(), 
                product.getId());
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
                LoggerUtil.Log.info(InventoryDAOImpl.class, "Successfully deleted product: " + product.getProductName() + " (ID: " + productId + ")");
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
    
    /**
     * Force delete a product by first removing all related records from dependent tables
     */
    public boolean forceDeleteProduct(int productId) {
        // First check if product exists
        Product product = getProductById(productId);
        if (product == null) {
            LoggerUtil.Log.warning(InventoryDAOImpl.class, "Product with ID " + productId + " not found for deletion");
            return false;
        }
        
        try {
            // Delete from sale_items first (this is the main constraint causing issues)
            String deleteSaleItemsQuery = "DELETE FROM supramart.sale_items WHERE product_id = ?";
            try {
                int saleItemsDeleted = MySQL.executePreparedIUD(deleteSaleItemsQuery, productId);
                if (saleItemsDeleted > 0) {
                    LoggerUtil.Log.info(InventoryDAOImpl.class, "Deleted " + saleItemsDeleted + " sale items for product " + productId);
                }
            } catch (SQLException ex) {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "Error deleting sale items for product " + productId + ": " + ex.getMessage());
                // Continue anyway
            }
            
            // Delete from inventory_transactions
            String deleteTransactionsQuery = "DELETE FROM supramart.inventory_transactions WHERE product_id = ?";
            try {
                int transactionsDeleted = MySQL.executePreparedIUD(deleteTransactionsQuery, productId);
                if (transactionsDeleted > 0) {
                    LoggerUtil.Log.info(InventoryDAOImpl.class, "Deleted " + transactionsDeleted + " inventory transactions for product " + productId);
                }
            } catch (SQLException ex) {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "Error deleting inventory transactions for product " + productId + ": " + ex.getMessage());
                // Continue anyway
            }
            
            // Delete from branches_has_products
            String deleteBranchProductsQuery = "DELETE FROM mydb.branches_has_products WHERE products_product_id = ?";
            try {
                int branchProductsDeleted = MySQL.executePreparedIUD(deleteBranchProductsQuery, productId);
                if (branchProductsDeleted > 0) {
                    LoggerUtil.Log.info(InventoryDAOImpl.class, "Deleted " + branchProductsDeleted + " branch product assignments for product " + productId);
                }
            } catch (SQLException ex) {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "Error deleting branch products for product " + productId + ": " + ex.getMessage());
                // Continue anyway
            }
            
            // Now delete the product itself
            String deleteProductQuery = "DELETE FROM supramart.products WHERE product_id = ?";
            int rows = MySQL.executePreparedIUD(deleteProductQuery, productId);
            
            if (rows > 0) {
                LoggerUtil.Log.info(InventoryDAOImpl.class, "Successfully force deleted product: " + product.getProductName() + " (ID: " + productId + ")");
                return true;
            } else {
                LoggerUtil.Log.warning(InventoryDAOImpl.class, "No rows affected when force deleting product " + productId);
                return false;
            }
            
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error force deleting product: " + ex.getMessage());
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
        String checkSalesQuery = "SELECT COUNT(*) FROM supramart.sale_items WHERE product_id = ?";
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
        String checkSalesQuery = "SELECT COUNT(*) FROM supramart.sale_items WHERE product_id = ?";
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
        if (product.getStock() > 0) {
            constraints.append("• Has stock quantity: ").append(product.getStock()).append("\n");
        }
        
        return constraints.length() > 0 ? constraints.toString() : "No constraints found";
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
        return transactions;
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
    
    // Sales Management Methods
    
    @Override
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT s.*, b.branch_name, e.first_name, e.last_name, " +
                      "c.first_name as customer_first_name, c.last_name as customer_last_name " +
                      "FROM supramart.sales s " +
                      "LEFT JOIN mydb.branches b ON s.branches_branch_id = b.branch_id " +
                      "LEFT JOIN supramart.employees e ON s.employee_id = e.employee_id " +
                      "LEFT JOIN supramart.customers c ON s.customers_customer_id = c.customer_id " +
                      "ORDER BY s.sale_date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setTotalAmount(rs.getBigDecimal("total_amount"));
                sale.setPaymentMethod(rs.getString("payment_method"));
                sale.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
                sale.setBranchId(rs.getInt("branches_branch_id"));
                sale.setBranchName(rs.getString("branch_name"));
                sale.setEmployeeId(rs.getString("employee_id"));
                
                // Combine employee first and last name
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                if (firstName != null && lastName != null) {
                    sale.setEmployeeName(firstName + " " + lastName);
                } else {
                    sale.setEmployeeName(rs.getString("employee_id"));
                }
                
                sale.setCustomerId(rs.getInt("customers_customer_id"));
                
                // Combine customer first and last name
                String customerFirstName = rs.getString("customer_first_name");
                String customerLastName = rs.getString("customer_last_name");
                if (customerFirstName != null && customerLastName != null) {
                    sale.setCustomerName(customerFirstName + " " + customerLastName);
                } else {
                    sale.setCustomerName("Customer " + rs.getInt("customers_customer_id"));
                }
                
                sales.add(sale);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching sales: " + ex.getMessage());
        }
        return sales;
    }
    
    @Override
    public List<Sale> getSalesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT s.*, b.branch_name, e.first_name, e.last_name, " +
                      "c.first_name as customer_first_name, c.last_name as customer_last_name " +
                      "FROM supramart.sales s " +
                      "LEFT JOIN mydb.branches b ON s.branches_branch_id = b.branch_id " +
                      "LEFT JOIN supramart.employees e ON s.employee_id = e.employee_id " +
                      "LEFT JOIN supramart.customers c ON s.customers_customer_id = c.customer_id " +
                      "WHERE s.sale_date BETWEEN ? AND ? " +
                      "ORDER BY s.sale_date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, startDate, endDate);
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setTotalAmount(rs.getBigDecimal("total_amount"));
                sale.setPaymentMethod(rs.getString("payment_method"));
                sale.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
                sale.setBranchId(rs.getInt("branches_branch_id"));
                sale.setBranchName(rs.getString("branch_name"));
                sale.setEmployeeId(rs.getString("employee_id"));
                
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                if (firstName != null && lastName != null) {
                    sale.setEmployeeName(firstName + " " + lastName);
                } else {
                    sale.setEmployeeName(rs.getString("employee_id"));
                }
                
                sale.setCustomerId(rs.getInt("customers_customer_id"));
                
                String customerFirstName = rs.getString("customer_first_name");
                String customerLastName = rs.getString("customer_last_name");
                if (customerFirstName != null && customerLastName != null) {
                    sale.setCustomerName(customerFirstName + " " + customerLastName);
                } else {
                    sale.setCustomerName("Customer " + rs.getInt("customers_customer_id"));
                }
                
                sales.add(sale);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching sales by date range: " + ex.getMessage());
        }
        return sales;
    }
    
    @Override
    public List<Sale> getSalesByBranch(int branchId) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT s.*, b.branch_name, e.first_name, e.last_name, " +
                      "c.first_name as customer_first_name, c.last_name as customer_last_name " +
                      "FROM supramart.sales s " +
                      "LEFT JOIN mydb.branches b ON s.branches_branch_id = b.branch_id " +
                      "LEFT JOIN supramart.employees e ON s.employee_id = e.employee_id " +
                      "LEFT JOIN supramart.customers c ON s.customers_customer_id = c.customer_id " +
                      "WHERE s.branches_branch_id = ? " +
                      "ORDER BY s.sale_date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, branchId);
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setTotalAmount(rs.getBigDecimal("total_amount"));
                sale.setPaymentMethod(rs.getString("payment_method"));
                sale.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
                sale.setBranchId(rs.getInt("branches_branch_id"));
                sale.setBranchName(rs.getString("branch_name"));
                sale.setEmployeeId(rs.getString("employee_id"));
                
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                if (firstName != null && lastName != null) {
                    sale.setEmployeeName(firstName + " " + lastName);
                } else {
                    sale.setEmployeeName(rs.getString("employee_id"));
                }
                
                sale.setCustomerId(rs.getInt("customers_customer_id"));
                
                String customerFirstName = rs.getString("customer_first_name");
                String customerLastName = rs.getString("customer_last_name");
                if (customerFirstName != null && customerLastName != null) {
                    sale.setCustomerName(customerFirstName + " " + customerLastName);
                } else {
                    sale.setCustomerName("Customer " + rs.getInt("customers_customer_id"));
                }
                
                sales.add(sale);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching sales by branch: " + ex.getMessage());
        }
        return sales;
    }
    
    @Override
    public List<Sale> getSalesByEmployee(String employeeId) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT s.*, b.branch_name, e.first_name, e.last_name, " +
                      "c.first_name as customer_first_name, c.last_name as customer_last_name " +
                      "FROM supramart.sales s " +
                      "LEFT JOIN mydb.branches b ON s.branches_branch_id = b.branch_id " +
                      "LEFT JOIN supramart.employees e ON s.employee_id = e.employee_id " +
                      "LEFT JOIN supramart.customers c ON s.customers_customer_id = c.customer_id " +
                      "WHERE s.employee_id = ? " +
                      "ORDER BY s.sale_date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, employeeId);
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setTotalAmount(rs.getBigDecimal("total_amount"));
                sale.setPaymentMethod(rs.getString("payment_method"));
                sale.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
                sale.setBranchId(rs.getInt("branches_branch_id"));
                sale.setBranchName(rs.getString("branch_name"));
                sale.setEmployeeId(rs.getString("employee_id"));
                
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                if (firstName != null && lastName != null) {
                    sale.setEmployeeName(firstName + " " + lastName);
                } else {
                    sale.setEmployeeName(rs.getString("employee_id"));
                }
                
                sale.setCustomerId(rs.getInt("customers_customer_id"));
                
                String customerFirstName = rs.getString("customer_first_name");
                String customerLastName = rs.getString("customer_last_name");
                if (customerFirstName != null && customerLastName != null) {
                    sale.setCustomerName(customerFirstName + " " + customerLastName);
                } else {
                    sale.setCustomerName("Customer " + rs.getInt("customers_customer_id"));
                }
                
                sales.add(sale);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching sales by employee: " + ex.getMessage());
        }
        return sales;
    }
    
    @Override
    public List<Sale> searchSales(String searchTerm) {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT s.*, b.branch_name, e.first_name, e.last_name, " +
                      "c.first_name as customer_first_name, c.last_name as customer_last_name " +
                      "FROM supramart.sales s " +
                      "LEFT JOIN mydb.branches b ON s.branches_branch_id = b.branch_id " +
                      "LEFT JOIN supramart.employees e ON s.employee_id = e.employee_id " +
                      "LEFT JOIN supramart.customers c ON s.customers_customer_id = c.customer_id " +
                      "WHERE s.sale_id LIKE ? OR b.branch_name LIKE ? OR e.first_name LIKE ? " +
                      "OR e.last_name LIKE ? OR s.payment_method LIKE ? " +
                      "ORDER BY s.sale_date DESC";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, 
                "%" + searchTerm + "%", "%" + searchTerm + "%", 
                "%" + searchTerm + "%", "%" + searchTerm + "%", 
                "%" + searchTerm + "%");
            while (rs.next()) {
                Sale sale = new Sale();
                sale.setSaleId(rs.getInt("sale_id"));
                sale.setTotalAmount(rs.getBigDecimal("total_amount"));
                sale.setPaymentMethod(rs.getString("payment_method"));
                sale.setSaleDate(rs.getTimestamp("sale_date").toLocalDateTime());
                sale.setBranchId(rs.getInt("branches_branch_id"));
                sale.setBranchName(rs.getString("branch_name"));
                sale.setEmployeeId(rs.getString("employee_id"));
                
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                if (firstName != null && lastName != null) {
                    sale.setEmployeeName(firstName + " " + lastName);
                } else {
                    sale.setEmployeeName(rs.getString("employee_id"));
                }
                
                sale.setCustomerId(rs.getInt("customers_customer_id"));
                
                String customerFirstName = rs.getString("customer_first_name");
                String customerLastName = rs.getString("customer_last_name");
                if (customerFirstName != null && customerLastName != null) {
                    sale.setCustomerName(customerFirstName + " " + customerLastName);
                } else {
                    sale.setCustomerName("Customer " + rs.getInt("customers_customer_id"));
                }
                
                sales.add(sale);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error searching sales: " + ex.getMessage());
        }
        return sales;
    }
    
    @Override
    public List<SaleItem> getSaleItems(int saleId) {
        List<SaleItem> saleItems = new ArrayList<>();
        String query = "SELECT si.*, p.name as product_name " +
                      "FROM supramart.sale_items si " +
                      "LEFT JOIN supramart.products p ON si.product_id = p.product_id " +
                      "WHERE si.sale_id = ? " +
                      "ORDER BY si.sale_item_id";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, saleId);
            while (rs.next()) {
                SaleItem saleItem = new SaleItem();
                saleItem.setSaleItemId(rs.getInt("sale_item_id"));
                saleItem.setSaleId(rs.getInt("sale_id"));
                saleItem.setProductId(rs.getInt("product_id"));
                saleItem.setProductName(rs.getString("product_name"));
                saleItem.setQuantity(rs.getInt("quantity"));
                saleItem.setPrice(rs.getBigDecimal("price"));
                
                saleItems.add(saleItem);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(InventoryDAOImpl.class, "Error fetching sale items: " + ex.getMessage());
        }
        return saleItems;
    }
}
