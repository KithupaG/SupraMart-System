package lk.supramart.model;

public class Employee {

    private String id, fname, lname, email, password, mobileNumber1, mobileNumber2, hiredDate;
    private int branchId, imageId, roleId;
    private double baseSalary;

    private Employee(Builder builder) {
        this.id = builder.id;
        this.fname = builder.fname;
        this.lname = builder.lname;
        this.email = builder.email;
        this.password = builder.password;
        this.mobileNumber1 = builder.mobileNumber1;
        this.mobileNumber2 = builder.mobileNumber2;
        this.hiredDate = builder.hiredDate;
        this.branchId = builder.branchId;
        this.imageId = builder.imageId;
        this.baseSalary = builder.baseSalary;
        this.roleId = builder.roleId;
    }

    public static class Builder {

        private String id, fname, lname, email, password;
        private String mobileNumber1, mobileNumber2, hiredDate;
        private int branchId, imageId, roleId;
        private double baseSalary;

        public Builder() {
        }
        
        public Builder(String id) {
            this.id = id;
        }

        public Builder setId(String id) {
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

        public Builder setMobileNumber1(String mobileNumber1) {
            this.mobileNumber1 = mobileNumber1;
            return this;
        }

        public Builder setMobileNumber2(String mobileNumber2) {
            this.mobileNumber2 = mobileNumber2;
            return this;
        }

        public Builder setHiredDate(String hiredDate) {
            this.hiredDate = hiredDate;
            return this;
        }

        public Builder setBranchId(int branchId) {
            this.branchId = branchId;
            return this;
        }

        public Builder setImageId(int imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder setBaseSalary(double baseSalary) {
            this.baseSalary = baseSalary;
            return this;
        }

        public Builder setRoleId(int roleId) {
            this.roleId = roleId;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
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

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    
    public String getFullName() {
        if (fname == null && lname == null) return null;
        if (fname == null) return lname;
        if (lname == null) return fname;
        return fname + " " + lname;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber1() {
        return mobileNumber1;
    }

    public String getMobileNumber2() {
        return mobileNumber2;
    }

    public String getHiredDate() {
        return hiredDate;
    }

    public int getBranchId() {
        return branchId;
    }

    public int getImageId() {
        return imageId;
    }

    public double getBaseSalery() {
        if (baseSalary < 0) {
            throw new IllegalArgumentException("Base salary cannot be negative or zero");
        }
        return baseSalary;
    }
    
    public Builder toBuilder() {//for updating existing employee
        return new Builder(this.id)
                .setPassword(this.password)
                .setFname(this.fname)
                .setLname(this.lname)
                .setEmail(this.email)
                .setMobileNumber1(this.mobileNumber1)
                .setMobileNumber2(this.mobileNumber2)
                .setHiredDate(this.hiredDate)
                .setBranchId(this.branchId)
                .setImageId(this.imageId)
                .setRoleId(this.roleId)
                .setBaseSalary(this.baseSalary);
    }

}
