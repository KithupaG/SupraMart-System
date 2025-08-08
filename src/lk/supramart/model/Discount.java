package lk.supramart.model;

import java.time.LocalDate;
import lk.supramart.enums.DiscountType;

/**
 *
 * @author Sanduni PC
 */
public class Discount {
    private int id;
    private String name;
    private DiscountType type;
    private double value;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    
    public Discount(int id, String name, DiscountType type, double value, LocalDate startDate, LocalDate endDate, boolean active){
        this.id = id;
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }
    
    // Getters and Setters
    // toString(), equals(), hashCode() as needed

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }
}
