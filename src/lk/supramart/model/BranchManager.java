package lk.supramart.model;

public class BranchManager {

    private int city_id;
    private String branch_name;
    private String fname;
    private String email;
    private String password;
    private String id;

    private BranchManager(Builder builder) {
        this.id = builder.id;
        this.branch_name = builder.branch_name;
        this.city_id = builder.city_id;
        this.fname = builder.fname;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static class Builder {

        private int city_id;
        private String branch_name;
        private String fname;
        private String email;
        private String password;
        private String id;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setCity_id(int city_id) {
            this.city_id = city_id;
            return this;
        }

        public Builder setBranch_name(String branch_name) {
            this.branch_name = branch_name;
            return this;
        }

        public Builder setFName(String fname) {
            this.fname = fname;
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

        public BranchManager build() {
            return new BranchManager(this);
        }

        public Builder setFname(String fname) {
            this.fname = fname;
            return this;
        }

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return branch_name;
    }

    public String getFName() {
        return fname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getCityId() {
        return city_id;
    }
}
