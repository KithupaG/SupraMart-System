/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.util.ArrayList;
import java.util.List;
import lk.supramart.connection.MySQL;
import lk.supramart.model.Customer;
import lk.supramart.util.LoggerUtil;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author kithu
 */
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (branch_id, fname, lname, email, mobile, registered_date) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int rows = MySQL.executePreparedIUD(sql,
                    customer.getBranchId(),
                    customer.getFname(),
                    customer.getLname(),
                    customer.getEmail(),
                    customer.getMobile(),
                    customer.getRegisteredDate()
            );
            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(getClass(), "Add Customer Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET branch_id=?, fname=?, lname=?, email=?, mobile=?, registered_date=? WHERE id=?";
        try {
            int rows = MySQL.executePreparedIUD(sql,
                    customer.getBranchId(),
                    customer.getFname(),
                    customer.getLname(),
                    customer.getEmail(),
                    customer.getMobile(),
                    customer.getRegisteredDate(),
                    customer.getId()
            );
            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(getClass(), "Update Customer Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id=?";
        try {
            int rows = MySQL.executePreparedIUD(sql, id);
            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(getClass(), "Delete Customer Error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customer WHERE id=?";
        try (ResultSet rs = MySQL.executePreparedSearch(sql, id)) {
            if (rs.next()) {
                return mapCustomer(rs);
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(getClass(), "Get Customer By ID Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (ResultSet rs = MySQL.executePreparedSearch(sql)) {
            while (rs.next()) {
                list.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(getClass(), "Get All Customers Error: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE fname LIKE ? OR lname LIKE ? OR email LIKE ?";
        String searchValue = "%" + keyword + "%";
        try (ResultSet rs = MySQL.executePreparedSearch(sql, searchValue, searchValue, searchValue)) {
            while (rs.next()) {
                list.add(mapCustomer(rs));
            }
        } catch (SQLException e) {
            LoggerUtil.Log.severe(getClass(), "Search Customers Error: " + e.getMessage());
        }
        return list;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer.Builder()
                .setId(rs.getInt("id"))
                .setBranchId(rs.getInt("branch_id"))
                .setFname(rs.getString("fname"))
                .setLname(rs.getString("lname"))
                .setEmail(rs.getString("email"))
                .setMobile(rs.getString("mobile"))
                .setRegisteredDate(rs.getString("registered_date"))
                .build();
    }
}
