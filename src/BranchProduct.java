/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kithu
 */
public class BranchProduct {

    private final String branchId;
    private final String branchName;
    private final String productId;
    private final String productName;
    private final String supplierId;
    private final String supplierName;
    private final int availableStock;

    public BranchProduct(String branchId, String branchName,
            String productId, String productName,
            String supplierId, String supplierName,
            int availableStock) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.availableStock = availableStock;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
