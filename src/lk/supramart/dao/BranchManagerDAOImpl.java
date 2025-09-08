package lk.supramart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.supramart.connection.MySQL;
import lk.supramart.model.Branch;
import lk.supramart.util.LoggerUtil;

/**
 * JDBC implementation for BranchManagerDAO
 */
public class BranchManagerDAOImpl implements BranchManagerDAO {

    @Override
    public boolean addBranch(Branch branch) {
        if (branch == null) {
            return false;
        }
        String query = "INSERT INTO mydb.branches (branch_name, City_city_id) VALUES (?, ?)";
        try {
            int rows = MySQL.executePreparedIUD(query, branch.getName(), branch.getLocation());
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error adding branch: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateBranch(Branch branch) {
        if (branch == null || branch.getId() <= 0) {
            return false;
        }
        String query = "UPDATE mydb.branches SET branch_name = ?, City_city_id = ? WHERE branch_id = ?";
        try {
            int rows = MySQL.executePreparedIUD(query, branch.getName(), branch.getLocation(), branch.getId());
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error updating branch: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBranch(int branchId) {
        if (branchId <= 0) {
            return false;
        }
        String query = "DELETE FROM mydb.branches WHERE branch_id = ?";
        try {
            int rows = MySQL.executePreparedIUD(query, branchId);
            return rows > 0;
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error deleting branch: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public Branch getBranchById(int branchId) {
        if (branchId <= 0) {
            return null;
        }
        String query = "SELECT branch_id, branch_name, City_city_id FROM mydb.branches WHERE branch_id = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, branchId);
            if (rs.next()) {
                Branch branch = new Branch();
                branch.setId(rs.getInt("branch_id"));
                branch.setName(rs.getString("branch_name"));
                branch.setLocation(String.valueOf(rs.getInt("City_city_id")));
                return branch;
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error fetching branch: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        String query = "SELECT branch_id, branch_name, City_city_id FROM mydb.branches ORDER BY branch_name";
        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                Branch branch = new Branch();
                branch.setId(rs.getInt("branch_id"));
                branch.setName(rs.getString("branch_name"));
                branch.setLocation(String.valueOf(rs.getInt("City_city_id")));
                branches.add(branch);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(BranchManagerDAOImpl.class, "Error fetching branches: " + ex.getMessage());
        }
        return branches;
    }
}


