package lk.supramart.controller;

import java.util.List;
import lk.supramart.dao.BranchManagerDAO;
import lk.supramart.dao.BranchManagerDAOImpl;
import lk.supramart.model.Branch;

public class BranchManagerController {
    private final BranchManagerDAO branchDAO;

    public BranchManagerController() {
        this.branchDAO = new BranchManagerDAOImpl();
    }

    public boolean addBranch(Branch branch) {
        return branchDAO.addBranch(branch);
    }

    public boolean updateBranch(Branch branch) {
        return branchDAO.updateBranch(branch);
    }

    public boolean deleteBranch(int branchId) {
        return branchDAO.deleteBranch(branchId);
    }

    public Branch getBranchById(int branchId) {
        return branchDAO.getBranchById(branchId);
    }

    public List<Branch> getAllBranches() {
        return branchDAO.getAllBranches();
    }
}


