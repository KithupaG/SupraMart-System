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
    CASHIER(1), BRANCH_MANAGER(5), AUDITOR(3), INVENTORY_MANAGER(4), ADMIN(2);

    private final int id;

    UserRole(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    
    public String getRolePrefix(int id) {
        switch (id) {
            case 1:
                return "CASH";
            case 2:
                return "ADMI";
            case 3:
                return "AUDI";
            case 4:
                return "IMAN"; // I + MAN(ager)
            case 5:
                return "BMAN"; // B + MAN(ager)
            default:
                return "UNKN";
        }
    }
}