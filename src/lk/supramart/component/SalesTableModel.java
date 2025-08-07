package lk.supramart.component;

import lk.supramart.model.Sale;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Table model for displaying sales data
 * @author dehemi
 */
public class SalesTableModel extends AbstractTableModel {
    
    private List<Sale> sales;
    private final String[] columnNames = {
        "Sale ID", "Date", "Branch", "Employee", "Customer", 
        "Payment Method", "Total Amount"
    };
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    public SalesTableModel() {
        this.sales = new ArrayList<>();
    }
    
    public SalesTableModel(List<Sale> sales) {
        this.sales = sales;
    }
    
    @Override
    public int getRowCount() {
        return sales.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < sales.size()) {
            Sale sale = sales.get(rowIndex);
            
            switch (columnIndex) {
                case 0: return sale.getSaleId();
                case 1: return sale.getSaleDate() != null ? 
                    sale.getSaleDate().format(dateFormatter) : "";
                case 2: return sale.getBranchName() != null ? 
                    sale.getBranchName() : "Branch " + sale.getBranchId();
                case 3: return sale.getEmployeeName() != null ? 
                    sale.getEmployeeName() : sale.getEmployeeId();
                case 4: return sale.getCustomerName() != null ? 
                    sale.getCustomerName() : "Customer " + sale.getCustomerId();
                case 5: return sale.getPaymentMethod();
                case 6: return sale.getTotalAmount() != null ? 
                    "LKR " + sale.getTotalAmount().toString() : "LKR 0.00";
                default: return "";
            }
        }
        return "";
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class; // Sale ID
            case 1: return String.class;  // Date
            case 2: return String.class;  // Branch
            case 3: return String.class;  // Employee
            case 4: return String.class;  // Customer
            case 5: return String.class;  // Payment Method
            case 6: return String.class;  // Total Amount
            default: return Object.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Make table read-only
    }
    
    /**
     * Set the sales data
     */
    public void setSales(List<Sale> sales) {
        this.sales = sales;
        fireTableDataChanged();
    }
    
    /**
     * Get all sales
     */
    public List<Sale> getSales() {
        return new ArrayList<>(sales);
    }
    
    /**
     * Get sale at specific row
     */
    public Sale getSaleAt(int row) {
        if (row >= 0 && row < sales.size()) {
            return sales.get(row);
        }
        return null;
    }
    
    /**
     * Add a sale to the table
     */
    public void addSale(Sale sale) {
        sales.add(sale);
        fireTableRowsInserted(sales.size() - 1, sales.size() - 1);
    }
    
    /**
     * Remove a sale from the table
     */
    public void removeSale(int row) {
        if (row >= 0 && row < sales.size()) {
            sales.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }
    
    /**
     * Clear all sales
     */
    public void clearSales() {
        int size = sales.size();
        sales.clear();
        fireTableRowsDeleted(0, size - 1);
    }
    
    /**
     * Get total sales amount
     */
    public double getTotalSalesAmount() {
        return sales.stream()
            .mapToDouble(sale -> sale.getTotalAmount() != null ? 
                sale.getTotalAmount().doubleValue() : 0.0)
            .sum();
    }
    
    /**
     * Get sales count
     */
    public int getSalesCount() {
        return sales.size();
    }
} 