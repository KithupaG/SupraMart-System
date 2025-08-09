/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import java.util.List;
import lk.supramart.dao.CustomerDAO;
import lk.supramart.dao.CustomerDAOImpl;
import lk.supramart.model.Customer;
import lk.supramart.util.LoggerUtil;

/**
 *
 * @author kithu
 */
public class CustomerController {
    private final CustomerDAO customerDAO;

    public CustomerController() {
        this.customerDAO = new CustomerDAOImpl();
    }

    public boolean addCustomer(int branchId, String fname, String lname, String email, String mobile, String registeredDate) {
        try {
            Customer customer = new Customer.Builder()
                    .setBranchId(branchId)
                    .setFname(fname)
                    .setLname(lname)
                    .setEmail(email)
                    .setMobile(mobile)
                    .setRegisteredDate(registeredDate)
                    .build();

            return customerDAO.addCustomer(customer);
        } catch (Exception e) {
            LoggerUtil.Log.severe(getClass(), "Add Customer Controller Error: " + e.getMessage());
            return false;
        }
    }

    public boolean updateCustomer(int id, int branchId, String fname, String lname, String email, String mobile, String registeredDate) {
        try {
            Customer customer = new Customer.Builder()
                    .setId(id)
                    .setBranchId(branchId)
                    .setFname(fname)
                    .setLname(lname)
                    .setEmail(email)
                    .setMobile(mobile)
                    .setRegisteredDate(registeredDate)
                    .build();

            return customerDAO.updateCustomer(customer);
        } catch (Exception e) {
            LoggerUtil.Log.severe(getClass(), "Update Customer Controller Error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteCustomer(int id) {
        try {
            return customerDAO.deleteCustomer(id);
        } catch (Exception e) {
            LoggerUtil.Log.severe(getClass(), "Delete Customer Controller Error: " + e.getMessage());
            return false;
        }
    }

    public Customer getCustomerById(int id) {
        try {
            return customerDAO.getCustomerById(id);
        } catch (Exception e) {
            LoggerUtil.Log.severe(getClass(), "Get Customer By ID Controller Error: " + e.getMessage());
            return null;
        }
    }

    public List<Customer> getAllCustomers() {
        try {
            return customerDAO.getAllCustomers();
        } catch (Exception e) {
            LoggerUtil.Log.severe(getClass(), "Get All Customers Controller Error: " + e.getMessage());
            return null;
        }
    }

    public List<Customer> searchCustomers(String keyword) {
        try {
            return customerDAO.searchCustomers(keyword);
        } catch (Exception e) {
            LoggerUtil.Log.severe(getClass(), "Search Customers Controller Error: " + e.getMessage());
            return null;
        }
    }
}
