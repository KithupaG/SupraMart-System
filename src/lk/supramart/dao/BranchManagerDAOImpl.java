/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

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

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
