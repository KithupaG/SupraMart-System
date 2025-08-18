package lk.supramart.model;

/**
 *
 * @author Shenu
 */
public class Auditor {

    private int id;
    private String name;
    private String email;
    private String password;

    public Auditor(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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


    @Override
    public String toString() {
        return "Auditor{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
