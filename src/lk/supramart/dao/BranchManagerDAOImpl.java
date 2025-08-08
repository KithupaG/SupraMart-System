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
        String query = "SELECT * FROM branches WHERE branch_name= ?";

        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                BranchManager bm = new BranchManager.Builder()
                        .setId(rs.getInt("branch_id"))
                        .setBranch_name(rs.getString("branch_name"))
                        .setCity_id(rs.getInt("City_city_id"))
                        .build();
                branchManagerList.add(bm);

                return branchManagerList;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(SupplierDAOImpl.class, "Error fetching branches: " + ex.getMessage());
        }

        return branchManagerList;
    }

    public boolean addBranch(BranchManager branch) {
        String query = "INSERT INTO branch (branch_id, branch_name, City_city_id) VALUES (?,?,?)";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setInt(1, branch.getId());
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
    public boolean deleteBranch(int branchId) {
        String query = "DELETE FROM branches WHERE branch_id = ?";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setInt(1, branchId);

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
            ps.setString(1, branchManager.getName());
            ps.setString(2, branchManager.getEmail());
            ps.setString(3, branchManager.getPassword());
            ps.setInt(4, branchManager.getId());
            
            int rows = ps.executeUpdate();
            return rows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet getBranchProducts(String branchName) {
        String query = "SELECT p.product_id, p.product_name, p.available_stock FROM"
                + " products p JOIN branches_has_products ON"
                + " p.product_id = bhp.products_product_id JOIN branches b ON"
                + " p.product_id = branches_has_products.products_product_id JOIN branches b ON"
                + " branches_has_products.branches_branch_id = branchName. branch_id WHERE b.branchName = ?";
        try {
            return MySQL.executePreparedSearch(query, branchName);
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getBranchEmployees(String branchName) {
        String query = "SELECT e.employee_id, e.fname, e.lname FROM employee e JOIN"
                + " branches b ON e.branch_id = b.branch_id WHERE"
                + " b.branch_name = ?";
        
        try {
            return MySQL.executePreparedSearch(query, branchName);
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultSet getBranchAdmins(String branchName) {
        String query = "SELECT a.admin_id, a.fname FORM admin a JOIN"
                + " admin a JOIN admin_has_branches ahb ON"
                + " a.admin_id = ahb.admin_admin_id JOIN"
                + " branches b ON ahb.branches_branch_id = b.branch_id WHERE"
                + " b.branch_name = ?";
        
        try {
            return MySQL.executePreparedSearch(query, branchName);
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
