/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lk.supramart.gui.inventoryManager;

import lk.supramart.gui.branchManager.*;
import lk.supramart.gui.admin.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.JOptionPane;
import lk.supramart.gui.Home;
import lk.supramart.gui.addProduct;
import lk.supramart.gui.editProduct;
import lk.supramart.controller.InventoryController;
import lk.supramart.component.ProductTableModel;
import lk.supramart.model.Product;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.supramart.connection.MySQL;

/**
 *
 * @author dehemi
 */
public class inventoryManagerDashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(inventoryManagerDashboard.class.getName());
    private final InventoryController inventoryController;
    private ProductTableModel productTableModel;

    /**
     * Creates new form adminDashboard
     */
    public inventoryManagerDashboard() {
        initComponents();
        inventoryController = new InventoryController();
        initializeTable();
        loadProducts();
        loadComboBoxes();
        setupKeyListeners();
    }
    
    /**
     * Setup key listeners for better user experience
     */
    private void setupKeyListeners() {
        // Add Enter key handling for search fields
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    searchProductsFromTextField();
                }
            }
        });
        
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    jButton9ActionPerformed(null);
                }
            }
        });
        
        // Add F5 key for refresh
        getRootPane().registerKeyboardAction(
            evt -> refreshProductTable(),
            "Refresh",
            javax.swing.KeyStroke.getKeyStroke("F5"),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        // Add Ctrl+F for search focus
        getRootPane().registerKeyboardAction(
            evt -> jTextField1.requestFocusInWindow(),
            "Search",
            javax.swing.KeyStroke.getKeyStroke("control F"),
            javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW
        );
        
        logger.info("Key listeners setup completed");
    }
    
    /**
     * Load all combo boxes with data from database
     */
    private void loadComboBoxes() {
        try {
            // Load categories
            List<String> categories = inventoryController.getAllCategories();
            jComboBox3.removeAllItems();
            jComboBox3.addItem("Select Product Category");
            for (String category : categories) {
                jComboBox3.addItem(category);
            }
            
            // Load suppliers
            List<String> suppliers = inventoryController.getAllSuppliers();
            jComboBox5.removeAllItems();
            jComboBox5.addItem("Select Product Supplier");
            for (String supplier : suppliers) {
                jComboBox5.addItem(supplier);
            }
            
            // Load branches
            List<String> branches = inventoryController.getAllBranches();
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Select Branch");
            for (String branch : branches) {
                jComboBox1.addItem(branch);
            }
            
            // Load brands (for now, we'll use a placeholder since there's no brand table)
            jComboBox4.removeAllItems();
            jComboBox4.addItem("Select Product Brand");
            jComboBox4.addItem("Generic");
            jComboBox4.addItem("Premium");
            jComboBox4.addItem("Budget");
            
            logger.info("Combo boxes loaded successfully");
        } catch (Exception e) {
            logger.severe("Error loading combo boxes: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error loading combo box data: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Initialize the product table
     */
    private void initializeTable() {
        productTableModel = new ProductTableModel();
        jTable2.setModel(productTableModel);
        
        // Set column widths
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(80);  // Product ID
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(150); // Category
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(100); // Price
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(100); // Cost
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(100); // Stock Quantity
        jTable2.getColumnModel().getColumn(6).setPreferredWidth(100); // Reorder Level
        jTable2.getColumnModel().getColumn(7).setPreferredWidth(150); // Added On
        
        // Add keyboard shortcuts
        jTable2.getInputMap().put(javax.swing.KeyStroke.getKeyStroke("DELETE"), "deleteProduct");
        jTable2.getActionMap().put("deleteProduct", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                deleteSelectedProduct();
            }
        });
        
        // Add double-click to edit
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editSelectedProduct();
                }
            }
            
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showContextMenu(e);
                }
            }
            
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showContextMenu(e);
                }
            }
        });
    }
    
    /**
     * Show context menu for product table
     */
    private void showContextMenu(java.awt.event.MouseEvent e) {
        int row = jTable2.rowAtPoint(e.getPoint());
        if (row >= 0) {
            jTable2.setRowSelectionInterval(row, row);
            
            javax.swing.JPopupMenu popup = new javax.swing.JPopupMenu();
            
            javax.swing.JMenuItem viewDetails = new javax.swing.JMenuItem("View Details");
            viewDetails.addActionListener(evt -> showProductDetails());
            popup.add(viewDetails);
            
            javax.swing.JMenuItem editItem = new javax.swing.JMenuItem("Edit Product");
            editItem.addActionListener(evt -> editSelectedProduct());
            popup.add(editItem);
            
            popup.addSeparator();
            
            javax.swing.JMenuItem deleteItem = new javax.swing.JMenuItem("Delete Product");
            deleteItem.addActionListener(evt -> deleteSelectedProduct());
            popup.add(deleteItem);
            
            popup.show(jTable2, e.getX(), e.getY());
        }
    }
    
    /**
     * Load all products into the table
     */
    private void loadProducts() {
        try {
            List<Product> products = inventoryController.getAllProducts();
            productTableModel.setProducts(products);
            logger.info("Loaded " + products.size() + " products");
        } catch (Exception e) {
            logger.severe("Error loading products: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error loading products: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Search products by term
     */
    private void searchProducts(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            loadProducts(); // Load all products if search is empty
            return;
        }
        
        try {
            List<Product> products = inventoryController.searchProducts(searchTerm.trim());
            productTableModel.setProducts(products);
            logger.info("Found " + products.size() + " products matching '" + searchTerm + "'");
        } catch (Exception e) {
            logger.severe("Error searching products: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error searching products: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Search products from text field
     */
    private void searchProductsFromTextField() {
        String searchTerm = jTextField1.getText();
        searchProducts(searchTerm);
    }
    
    /**
     * Delete selected product
     */
    private void deleteSelectedProduct() {
        int[] selectedRows = jTable2.getSelectedRows();
        
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, 
                "Please select at least one product to delete", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (selectedRows.length == 1) {
            deleteSingleProduct(selectedRows[0]);
        } else {
            deleteMultipleProducts(selectedRows);
        }
    }
    
    /**
     * Delete a single product
     */
    private void deleteSingleProduct(int selectedRow) {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a product to delete", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Product product = productTableModel.getProductAt(selectedRow);
        if (product == null) {
            JOptionPane.showMessageDialog(this, 
                "Invalid product selection", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if product can be deleted
        if (!inventoryController.canDeleteProduct(product.getProductId())) {
            String constraints = inventoryController.getDeletionConstraints(product.getProductId());
            JOptionPane.showMessageDialog(this, 
                "Cannot delete product '" + product.getName() + "'.\n\n" +
                "Constraints found:\n" + constraints + "\n" +
                "Please remove all dependencies before deleting.",
                "Cannot Delete Product", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Show detailed confirmation dialog
        String message = "Are you sure you want to delete this product?\n\n" +
                        "Product Details:\n" +
                        "• Name: " + product.getName() + "\n" +
                        "• ID: " + product.getProductId() + "\n" +
                        "• Category: " + product.getCategoryName() + "\n" +
                        "• Current Stock: " + product.getStockQuantity() + "\n" +
                        "• Price: LKR " + String.format("%,.2f", product.getPrice()) + "\n\n" +
                        "This action cannot be undone!";
        
        int confirm = JOptionPane.showConfirmDialog(this,
            message,
            "Confirm Delete Product", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Show progress dialog for better UX
                javax.swing.SwingWorker<Boolean, Void> worker = new javax.swing.SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        return inventoryController.deleteProduct(product.getProductId());
                    }
                    
                    @Override
                    protected void done() {
                        try {
                            boolean success = get();
                            if (success) {
                                productTableModel.removeProduct(selectedRow);
                                JOptionPane.showMessageDialog(inventoryManagerDashboard.this, 
                                    "Product '" + product.getName() + "' deleted successfully", 
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                                logger.info("Deleted product: " + product.getName() + " (ID: " + product.getProductId() + ")");
                                
                                // Refresh the table to ensure consistency
                                refreshProductTable();
                            } else {
                                JOptionPane.showMessageDialog(inventoryManagerDashboard.this, 
                                    "Failed to delete product '" + product.getName() + "'.\n\n" +
                                    "The product may have dependencies that prevent deletion.",
                                    "Delete Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            logger.severe("Error during product deletion: " + e.getMessage());
                            JOptionPane.showMessageDialog(inventoryManagerDashboard.this, 
                                "Error deleting product: " + e.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                };
                
                worker.execute();
                
            } catch (Exception e) {
                logger.severe("Error initiating product deletion: " + e.getMessage());
                JOptionPane.showMessageDialog(this, 
                    "Error initiating product deletion: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Delete multiple products
     */
    private void deleteMultipleProducts(int[] selectedRows) {
        List<Product> productsToDelete = new ArrayList<>();
        List<String> productsWithConstraints = new ArrayList<>();
        
        // Collect products and check constraints
        for (int row : selectedRows) {
            Product product = productTableModel.getProductAt(row);
            if (product != null) {
                if (inventoryController.canDeleteProduct(product.getProductId())) {
                    productsToDelete.add(product);
                } else {
                    productsWithConstraints.add(product.getName() + " (ID: " + product.getProductId() + ")");
                }
            }
        }
        
        // Show warning if some products have constraints
        if (!productsWithConstraints.isEmpty()) {
            String constraintMessage = "The following products cannot be deleted due to constraints:\n\n" +
                                     String.join("\n", productsWithConstraints) + "\n\n" +
                                     "These products will be skipped.";
            JOptionPane.showMessageDialog(this, constraintMessage, 
                "Products with Constraints", JOptionPane.WARNING_MESSAGE);
        }
        
        if (productsToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No products can be deleted. All selected products have constraints.", 
                "No Deletable Products", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Show confirmation dialog
        StringBuilder message = new StringBuilder();
        message.append("Are you sure you want to delete the following ").append(productsToDelete.size()).append(" products?\n\n");
        
        for (Product product : productsToDelete) {
            message.append("• ").append(product.getName()).append(" (ID: ").append(product.getProductId()).append(")\n");
        }
        
        message.append("\nThis action cannot be undone!");
        
        int confirm = JOptionPane.showConfirmDialog(this,
            message.toString(),
            "Confirm Bulk Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Collect product IDs
                List<Integer> productIds = new ArrayList<>();
                for (Product product : productsToDelete) {
                    productIds.add(product.getProductId());
                }
                
                // Show progress dialog for better UX
                javax.swing.SwingWorker<Boolean, Void> worker = new javax.swing.SwingWorker<Boolean, Void>() {
                    @Override
                    protected Boolean doInBackground() throws Exception {
                        return inventoryController.deleteMultipleProducts(productIds);
                    }
                    
                    @Override
                    protected void done() {
                        try {
                            boolean success = get();
                            if (success) {
                                // Remove products from table (in reverse order to maintain indices)
                                for (int i = selectedRows.length - 1; i >= 0; i--) {
                                    Product product = productTableModel.getProductAt(selectedRows[i]);
                                    if (product != null && productsToDelete.contains(product)) {
                                        productTableModel.removeProduct(selectedRows[i]);
                                    }
                                }
                                
                                JOptionPane.showMessageDialog(inventoryManagerDashboard.this, 
                                    "Successfully deleted " + productsToDelete.size() + " products", 
                                    "Bulk Delete Success", JOptionPane.INFORMATION_MESSAGE);
                                logger.info("Bulk deleted " + productsToDelete.size() + " products");
                                
                                // Refresh the table to ensure consistency
                                refreshProductTable();
                            } else {
                                JOptionPane.showMessageDialog(inventoryManagerDashboard.this, 
                                    "Failed to delete some products. Please check the logs for details.", 
                                    "Bulk Delete Failed", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            logger.severe("Error during bulk product deletion: " + e.getMessage());
                            JOptionPane.showMessageDialog(inventoryManagerDashboard.this, 
                                "Error during bulk deletion: " + e.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                };
                
                worker.execute();
                
            } catch (Exception e) {
                logger.severe("Error initiating bulk product deletion: " + e.getMessage());
                JOptionPane.showMessageDialog(this, 
                    "Error initiating bulk deletion: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /**
     * Refresh the product table
     */
    private void refreshProductTable() {
        loadProducts();
        logger.info("Product table refreshed");
    }
    
    /**
     * Show low stock products
     */
    private void showLowStockProducts() {
        try {
            List<Product> lowStockProducts = inventoryController.getLowStockProducts();
            productTableModel.setProducts(lowStockProducts);
            logger.info("Showing " + lowStockProducts.size() + " low stock products");
            JOptionPane.showMessageDialog(this, 
                "Showing " + lowStockProducts.size() + " products with low stock", 
                "Low Stock Alert", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            logger.severe("Error loading low stock products: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error loading low stock products: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Export stock report
     */
    private void exportStockReport() {
        try {
            List<Product> products = inventoryController.getAllProducts();
            // TODO: Implement actual export functionality
            JOptionPane.showMessageDialog(this, 
                "Stock report export functionality will be implemented here.\n" +
                "Total products: " + products.size(), 
                "Export Stock Report", JOptionPane.INFORMATION_MESSAGE);
            logger.info("Stock report export requested");
        } catch (Exception e) {
            logger.severe("Error exporting stock report: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error exporting stock report: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Export supplier report
     */
    private void exportSupplierReport() {
        try {
            List<String> suppliers = inventoryController.getAllSuppliers();
            // TODO: Implement actual export functionality
            JOptionPane.showMessageDialog(this, 
                "Supplier report export functionality will be implemented here.\n" +
                "Total suppliers: " + suppliers.size(), 
                "Export Supplier Report", JOptionPane.INFORMATION_MESSAGE);
            logger.info("Supplier report export requested");
        } catch (Exception e) {
            logger.severe("Error exporting supplier report: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error exporting supplier report: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Export sales report
     */
    private void exportSalesReport() {
        try {
            // TODO: Implement sales report functionality
            JOptionPane.showMessageDialog(this, 
                "Sales report export functionality will be implemented here.", 
                "Export Sales Report", JOptionPane.INFORMATION_MESSAGE);
            logger.info("Sales report export requested");
        } catch (Exception e) {
            logger.severe("Error exporting sales report: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error exporting sales report: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton23 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        jButton25 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jButton24 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabel1.setText("Search Product:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Product Branch" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton4.setBackground(new java.awt.Color(0, 112, 235));
        jButton4.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Add New Product");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 51, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Edit Product");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 51, 51));
        jButton6.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Delete Product");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Search Product");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Product Category", "Item 1", "Item 2" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Product Brand", "Item 1", "Item 2" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Product Supplier", "Item 1", "Item 2" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dashboard", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabel7.setText("Search in Sales Table:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jLabel8.setText("Sales Table");

        jButton8.setBackground(new java.awt.Color(0, 102, 255));
        jButton8.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Export Expenses Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(0, 112, 235));
        jButton9.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Search");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sales Tab", jPanel3);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTable11);

        jButton23.setBackground(new java.awt.Color(0, 102, 255));
        jButton23.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setText("Export Stock Report");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton23))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23))
        );

        jTabbedPane2.addTab("Export Stock Information", jPanel14);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(jTable13);

        jButton25.setBackground(new java.awt.Color(0, 102, 255));
        jButton25.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 255, 255));
        jButton25.setText("Export Supplier Report");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton25))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25))
        );

        jTabbedPane2.addTab("Export Supplier Information", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(jTable12);

        jButton24.setBackground(new java.awt.Color(0, 102, 255));
        jButton24.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setText("Export Sales Report");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 1337, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24))
        );

        jTabbedPane2.addTab("Export Sales Information", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reports", jPanel6);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton3.setText("Settings");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI Variable", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("X");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inventoryManagerSettings inventorymanagersetting = new inventoryManagerSettings(this, true);
        inventorymanagersetting.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();

            Home home = new Home();
            home.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selectedBranch = (String) jComboBox1.getSelectedItem();
        if (selectedBranch != null && !selectedBranch.equals("Select Branch")) {
            // Implement branch filtering
            logger.info("Selected branch: " + selectedBranch);
            // TODO: Implement branch-specific product filtering
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        addProduct addproduct = new addProduct(addProduct.addProduct, true);
        addproduct.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        editSelectedProduct();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        deleteSelectedProduct();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String selectedCategory = (String) jComboBox3.getSelectedItem();
        if (selectedCategory != null && !selectedCategory.equals("Select Product Category")) {
            // Implement category filtering
            logger.info("Selected category: " + selectedCategory);
            filterProductsByCategory(selectedCategory);
        } else {
            loadProducts(); // Load all products if "Select Product Category" is chosen
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        String searchTerm = jTextField2.getText();
        searchProducts(searchTerm);
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        String selectedBrand = (String) jComboBox4.getSelectedItem();
        if (selectedBrand != null && !selectedBrand.equals("Select Product Brand")) {
            // Implement brand filtering
            logger.info("Selected brand: " + selectedBrand);
            // TODO: Implement brand filtering when brand table is available
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        String selectedSupplier = (String) jComboBox5.getSelectedItem();
        if (selectedSupplier != null && !selectedSupplier.equals("Select Product Supplier")) {
            // Implement supplier filtering
            logger.info("Selected supplier: " + selectedSupplier);
            filterProductsBySupplier(selectedSupplier);
        } else {
            loadProducts(); // Load all products if "Select Product Supplier" is chosen
        }
    }//GEN-LAST:event_jComboBox5ActionPerformed
    
    /**
     * Filter products by category
     */
    private void filterProductsByCategory(String category) {
        try {
            // Get category ID first
            int categoryId = getCategoryIdByName(category);
            if (categoryId > 0) {
                List<Product> products = inventoryController.getProductsByCategory(categoryId);
                productTableModel.setProducts(products);
                logger.info("Filtered " + products.size() + " products by category: " + category);
            }
        } catch (Exception e) {
            logger.severe("Error filtering products by category: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error filtering products: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Filter products by supplier
     */
    private void filterProductsBySupplier(String supplier) {
        try {
            // For now, we'll search products that might be related to this supplier
            // This is a simplified implementation since supplier-product relationship 
            // is stored in branches_has_products table
            List<Product> products = inventoryController.searchProducts(supplier);
            productTableModel.setProducts(products);
            logger.info("Filtered " + products.size() + " products by supplier: " + supplier);
        } catch (Exception e) {
            logger.severe("Error filtering products by supplier: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error filtering products: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Get category ID by name
     */
    private int getCategoryIdByName(String categoryName) {
        try {
            String query = "SELECT category_id FROM supramart.product_categories WHERE category_name = ?";
            ResultSet rs = MySQL.executePreparedSearch(query, categoryName);
            if (rs.next()) {
                return rs.getInt("category_id");
            }
        } catch (SQLException ex) {
            logger.severe("Error getting category ID: " + ex.getMessage());
        }
        return -1;
    }
    
    /**
     * Show inventory summary
     */
    private void showInventorySummary() {
        try {
            List<Product> allProducts = inventoryController.getAllProducts();
            List<Product> lowStockProducts = inventoryController.getLowStockProducts();
            
            double totalValue = inventoryController.calculateTotalInventoryValue(allProducts);
            
            String summary = String.format(
                "Inventory Summary:\n\n" +
                "Total Products: %d\n" +
                "Low Stock Items: %d\n" +
                "Total Inventory Value: LKR %.2f\n\n" +
                "Low Stock Alerts:\n",
                allProducts.size(), lowStockProducts.size(), totalValue
            );
            
            for (Product product : lowStockProducts) {
                summary += String.format("- %s (Stock: %d, Reorder Level: %d)\n", 
                    product.getName(), product.getStockQuantity(), product.getReorderLevel());
            }
            
            JOptionPane.showMessageDialog(this, summary, "Inventory Summary", JOptionPane.INFORMATION_MESSAGE);
            logger.info("Inventory summary displayed");
        } catch (Exception e) {
            logger.severe("Error showing inventory summary: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error loading inventory summary: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Clear all filters and show all products
     */
    private void clearAllFilters() {
        // Reset combo boxes
        jComboBox1.setSelectedItem("Select Branch");
        jComboBox3.setSelectedItem("Select Product Category");
        jComboBox4.setSelectedItem("Select Product Brand");
        jComboBox5.setSelectedItem("Select Product Supplier");
        
        // Clear search text
        jTextField1.setText("");
        jTextField2.setText("");
        
        // Load all products
        loadProducts();
        
        logger.info("All filters cleared");
    }
    
    /**
     * Show product details in a dialog
     */
    private void showProductDetails() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a product to view details", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Product product = productTableModel.getProductAt(selectedRow);
        if (product == null) {
            return;
        }
        
        String details = String.format(
            "Product Details:\n\n" +
            "ID: %d\n" +
            "Name: %s\n" +
            "Category: %s\n" +
            "Price: LKR %.2f\n" +
            "Cost: LKR %.2f\n" +
            "Stock Quantity: %d\n" +
            "Reorder Level: %d\n" +
            "Added On: %s\n\n" +
            "Profit Margin: %.2f%%",
            product.getProductId(),
            product.getName(),
            product.getCategoryName(),
            product.getPrice(),
            product.getCost(),
            product.getStockQuantity(),
            product.getReorderLevel(),
            product.getAddedOn(),
            inventoryController.calculateProfitMargin(product)
        );
        
        JOptionPane.showMessageDialog(this, details, "Product Details", JOptionPane.INFORMATION_MESSAGE);
        logger.info("Product details shown for: " + product.getName());
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        searchProductsFromTextField();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String searchTerm = jTextField2.getText();
        // TODO: Implement sales search functionality
        JOptionPane.showMessageDialog(this, 
            "Sales search functionality will be implemented here.\n" +
            "Search term: " + searchTerm, 
            "Sales Search", JOptionPane.INFORMATION_MESSAGE);
        logger.info("Sales search requested for: " + searchTerm);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO: Implement expenses report export
        JOptionPane.showMessageDialog(this, 
            "Expenses report export functionality will be implemented here.", 
            "Export Expenses Report", JOptionPane.INFORMATION_MESSAGE);
        logger.info("Expenses report export requested");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        exportStockReport();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        exportSupplierReport();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        exportSalesReport();
    }//GEN-LAST:event_jButton24ActionPerformed

    /**
     * Edit selected product
     */
    private void editSelectedProduct() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a product to edit", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Product product = productTableModel.getProductAt(selectedRow);
        if (product == null) {
            return;
        }
        
        try {
            editProduct editproduct = new editProduct(this, true);
            editproduct.setVisible(true);
            // Refresh table after editing
            refreshProductTable();
        } catch (Exception e) {
            logger.severe("Error opening edit product dialog: " + e.getMessage());
            JOptionPane.showMessageDialog(this, 
                "Error opening edit dialog: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new inventoryManagerDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
