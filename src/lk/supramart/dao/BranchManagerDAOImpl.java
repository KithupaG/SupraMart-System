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
import lk.supramart.model.BranchProduct;
import lk.supramart.model.BranchProfit;
import lk.supramart.util.LoggerUtil;

/**
 *
 * @author kithu
 */
public class BranchManagerDAOImpl implements BranchManagerDAO {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BranchManagerDAOImpl.class.getName());
    
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

    public List<BranchProduct> getAllBranchProducts() throws Exception {
        String sql = "SELECT b.branch_name, "
                + "       p.product_id, p.name, "
                + "       bhp.quantity_change AS available_stock, "
                + "       s.supplier_name "
                + "FROM branches_has_products bhp "
                + "JOIN branches b ON bhp.branches_branch_id = b.branch_id "
                + "JOIN products p ON bhp.products_product_id = p.product_id "
                + "JOIN suppliers s ON bhp.suppliers_supplier_id = s.supplier_id";

        ResultSet rs = MySQL.executePreparedSearch(sql);
        List<BranchProduct> list = new ArrayList<>();

        while (rs.next()) {
            BranchProduct bp = new BranchProduct(
                    rs.getString("branch_name"),
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getInt("available_stock"),
                    rs.getString("supplier_name")
            );
            list.add(bp);
        }
        return list;
    }

    @Override
    public ResultSet getBranchProducts(String branchName) {
        // Return a ResultSet for a given branch name (if you prefer direct ResultSet usage)
        String query
                = "SELECT b.branch_name, p.product_id, p.name, s.supplier_id, s.supplier_name, bhp.available_stock "
                + "FROM branches_has_products bhp "
                + "JOIN branches b ON bhp.branches_branch_id = b.branch_id "
                + "JOIN products p ON bhp.products_product_id = p.product_id "
                + "LEFT JOIN suppliers s ON p.supplier_id = s.supplier_id "
                + "WHERE b.branch_name = ?";
        try {
            return MySQL.executePreparedSearch(query, branchName);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateBranchManager(BranchManager branchManager) {
        String query = "UPDATE employees "
                + "SET first_name = ?, email = ?, password = ? "
                + "WHERE employee_id = ? AND role_id = 5";

        try (PreparedStatement ps = MySQL.getConnection().prepareStatement(query)) {
            ps.setString(1, branchManager.getFName());
            ps.setString(2, branchManager.getEmail());
            ps.setString(3, branchManager.getPassword());
            ps.setString(4, branchManager.getId());

            // <<< Add these logging lines here, right before executeUpdate() >>>
            logger.info("Updating employee_id: " + branchManager.getId());
            logger.info("first_name: " + branchManager.getFName());
            logger.info("email: " + branchManager.getEmail());
            logger.info("password: " + branchManager.getPassword());

            int rows = ps.executeUpdate();
            logger.info("Rows affected: " + rows);

            return rows > 0;
        } catch (SQLException e) {
            logger.severe("SQLException while updating branch manager: " + e.getMessage());
            e.printStackTrace();
            return false;
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

    @Override
    public List<BranchProfit> getBranchProfitsPerMonth() throws SQLException {
        String sql
                = "SELECT b.branch_name, "
                + "       m.month, "
                + "       COALESCE(i.total_income, 0) AS total_income, "
                + "       COALESCE(e.total_expense, 0) AS total_expense "
                + "FROM branches b "
                + "LEFT JOIN ( "
                + "    SELECT branches_branch_id, DATE_FORMAT(date, '%Y-%m') AS month, SUM(monthly_income) AS total_income "
                + "    FROM income "
                + "    GROUP BY branches_branch_id, DATE_FORMAT(date, '%Y-%m') "
                + ") i ON b.branch_id = i.branches_branch_id "
                + "LEFT JOIN ( "
                + "    SELECT branches_branch_id, DATE_FORMAT(date, '%Y-%m') AS month, SUM(monthly_expenses) AS total_expense "
                + "    FROM expenses "
                + "    GROUP BY branches_branch_id, DATE_FORMAT(date, '%Y-%m') "
                + ") e ON b.branch_id = e.branches_branch_id AND i.month = e.month "
                + "LEFT JOIN ( "
                + "    SELECT DATE_FORMAT(date, '%Y-%m') AS month FROM income "
                + "    UNION "
                + "    SELECT DATE_FORMAT(date, '%Y-%m') AS month FROM expenses "
                + ") m ON m.month = i.month OR m.month = e.month "
                + "ORDER BY m.month, b.branch_name";

        ResultSet rs = MySQL.executePreparedSearch(sql);
        List<BranchProfit> profits = new ArrayList<>();

        while (rs.next()) {
            profits.add(new BranchProfit(
                    rs.getString("branch_name"),
                    rs.getString("month"),
                    rs.getDouble("total_income"),
                    rs.getDouble("total_expense")
            ));
        }
        return profits;
    }

}
