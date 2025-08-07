package lk.supramart.model;

import java.math.BigDecimal;

/**
 * SaleItem model class representing the sale_items table
 * @author dehemi
 */
public class SaleItem {
    private int saleItemId;
    private int saleId;
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private BigDecimal totalPrice;
    
    // Default constructor
    public SaleItem() {
    }
    
    // Full constructor
    public SaleItem(int saleItemId, int saleId, int productId, String productName, 
                   int quantity, BigDecimal price) {
        this.saleItemId = saleItemId;
        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price.multiply(BigDecimal.valueOf(quantity));
    }
    
    // Getters and Setters
    public int getSaleItemId() {
        return saleItemId;
    }
    
    public void setSaleItemId(int saleItemId) {
        this.saleItemId = saleItemId;
    }
    
    public int getSaleId() {
        return saleId;
    }
    
    public void setSaleId(int saleId) {
        this.saleId = saleId;
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
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        // Recalculate total price when quantity changes
        if (this.price != null) {
            this.totalPrice = this.price.multiply(BigDecimal.valueOf(quantity));
        }
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
        // Recalculate total price when price changes
        if (this.quantity > 0) {
            this.totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        }
    }
    
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    @Override
    public String toString() {
        return "SaleItem{" +
                "saleItemId=" + saleItemId +
                ", saleId=" + saleId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
} 