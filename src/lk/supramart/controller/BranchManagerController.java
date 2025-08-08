/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import java.util.List;
import lk.supramart.dao.BranchManagerDAO;
import lk.supramart.dao.BranchManagerDAOImpl;
import lk.supramart.model.BranchManager;

/**
 *
 * @author kithu
 */
public class BranchManagerController {
    private final BranchManagerDAO branchManagerDAO;

    public BranchManagerController() {
        this.branchManagerDAO = new BranchManagerDAOImpl();
    }

    public boolean addSupplier(BranchManager branch) {
        return branchManagerDAO.addBranch(branch);
    }

    public boolean updateSupplier(BranchManager branch) {
        return branchManagerDAO.updateBranch(branch);
    }

    public boolean deleteSupplier(int branchId) {
        return branchManagerDAO.deleteBranch(branchId);
    }

    public List<BranchManager> getAllBranch() {
        return branchManagerDAO.getAllBranches();
    }
    
    public boolean updateBranchManager(BranchManager branchManager) {
        return branchManagerDAO.updateBranchManager(branchManager);
    }
}
