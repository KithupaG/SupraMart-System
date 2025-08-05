/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class User {
    private int id;
    private String username;
    private String password;
    private int role;
    private int branchId;
    private String fname;
    private String lname;
    private String email;
    private String mobile_number1;
    private String mobile_number2;
    private String hired_date;
    private String base_salary;
    
    public User() {
    }

    public User(int id, String fname, String lname, String mobile_number1, String mobile_number2, String email, int branchId, String password, String hired_date, String base_salary, int role) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.branchId = branchId;
        this.mobile_number1 = mobile_number1;
        this.mobile_number2 = mobile_number2;
        this.hired_date = hired_date;
        this.base_salary = base_salary;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setName(String string) {
        this.fname = fname;
    }

    public void setLastName(String string) {
        this.lname = lname;
    }

    public void setNumber1(String string) {
        this.mobile_number1 = mobile_number1;
    }

    public void setNumber2(String string) {
        this.mobile_number2 = mobile_number2;
    }

    public void setEmail(String string) {
        this.email = email;
    }

    public void setHiredDate(String string) {
        this.hired_date = mobile_number2;
    }

    public void setBaseSalary(String string) {
        this.base_salary = base_salary;
    }
}
