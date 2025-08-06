/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Admin;
import lk.supramart.model.Employee;

public interface AdminDAO {
    boolean deleteUser(int userId);
    boolean updateInfo(Admin admin);
    List<Employee> getAllUsers();
}
