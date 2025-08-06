/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class Customer {

    private int id, branchId;
    private String fname, lname, email, mobile, registeredDate;

    private Customer(Builder builder) {
        this.id = id;
        this.branchId = branchId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.registeredDate = registeredDate;
    }

    public int getId() {
        return id;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public static class Builder {

        private int id, branchId;
        private String fname, lname, email, mobile, registeredDate;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setBranchId(int branchId) {
            this.branchId = branchId;
            return this;
        }

        public Builder setFname(String fname) {
            this.fname = fname;
            return this;
        }

        public Builder setLname(String lname) {
            this.lname = lname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder setRegisteredDate(String registeredDate) {
            this.registeredDate = registeredDate;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
    
    
}
