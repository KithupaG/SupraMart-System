package lk.supramart.model;

public class Employee {

    private String id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String mobileNumber1;
    private String mobileNumber2;
    private String hiredDate;
    private int branchId;
    private int imageId;
    private double baseSalery;
    private int roleId;

    public Employee(String id, String password, int roleId) {
        this.id = id;
        this.password = password;
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
    }
}
