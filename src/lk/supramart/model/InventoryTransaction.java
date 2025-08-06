package lk.supramart.model;

import java.time.LocalDateTime;

/**
 * InventoryTransaction model class representing inventory movements
 * @author daham
 */
public class InventoryTransaction {
    private int transactionId;
    private int productId;
    private String productName; // For display purposes
    private int quantityChange;
    private String reason;
    private LocalDateTime date;
    private int branchId;
    private String branchName; // For display purposes
    
    // Default constructor
    public InventoryTransaction() {
    }
    
    // Full constructor
    public InventoryTransaction(int transactionId, int productId, String productName,
                              int quantityChange, String reason, LocalDateTime date,
                              int branchId, String branchName) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.productName = productName;
        this.quantityChange = quantityChange;
        this.reason = reason;
        this.date = date;
        this.branchId = branchId;
        this.branchName = branchName;
    }
    
    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public int getQuantityChange() {
        return quantityChange;
    }
    
    public void setQuantityChange(int quantityChange) {
        this.quantityChange = quantityChange;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public int getBranchId() {
        return branchId;
    }
    
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
    
    public String getBranchName() {
        return branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    
    @Override
    public String toString() {
        return "InventoryTransaction{" +
                "transactionId=" + transactionId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantityChange=" + quantityChange +
                ", reason='" + reason + '\'' +
                ", date=" + date +
                ", branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                '}';
    }
} 