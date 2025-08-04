/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import lk.supramart.connection.MySQL;
import lk.supramart.model.Employee;
import lk.supramart.util.LoggerUtil;

/**
 *
 * @author Yashitha
 */
public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public synchronized boolean employeeLogin(Employee employee) {
        String query = "SELECT COUNT(*) FROM `employees` WHERE `employee_id` = ? AND `password` = ? AND `role_id` = ?;";
        try {
            ResultSet rs = MySQL.executePreparedSearch(query,employee.getId(),employee.getPassword(),employee.getId());
            return rs.next();
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(EmployeeDAOImpl.class, "Invalid ID or Password");
            return false;
        }
    }
    
}
