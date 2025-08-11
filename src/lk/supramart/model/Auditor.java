package lk.supramart.model;

import lk.supramart.enums.AuditorRole;

/**
 *
 * @author Shenu
 */
public class Auditor {

    private int id;
    private String name;
    private String email;
    private String password;
    private AuditorRole role; // Enum

    public Auditor(int id, String name, String email, String password) {
    }

    public Auditor(int id, String name, String email, String password, AuditorRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AuditorRole getRole() {
        return role;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(AuditorRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Auditor{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", role=" + role
                + '}';
    }
}
