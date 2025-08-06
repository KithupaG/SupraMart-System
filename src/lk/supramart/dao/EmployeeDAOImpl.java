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
        String query = "SELECT COUNT(*) FROM employees WHERE employee_id = ? AND password = ? AND role_id = ?;";
        try {
            ResultSet rs = MySQL.executePreparedSearch(query,
                    employee.getId(),employee.getPassword(),employee.getId());
            return rs.next();
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(EmployeeDAOImpl.class, "Invalid ID or Password");
            return false;
        }
    }

    @Override
    public synchronized boolean employeeRegister(Employee employee) {
        String selectQuery = "SELECT `employee_id` FROM `employees` WHERE `employee_id` = ? AND `email` = ?;";
        String insertQuery = "INSERT INTO `employees` (`employee_id`,"
                + "`first_name`,`last_name`,`email`,`password`,`dob`,"
                + "`mobile_number_1`,`mobile_number_2`,`hire_date`,`branch_id`,"
                + "`image_id`,`base_salary`,`role_id`,`gender_id`) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            ResultSet rs = MySQL.executePreparedSearch(selectQuery, 
                    employee.getId(),employee.getEmail());
            if (rs.next()) {
                lk.supramart.gui.admin.EmployeeRegistration.getMessageBox()
                        .setText("This Employee already exists.");
                return false;
            } else {
                int rowCount = MySQL.executePreparedIUD(insertQuery, 
                        employee.getId(),employee.getFname(),employee.getLname(),
                        employee.getEmail(),employee.getPassword(),employee.getDob(),
                        employee.getMobileNumber1(),employee.getMobileNumber2(),
                        employee.getHiredDate(),employee.getBranchId(),employee.getImageId(),
                        employee.getBaseSalery(),employee.getRoleId(),employee.getGenderId());
                return rowCount > 0;
                
            }
        } catch (SQLException ex) {
            LoggerUtil.Log.severe(EmployeeDAOImpl.class, 
                    "Something went wrong while executing the query");
            return false;
        }
    }
}