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
    private String id, productName;
    private int productCategoryId, stock;
    private double price;
    private double cost;
    private Timestamp addedDateTime;

    
    
    public static class Builder{
        
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
