package lk.supramart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Sale model class representing the sales table
 * @author dehemi
 */
public class Sale {
    private int saleId;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private LocalDateTime saleDate;
    private int branchId;
    private String branchName;
    private String employeeId;
    private String employeeName;
    private int customerId;
    private String customerName;
    
    // Default constructor
    public Sale() {
    }
    
    // Full constructor
    public Sale(int saleId, BigDecimal totalAmount, String paymentMethod, 
                LocalDateTime saleDate, int branchId, String branchName,
                String employeeId, String employeeName, int customerId, String customerName) {
        this.saleId = saleId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.saleDate = saleDate;
        this.branchId = branchId;
        this.branchName = branchName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.customerId = customerId;
        this.customerName = customerName;
    }
    
    // Getters and Setters
    public int getSaleId() {
        return saleId;
    }
    
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
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
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", totalAmount=" + totalAmount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", saleDate=" + saleDate +
                ", branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
} 