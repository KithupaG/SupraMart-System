/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import lk.supramart.database.MySQL;
import lk.supramart.model.Supplier;

import java.sql.*;
import lk.supramart.util.LoggerUtil;
/**
 *
 * @author kithu
 */
public class SupplierDAOImpl {
    public boolean getSupplier(Supplier s) {
        String query = "SELECT * FROM suppliers";
        try {
            ResultSet rs = MySQL.executeSearch(query);
            return rs.next();
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(SupplierDAOImpl.class, "Invalid ID or Password");
            return false;
        }
    }
}
