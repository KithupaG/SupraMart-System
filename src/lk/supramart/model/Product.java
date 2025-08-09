/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

import java.sql.Timestamp;

/**
 *
 * @author nimut
 */
public class Product {

    private String id, productName;
    private int productCategoryId, stock;
    private double price, cost;
    private Timestamp addedDateTime;

    public Product(Builder builder) {
        this.id = builder.id;
        this.productName = builder.productName;
        this.productCategoryId = builder.productCategoryId;
        this.stock = builder.stock;
        this.price = builder.price;
        this.cost = builder.cost;
        this.addedDateTime = builder.addedDateTime;
    }
    

    public static class Builder {
        private String id, productName;
        private int productCategoryId, stock;
        private double price, cost;
        private Timestamp addedDateTime;

        public Builder setId(String id) {
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

        public Builder setStock(int stock) {
            this.stock = stock;
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

        public Builder setAddedDateTime(Timestamp addedDateTime) {
            this.addedDateTime = addedDateTime;
            return this;
        }
        
        public Product build() {
            return new Product(this);
        }
        
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCategoryId() {
        return productCategoryId;
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

    public Timestamp getAddedDateTime() {
        return addedDateTime;
    }

    // toString method just in case 
    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", productName='" + productName + '\''
                + ", productCategoryId=" + productCategoryId
                + ", price=" + price
                + ", cost=" + cost
                + ", stock=" + stock
                + ", addedDateTime=" + addedDateTime
                + '}';
    }

}
