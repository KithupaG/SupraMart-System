/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import lk.supramart.dao.AdminDAO;
import lk.supramart.dao.AdminDAOImpl;
import lk.supramart.model.Admin;

/**
 *
 * @author kithu
 */
public class AdminController {
    private final AdminDAO adminDAO;

    public AdminController() {
        this.adminDAO = new AdminDAOImpl();
    }

    public boolean updateInfo(Admin admin) {
        return adminDAO.updateInfo(admin);
    }
}
