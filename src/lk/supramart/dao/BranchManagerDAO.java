/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.BranchManager;

/**
 *
 * @author kithu
 */
public interface BranchManagerDAO {
    boolean addBranch(BranchManager branch);
    boolean updateBranch(BranchManager branch);
    boolean deleteBranch(int branchId);
    List<BranchManager> getAllBranches();
}
