/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import lk.supramart.model.Employee;

/**
 *
 * @author kithu
 */
public interface EmployeeDAO {
    boolean employeeLogin(Employee employee);
    boolean employeeRegister(Employee employee);
}