/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Customer;
/**
 *
 * @author kithu
 */
public interface CustomerDAO {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomers(String keyword);
}
