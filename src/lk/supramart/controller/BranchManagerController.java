/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.controller;

import java.util.List;
import lk.supramart.dao.BranchManagerDAO;
import lk.supramart.dao.BranchManagerDAOImpl;
import lk.supramart.model.BranchManager;
import lk.supramart.model.BranchProduct;
import lk.supramart.model.BranchProfit;

/**
 *
 * @author kithu
 */
public class BranchManagerController {

    private final BranchManagerDAO branchManagerDAO;
    private final BranchManagerDAOImpl dao = new BranchManagerDAOImpl();

    public BranchManagerController() {
        this.branchManagerDAO = new BranchManagerDAOImpl();
    }

    public List<BranchManager> getAllBranch() {
        return branchManagerDAO.getAllBranches();
    }

    public boolean updateBranchManager(BranchManager branchManager) {
        return branchManagerDAO.updateBranchManager(branchManager);
    }

    public List<BranchProduct> getAllBranchProducts() throws Exception {
        return dao.getAllBranchProducts();
    }

    public List<BranchProfit> getBranchProfitsPerMonth() throws Exception {
        return dao.getBranchProfitsPerMonth();
    }
}
