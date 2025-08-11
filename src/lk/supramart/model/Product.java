package lk.supramart.model;

import java.sql.Timestamp;

public class Product {

    private int id;
    private String productName;
    private int productCategoryId;
    private String categoryName;
    private double price;
    private double cost;
    private int stock;
    private int reorderLevel;
    private Timestamp addedDateTime;

    // Full constructor
    public Product(int id, String productName, int productCategoryId, String categoryName,
                   double price, double cost, int stock, int reorderLevel, Timestamp addedDateTime) {
        this.id = id;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.categoryName = categoryName;
        this.price = price;
        this.cost = cost;
        this.stock = stock;
        this.reorderLevel = reorderLevel;
        this.addedDateTime = addedDateTime;
    }

    // Builder-based constructor
    public Product(Builder builder) {
        this.id = builder.id;
        this.productName = builder.productName;
        this.productCategoryId = builder.productCategoryId;
        this.categoryName = builder.categoryName;
        this.price = builder.price;
        this.cost = builder.cost;
        this.stock = builder.stock;
        this.reorderLevel = builder.reorderLevel;
        this.addedDateTime = builder.addedDateTime;
    }
    
    public Product (){
    
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getCategoryId() {
        return productCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getPrice() {
        return price;
    }

    public double getCost() {
        return cost;
    }

    public int getStock() {
        return stock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public Timestamp getAddedDateTime() {
        return addedDateTime;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public void setAddedDateTime(Timestamp addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    // toString
    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", productName='" + productName + '\''
                + ", productCategoryId=" + productCategoryId
                + ", categoryName='" + categoryName + '\''
                + ", price=" + price
                + ", cost=" + cost
                + ", stock=" + stock
                + ", reorderLevel=" + reorderLevel
                + ", addedDateTime=" + addedDateTime
                + '}';
    }

    // Builder class
    public static class Builder {

        private int id;
        private String productName;
        private int productCategoryId;
        private String categoryName;
        private double price;
        private double cost;
        private int stock;
        private int reorderLevel;
        private Timestamp addedDateTime;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductCategoryId(int productCategoryId) {
            this.productCategoryId = productCategoryId;
            return this;
        }

        public Builder setCategoryName(String categoryName) {
            this.categoryName = categoryName;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setReorderLevel(int reorderLevel) {
            this.reorderLevel = reorderLevel;
            return this;
        }

        public Builder setAddedDateTime(Timestamp addedDateTime) {
            this.addedDateTime = addedDateTime;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
