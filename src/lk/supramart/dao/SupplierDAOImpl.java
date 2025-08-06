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
                Supplier s = new Supplier.Builder()
                        .setId(rs.getString("supplier_id"))
                        .setName(rs.getString("supplier_name"))
                        .setPhone(rs.getString("phone_number"))
                        .setEmail(rs.getString("email"))
                        .build();
                suppliers.add(s);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(SupplierDAOImpl.class, "Error fetching suppliers: " + ex.getMessage());
        }

        return suppliers;
    }

    public List<Supplier> searchSuppliersByName(String name) {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers WHERE supplier_name = ?";
        try {
            ResultSet rs = MySQL.executePreparedSearch(query, name);

            if (rs.next()) {
                Supplier s = new Supplier.Builder()
                        .setId(rs.getString("supplier_id"))
                        .setName(rs.getString("supplier_name"))
                        .setPhone(rs.getString("contact_number"))
                        .setEmail(rs.getString("email"))
                        .setAddress(rs.getString("address"))
                        .build();
                suppliers.add(s);
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(SupplierDAOImpl.class, "Error searching supplier: " + ex.getMessage());
        }
        return suppliers;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (supplier_name, phone_number, email, address) VALUES (?, ?, ?, ?)";

        try {
            int rows = MySQL.executePreparedIUD(query, supplier.getName(), supplier.getPhone(), supplier.getEmail(), supplier.getAddress());
            return rows > 0; // return true if at least one row was inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(Supplier supplier) {
        String query = "INSERT INTO suppliers (supplier_name, phone_number, email, address) VALUES (?, ?, ?, ?)";

        try {
            int rows = MySQL.executePreparedIUD(query, supplier.getName(), supplier.getPhone(), supplier.getEmail(), supplier.getAddress());
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
            int rowsAffected = MySQL.executePreparedIUD(query, supplierId);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
