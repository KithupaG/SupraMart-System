package lk.supramart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Product model class representing the products table
 * @author daham
 */
public class Product {
    private int productId;
    private String name;
    private int categoryId;
    private String categoryName;
    private BigDecimal price;
    private BigDecimal cost;
    private int stockQuantity;
    private int reorderLevel;
    private LocalDateTime addedOn;
    
    // Default constructor
    public Product() {
    }
    
    // Full constructor
    public Product(int productId, String name, int categoryId, String categoryName, 
                   BigDecimal price, BigDecimal cost, int stockQuantity, 
                   int reorderLevel, LocalDateTime addedOn) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.price = price;
        this.cost = cost;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
        this.addedOn = addedOn;
    }
    
    // Getters and Setters
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public BigDecimal getCost() {
        return cost;
    }
    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public int getReorderLevel() {
        return reorderLevel;
    }
    
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public LocalDateTime getAddedOn() {
        return addedOn;
    }
    
    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", stockQuantity=" + stockQuantity +
                ", reorderLevel=" + reorderLevel +
                ", addedOn=" + addedOn +
                '}';
    }
} 