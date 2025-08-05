/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.supramart.model;

/**
 *
 * @author kithu
 */
public class Supplier {
    private int id;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    
    public Supplier(int id, String name, String contactName, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
