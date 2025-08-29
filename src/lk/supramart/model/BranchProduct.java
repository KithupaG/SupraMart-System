/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class BranchProduct {

    private String branchName;
    private int productId;
    private String productName;
    private int availableStock;
    private String supplierName;

    // Constructor
    public BranchProduct(String branchName, int productId, String productName, int availableStock, String supplierName) {
        this.branchName = branchName;
        this.productId = productId;
        this.productName = productName;
        this.availableStock = availableStock;
        this.supplierName = supplierName;
    }

    // Getters
    public String getBranchName() {
        return branchName;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public String getSupplierName() {
        return supplierName;
    }
}
