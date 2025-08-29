/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.supramart.model.BranchManager;
import lk.supramart.connection.MySQL;
import lk.supramart.util.LoggerUtil;

/**
 *
 * @author kithu
 */
public class BranchManagerDAOImpl implements BranchManagerDAO {

    @Override
    public List<BranchManager> getAllBranches() {
        List<BranchManager> branchManagerList = new ArrayList<>();
        String query = "SELECT * FROM branches";

        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                BranchManager bm = new BranchManager.Builder()
                        .setId(rs.getString("branch_id"))
                        .setBranch_name(rs.getString("branch_name"))
                        .setCity_id(rs.getInt("City_city_id"))
                        .build();
                branchManagerList.add(bm);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error fetching branches: " + ex.getMessage());
        }
        return branchManagerList;
    }

    public ResultSet getBranchInfo(String branchName, int managerRoleId) {
        String sql
                = "SELECT "
                + "  b.branch_address AS address, "
                + "  CONCAT(m.first_name, ' ', m.last_name) AS manager_name "
                + "FROM branches b "
                + "LEFT JOIN employees m ON m.branch_id = b.branch_id AND m.role_id = ? "
                + "WHERE b.branch_name = ?";
        try {
            return MySQL.executePreparedSearch(sql, managerRoleId, branchName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addBranch(BranchManager branch) {
        String query = "INSERT INTO branch (branch_id, branch_name, City_city_id) VALUES (?,?,?)";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setString(1, branch.getId());
            ps.setString(2, branch.getName());
            ps.setInt(3, branch.getCityId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBranch(BranchManager branch) {
        String query = "INSERT INTO branches (branch_name, City_city_id) VALUES (?,?)";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setString(1, branch.getName());
            ps.setInt(2, branch.getCityId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBranch(String branchId) {
        String query = "DELETE FROM branches WHERE branch_id = ?";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setString(1, branchId);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBranchManager(BranchManager branchManager) {
        String query = "UPDATE employees SET first_name = ?, email = ?, password = ? WHERE employee_id = ? AND role_id = 4";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setString(1, branchManager.getId());
            ps.setString(2, branchManager.getName());
            ps.setString(3, branchManager.getEmail());
            ps.setString(4, branchManager.getPassword());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet getBranchProducts(String branchName) {
        String query = "SELECT p.product_id, p.product_name, bhp.available_stock\n"
                + "FROM products p\n"
                + "JOIN branches_has_products bhp ON p.product_id = bhp.products_product_id\n"
                + "JOIN branches b ON bhp.branches_branch_id = b.branch_id\n"
                + "WHERE b.branch_name = ?";
        try {
            return MySQL.executePreparedSearch(query, branchName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getBranchEmployees(String branchName) {
        String query = "SELECT e.employee_id, e.first_name, e.last_name\n"
                + "FROM employees e\n"
                + "JOIN branches b ON e.branch_id = b.branch_id\n" + "WHERE b.branch_name = ?";

        try {
            return MySQL.executePreparedSearch(query, branchName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getBranchAdmins(String branchName) {
        String query = "SELECT a.admin_id, a.first_name, a.last_name\n"
                + "FROM admin a\n"
                + "JOIN admin_has_branches ahb ON a.admin_id = ahb.admin_admin_id\n"
                + "JOIN branches b ON ahb.branches_branch_id = b.branch_id\n"
                + "WHERE b.branch_name = ?";

        try {
            return MySQL.executePreparedSearch(query, branchName);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getManagerById(String id) {
        String query = "SELECT * FROM employees WHERE employee_id = ?";

        try {
            return MySQL.executePreparedSearch(query, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
