/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Branch;
import lk.supramart.model.User;

public interface AdminDAO {
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int userId);
    User getUserById(int userId);
    List<User> getAllUsers();
    List<User> searchUsersByRole(String role);

}
