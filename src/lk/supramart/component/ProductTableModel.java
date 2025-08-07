package lk.supramart.component;

import lk.supramart.model.Product;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 * Custom table model for displaying products in JTable
 * @author dehemi
 */
public class ProductTableModel extends AbstractTableModel {
    
    private List<Product> products;
    private final String[] columnNames = {
        "Product ID", "Name", "Category", "Price (LKR)", "Cost (LKR)", 
        "Stock Quantity", "Reorder Level", "Added On"
    };
    
    public ProductTableModel() {
        this.products = new ArrayList<>();
    }
    
    public ProductTableModel(List<Product> products) {
        this.products = products != null ? products : new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return products.size();
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
        if (rowIndex >= 0 && rowIndex < products.size()) {
            Product product = products.get(rowIndex);
            
            switch (columnIndex) {
                case 0: return product.getProductId();
                case 1: return product.getName();
                case 2: return product.getCategoryName();
                case 3: return product.getPrice() != null ? String.format("%,.2f", product.getPrice()) : "0.00";
                case 4: return product.getCost() != null ? String.format("%,.2f", product.getCost()) : "0.00";
                case 5: return product.getStockQuantity();
                case 6: return product.getReorderLevel();
                case 7: 
                    if (product.getAddedOn() != null) {
                        String dateStr = product.getAddedOn().toString();
                        return dateStr.length() > 19 ? dateStr.substring(0, 19) : dateStr;
                    }
                    return "";
                default: return null;
            }
        }
        return null;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Make only certain columns editable
        return columnIndex == 5 || columnIndex == 6; // Stock Quantity and Reorder Level
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (rowIndex >= 0 && rowIndex < products.size()) {
            Product product = products.get(rowIndex);
            
            switch (columnIndex) {
                case 5: // Stock Quantity
                    try {
                        int newQuantity = Integer.parseInt(value.toString());
                        if (newQuantity >= 0) {
                            product.setStockQuantity(newQuantity);
                        }
                    } catch (NumberFormatException e) {
                        // Invalid input, ignore
                    }
                    break;
                case 6: // Reorder Level
                    try {
                        int newLevel = Integer.parseInt(value.toString());
                        if (newLevel >= 0) {
                            product.setReorderLevel(newLevel);
                        }
                    } catch (NumberFormatException e) {
                        // Invalid input, ignore
                    }
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
    
    /**
     * Get product at specific row
     * @param rowIndex Row index
     * @return Product at the row or null if invalid index
     */
    public Product getProductAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < products.size()) {
            return products.get(rowIndex);
        }
        return null;
    }
    
    /**
     * Set the products list
     * @param products New products list
     */
    public void setProducts(List<Product> products) {
        this.products = products != null ? products : new ArrayList<>();
        fireTableDataChanged();
    }
    
    /**
     * Add a product to the table
     * @param product Product to add
     */
    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
            fireTableRowsInserted(products.size() - 1, products.size() - 1);
        }
    }
    
    /**
     * Remove a product from the table
     * @param rowIndex Row index to remove
     */
    public void removeProduct(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < products.size()) {
            products.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }
    
    /**
     * Clear all products from the table
     */
    public void clearProducts() {
        int oldSize = products.size();
        products.clear();
        if (oldSize > 0) {
            fireTableRowsDeleted(0, oldSize - 1);
        }
    }
    
    /**
     * Get all products in the table
     * @return List of products
     */
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
    
    /**
     * Find product by ID
     * @param productId Product ID to search for
     * @return Product if found, null otherwise
     */
    public Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * Find products by name (partial match)
     * @param name Product name to search for
     * @return List of matching products
     */
    public List<Product> findProductsByName(String name) {
        List<Product> matches = new ArrayList<>();
        String searchName = name.toLowerCase();
        
        for (Product product : products) {
            if (product.getName() != null && 
                product.getName().toLowerCase().contains(searchName)) {
                matches.add(product);
            }
        }
        
        return matches;
    }
    
    /**
     * Get products with low stock
     * @return List of products with stock <= reorder level
     */
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        
        for (Product product : products) {
            if (product.getStockQuantity() <= product.getReorderLevel()) {
                lowStockProducts.add(product);
            }
        }
        
        return lowStockProducts;
    }
} 
