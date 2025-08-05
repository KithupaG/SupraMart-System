/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Supplier;

/**
 *
 * @author kithu
 */
public interface SupplierDAO {
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    boolean deleteSupplier(int supplierId);
    
    Supplier getSupplierById(int supplierId);
    List<Supplier> getAllSuppliers();
    List<Supplier> searchSuppliersByName(String keyword);
    
}
