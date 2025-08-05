/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import java.util.List;
import lk.supramart.dao.SupplierDAO;
import lk.supramart.dao.SupplierDAOImpl;
import lk.supramart.model.Supplier;

/**
 *
 * @author kithu
 */
public class SupplierController {
     private final SupplierDAO supplierDAO;

    public SupplierController() {
        this.supplierDAO = new SupplierDAOImpl();
    }

    public boolean addSupplier(Supplier supplier) {
        return supplierDAO.addSupplier(supplier);
    }

    public boolean updateSupplier(Supplier supplier) {
        return supplierDAO.updateSupplier(supplier);
    }

    public boolean deleteSupplier(int supplierId) {
        return supplierDAO.deleteSupplier(supplierId);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    public List<Supplier> searchSuppliersByName(String keyword) {
        return supplierDAO.searchSuppliersByName(keyword);
    }
}
