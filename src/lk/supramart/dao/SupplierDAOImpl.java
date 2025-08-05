package lk.supramart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lk.supramart.connection.MySQL;
import lk.supramart.model.Supplier;
import lk.supramart.util.LoggerUtil;

public class SupplierDAOImpl implements SupplierDAO {

    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers";

        try {
            ResultSet rs = MySQL.executePreparedSearch(query);
            while (rs.next()) {
                Supplier s = new Supplier();
                s.setId(rs.getInt("supplier_id"));
                s.setName(rs.getString("supplier_name"));
                s.setPhone(rs.getString("phone_number"));
                s.setEmail(rs.getString("email"));
                suppliers.add(s);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(SupplierDAOImpl.class, "Error fetching suppliers: " + ex.getMessage());
        }

        return suppliers;
    }

    public List<Supplier> searchSuppliersByName(String name) {
        String query = "SELECT * FROM suppliers WHERE supplier_name = ?";
        try {
            PreparedStatement stmt = MySQL.getConnection().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Supplier s = new Supplier();
                s.setId(rs.getInt("supplier_id"));
                s.setName(rs.getString("supplier_name"));
                s.setPhone(rs.getString("contact_number"));
                s.setEmail(rs.getString("email"));
                s.setAddress(rs.getString("address"));
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(SupplierDAOImpl.class, "Error searching supplier: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (supplier_name, phone_number, email, address) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getAddress());

            int rows = ps.executeUpdate();
            return rows > 0; // return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (supplier_name, phone_number, email, address) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getPhone());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getAddress());

            int rows = ps.executeUpdate();
            return rows > 0; // return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteSupplier(int supplierId) {
        String query = "DELETE FROM suppliers WHERE supplier_id = ?";

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(query);
            ps.setInt(1, supplierId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
