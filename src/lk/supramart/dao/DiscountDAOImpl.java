package lk.supramart.dao;

import lk.supramart.model.Discount;
import lk.supramart.connection.MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanduni PC
 */
public class DiscountDAOImpl implements DiscountDAO {

    private final Connection conn;

    public DiscountDAOImpl() {
        conn = MySQL.getConnection();
    }
    
    @Override
    public void addDiscount(Discount discount) {
        String sql = "INSERT INTO discount (discount_id, discount_name, discount_percentage, start_date, end_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            
            pst.setInt(1, discount.getDiscountId());   
            pst.setString(2, discount.getDiscountName());   
            pst.setDouble(3, discount.getDiscountPercentage());   
            pst.setDate(4, java.sql.Date.valueOf(discount.getStartDate()));
            pst.setDate(5, java.sql.Date.valueOf(discount.getEndDate()));  
            
            pst.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateDiscount(Discount discount){
        String sql = "UPDATE discount SET discount_name=?, discount_percentage=?, start_date=?, end_date=? WHERE discount_id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            
            pst.setInt(1, discount.getDiscountId());   
            pst.setString(2, discount.getDiscountName());   
            pst.setDouble(3, discount.getDiscountPercentage());   
            pst.setDate(4, java.sql.Date.valueOf(discount.getStartDate()));
            pst.setDate(5, java.sql.Date.valueOf(discount.getEndDate()));  
            
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteDiscount(int discountId) {
        String sql = "DELETE FROM discount WHERE discount_id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, discountId);    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Discount getDiscountById(int discountId) {
        String sql = "SELECT * FROM discount WHERE discount_id=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)){
            pst.setInt(1, discountId); 
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Discount(
                    rs.getInt("discount_id"), 
                    rs.getString("discount_name"), 
                    rs.getDouble("discount_percentage"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM discount";
        try (PreparedStatement pst = conn.prepareStatement(sql);  ResultSet rs = pst.executeQuery()){
            while (rs.next()) {
                discounts.add(new Discount(
                    rs.getInt("discount_id"), 
                    rs.getString("discount_name"), 
                    rs.getDouble("discount_percentage"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discounts;
    }
}
