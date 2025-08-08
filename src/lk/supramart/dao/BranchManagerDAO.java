/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.BranchManager;
import java.sql.ResultSet;

/**
 *
 * @author kithu
 */
public interface BranchManagerDAO {
    boolean addBranch(BranchManager branch);
    boolean updateBranch(BranchManager branch);
    boolean deleteBranch(int branchId);
    boolean updateBranchManager(BranchManager branchManager);
    
    List<BranchManager> getAllBranches();
    ResultSet getBranchProducts(String branchName);
    ResultSet getBranchEmployees(String branchName);
    ResultSet getBranchAdmins(String branchName);
    public ResultSet getManagerById(String id);
}
