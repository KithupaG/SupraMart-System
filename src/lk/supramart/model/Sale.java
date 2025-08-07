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
    
   