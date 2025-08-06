/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import lk.supramart.connection.MySQL;
import lk.supramart.model.User;
import lk.supramart.util.LoggerUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.supramart.model.Admin;

/**
 *
 * @author kithu
 */
public class AdminDAOImpl implements AdminDAO {

    @Override
    public boolean deleteUser(int userId) {
        String deleteQuery = "DELETE FROM employees WHERE employee_id = ?";
        try {
            int rowsAffected = MySQL.executePreparedIUD(deleteQuery, userId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(AdminDAOImpl.class, "Error deleting user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateInfo(Admin admin) {
        String query = "INSERT INTO admin (fname, lname, email, password) VALUES (?, ?, ?, ?)";
        try {
            int rows = MySQL.executePreparedIUD(query,
                    admin.getFName(),
                    admin.getLName(),
                    admin.getEmail(),
                    admin.getPassword());
            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(AdminDAOImpl.class, "Error updating admin info: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (ResultSet rs = MySQL.executePreparedSearch(query)) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("employee_id"));
                u.setName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setNumber1(rs.getString("mobile_number_1"));
                u.setNumber2(rs.getString("mobile_number_2"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getInt("role_id"));
                u.setBranchId(rs.getInt("branch_id"));
                u.setHiredDate(rs.getString("hire_date"));
                u.setBaseSalary(rs.getString("base_salary"));
                users.add(u);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(AdminDAOImpl.class, "Error fetching employees: " + ex.getMessage());
        }

        return users;
    }
}