/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package lk.supramart.enums;

/**
 *
 * @author Yashitha
 */
public enum UserRole {
    CASHIER(1), BRANCH_MANAGER(2), AUDITOR(3), INVENTORY_MANAGER(4);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}