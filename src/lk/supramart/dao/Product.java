/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.sql.Timestamp;

/**
 *
 * @author nimut
 */
public class Product {

    private int id;
    private String productName;
    private int productCategoryId;
    private double price;
    private double cost;
    private int stock;
    private Timestamp addedDateTime;

    public Product(int id, String name, int categoryId, double price, double cost, int stock, Timestamp addedDateTime) {
        this.id = id;
        this.productName = productName;
        this.productCategoryId = productCategoryId;
        this.price = price;
        this.cost = cost;
        this.stock = stock;
        this.addedDateTime = addedDateTime;
    }

    // Getters
    public int getId() {
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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCategoryId(int productCategoryId) {
        this.productCategoryId = productCategoryId;
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

    public void setAddedDateTime(Timestamp addedDateTime) {
        this.addedDateTime = addedDateTime;
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

    double getCategoryId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
