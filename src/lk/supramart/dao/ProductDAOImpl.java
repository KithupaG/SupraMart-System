package lk.supramart.dao;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import lk.supramart.connection.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author nimut
 */
public class ProductDAOImpl implements ProductDAO {

    private Connection conn;

    public ProductDAOImpl() {
        conn = MySQL.getConnection();
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO products "
                + "(product_id, product_name, category_id, product_price, product_cost,"
                + " product_quantity, product_added_datetime)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            MySQL.executePreparedIUD(
                    sql
                    , product.getId()
                    ,product.getProductName()
                    ,product.getCategoryId()
                    ,product.getPrice()
                    ,product.getPrice()
                    ,product.getPrice()
                    ,product.getPrice()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("product_id"), 
                        rs.getString("product_name"), 
                        rs.getInt("category_id"), 
                        rs.getDouble("product_price"), 
                        rs.getDouble("product_cost"), 
                        rs.getInt("product_quantity"), 
                        rs.getTimestamp("product_added_datetime")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("product_id"), 
                        rs.getString("product_name"), 
                        rs.getInt("category_id"), 
                        rs.getDouble("product_price"), 
                        rs.getDouble("product_cost"), 
                        rs.getInt("product_quantity"), 
                        rs.getTimestamp("product_added_datetime")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET name=?, price=? WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
