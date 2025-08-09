package lk.supramart.model;

import java.time.LocalDate;
/**
 *
 * @author Sanduni PC
 */
public class Discount {
    private int discountId;
    private String discountName;
    private double discountPercentage;
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Discount(){
    
    }
    
    public Discount(int discountId, String discountName, double discountPercentage, LocalDate startDate, LocalDate endDate){
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // Getters and Setters
    // toString(), equals(), hashCode() as needed

    public int getDiscountId(){
        return discountId;
    }
    
    public void setDiscountId(int DiscountId){
        this.discountId = discountId;
    }
    
    public String getDiscountName(){
        return discountName;
    }
    
    public void setDiscountName(String DiscountName){
        this.discountName = discountName;
    }
    
    public double getDiscountPercentage(){
        return discountPercentage;
    }
    
    public void setDiscountPercentage(){
        this.discountPercentage = discountPercentage;
    }
    
    public LocalDate getStartDate(){
        return startDate;
    }
    
    public void setStartDate(){
        this.startDate = startDate;
    }
    
    public LocalDate getEndDate(){
        return endDate;
    }
    
    public void setEndDate(){
        this.endDate = endDate;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
    return "Discount{" +
            "discountId='" + discountId + '\'' +
            ", discountName='" + discountName + '\'' +
            ", discountPercentage=" + discountPercentage +
            ", startDate='" + startDate + '\'' +
            ", endDate='" + endDate + '\'' +
            '}';
    }
    
}

