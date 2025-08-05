/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import java.util.List;
import lk.supramart.dao.AdminDAO;
import lk.supramart.dao.AdminDAOImpl;
import lk.supramart.model.User;

/**
 *
 * @author kithu
 */
public class UserController {
    private final AdminDAO adminDAO;

    public UserController() {
        this.adminDAO = new AdminDAOImpl();
    }

    public boolean deleteUser(int userId) {
        return adminDAO.deleteUser(userId);
    }

    public List<User> getAllUsers() {
        return adminDAO.getAllUsers();
    }
}
