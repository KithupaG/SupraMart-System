/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

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
        
        try (ResultSet rs = MySQL.executePreparedSearch(query)) {
            while (rs.next()) {
                BranchManager bm = new BranchManager.Builder()
                        .setId(rs.getInt("branch_id"))
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

    @Override
    public boolean addBranch(BranchManager branch) {
        String query = "INSERT INTO branches (branch_id, branch_name, City_city_id) VALUES (?, ?, ?)";

        try {
            int rows = MySQL.executePreparedIUD(query,
                    branch.getId(),
                    branch.getName(),
                    branch.getCityId());
            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error adding branch: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBranch(BranchManager branch) {
        String query = "UPDATE branches SET branch_name = ?, City_city_id = ? WHERE branch_id = ?";

        try {
            int rows = MySQL.executePreparedIUD(query,
                    branch.getName(),
                    branch.getCityId(),
                    branch.getId());
            return rows > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error updating branch: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBranch(int branchId) {
        String query = "DELETE FROM branches WHERE branch_id = ?";

        try {
            int rowsAffected = MySQL.executePreparedIUD(query, branchId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error deleting branch: " + e.getMessage());
            return false;
        }
    }
}
