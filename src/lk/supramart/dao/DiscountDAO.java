package lk.supramart.dao;

import lk.supramart.model.Discount;
import java.util.List;

/**
 *
 * @author Sanduni PC
 */
public interface DiscountDAO {
    void addDiscount(Discount discount);
    void updateDiscount(Discount discount);
    void deleteDiscount(int id);
    Discount getDiscountById(int id);
    List<Discount> getAllDiscounts();
    List<Discount> getActiveDiscounts();
}
