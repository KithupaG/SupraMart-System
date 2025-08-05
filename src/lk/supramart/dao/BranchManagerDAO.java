/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Branch;

/**
 *
 * @author kithu
 */
public interface BranchManagerDAO {
    boolean addBranch(Branch branch);
    boolean updateBranch(Branch branch);
    boolean deleteBranch(int branchId);
    Branch getBranchById(int branchId);
    List<Branch> getAllBranches();
}
