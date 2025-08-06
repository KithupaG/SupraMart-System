/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class Admin {

    private int id;
    private String fname, lname, email, password;

    private Admin(Builder builder) {
        this.id = builder.id;
        this.fname = builder.fname;
        this.lname = builder.lname;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder {

        private int id;
        private String fname, lname, email, password;

        public Builder setId(int id) {
            this.id = id;
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

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }

    }

    public String getFName() {
        return fname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getLname() {
        return lname;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fname + " " + lname;
    }

}
