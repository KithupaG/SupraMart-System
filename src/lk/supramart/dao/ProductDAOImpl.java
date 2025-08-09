/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import java.sql.SQLException;
import lk.supramart.connection.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.supramart.dao.Product;
import lk.supramart.util.LoggerUtil;

/**
 *
 * @author nimut
 */
public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO products "
                + "(product_id, product_name, category_id, product_price, product_cost, product_quantity, product_added_datetime)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            int rowsAffected = MySQL.executePreparedIUD(sql, product.getId(),
                    product.getProductName(), product.getProductCategoryId(),
                    product.getPrice(), product.getCost(), product.getStock(),
                    product.getAddedDateTime());
            return rowsAffected > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(ProductDAOImpl.class, "Error while inserting product" + e.getMessage());
            return false;
        }
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(sql, id);
            if (rs.next()) {
                return new Product.Builder()
                        .setId(rs.getString("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .setProductCategoryId(rs.getInt("category_id"))
                        .setPrice(rs.getDouble("product_price"))
                        .setCost(rs.getDouble("product_cost"))
                        .setStock(rs.getInt("product_quantity"))
                        .setAddedDateTime(rs.getTimestamp("product_added_datetime"))
                        .build();
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(ProductDAOImpl.class, "Error while Selecting product" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (ResultSet rs = MySQL.executePreparedSearch(sql)) {
            while (rs.next()) {
                list.add(new Product.Builder()
                        .setId(rs.getString("product_id"))
                        .setProductName(rs.getString("product_name"))
                        .setProductCategoryId(rs.getInt("category_id"))
                        .setPrice(rs.getDouble("product_price"))
                        .setCost(rs.getDouble("product_cost"))
                        .setStock(rs.getInt("product_quantity"))
                        .setAddedDateTime(rs.getTimestamp("product_added_datetime"))
                        .build());
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(ProductDAOImpl.class, "Error while Selecting products" + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE products SET name=?, price=? WHERE id=?";
        try {
            int rowsAffected = MySQL.executePreparedIUD(sql, product.getProductName(),
                    product.getPrice(), product.getId());
            return rowsAffected > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(ProductDAOImpl.class, "Error while Updating product" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try {
            int rowsAffected = MySQL.executePreparedIUD(sql, id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(ProductDAOImpl.class, "Error while Deleting product" + e.getMessage());
            return false;
        }
    }

}
