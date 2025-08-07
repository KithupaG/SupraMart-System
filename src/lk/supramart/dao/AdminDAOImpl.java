/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.util.ArrayList;
import java.util.List;
import lk.supramart.connection.MySQL;
import lk.supramart.util.LoggerUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.supramart.model.Employee;
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
            int rows = MySQL.executePreparedIUD(query, admin.getFName(),admin.getLname(),admin.getEmail(),admin.getPassword());

            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(AdminDAOImpl.class, "Error updating admin info: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Employee> getAllUsers() {
        List<Employee> users = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (ResultSet rs = MySQL.executePreparedSearch(query)) {
            while (rs.next()) {

                Employee employee = new Employee.Builder(rs.getString("employee_id"))
                        .setFname(rs.getString("first_name"))
                        .setLname(rs.getString("last_name"))
                        .setMobileNumber1(rs.getString("mobile_number_1"))
                        .setMobileNumber2(rs.getString("mobile_number_1"))
                        .setEmail(rs.getString("email"))
                        .setRoleId(rs.getInt("role_id"))
                        .setBranchId(rs.getInt("branch_id"))
                        .setHiredDate(rs.getString("hire_date"))
                        .setBaseSalary(rs.getDouble("base_salary"))
                        .build();
                users.add(employee);
                        

            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(AdminDAOImpl.class, "Error fetching employees: " + ex.getMessage());
        }

        return users;
    }
}