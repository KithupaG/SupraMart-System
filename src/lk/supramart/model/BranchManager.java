/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class BranchManager {
    
    private int id;
    private String branch_name;
    private int city_id;
    
    public BranchManager() {}
    
    public BranchManager(int id, String branch_name, int city_id) {
        this.id = id;
        this.branch_name = branch_name;
        this.city_id = city_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setCityId(int city_id) {
        this.city_id = city_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return branch_name;
    }

    public int getCityId() {
        return city_id;
    }
    
}
