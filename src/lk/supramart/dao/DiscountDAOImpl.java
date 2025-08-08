package lk.supramart.dao;

import lk.supramart.model.Discount;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sanduni PC
 */
public class DiscountDAOImpl implements DiscountDAO {

    private final List<Discount> discountList = new ArrayList<>();
    
    public void addDiscount(Discount discount) {
        discountList.add(discount);
    }

    public void updateDiscount(Discount discount) {
        for (int i = 0; i < discountList.size(); i++){
            if (discountList.get(i).getId() == discount.getId()){
                discountList.set(i, discount);
                break;
            }
        }
    }

    public void deleteDiscount(int id) {
        discountList.removeIf(discount -> discount.getId() == id);
    }

    public Discount getDiscountById(int id) {
        return discountList.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public List<Discount> getAllDiscounts() {
        return discountList;
    }

    public List<Discount> getActiveDiscounts() {
        List<Discount> active = new ArrayList<>();
        for (Discount d : discountList){
            if (d.isActive()){
                active.add(d);
            }
        }
        return active;
    }
    
}
