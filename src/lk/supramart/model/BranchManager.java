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

    private int id, city_id;
    private String branch_name;

    private BranchManager(Builder builder) {
        this.id = builder.id;
        this.branch_name = builder.branch_name;
        this.city_id = builder.city_id;
    }

    public static class Builder {

        private int id, city_id;
        private String branch_name;

        public Builder setId(int id) {
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

        public BranchManager build() {
            return new BranchManager(this);
        }

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
