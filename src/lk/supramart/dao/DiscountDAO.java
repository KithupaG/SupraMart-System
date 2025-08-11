package lk.supramart.dao;

import lk.supramart.model.Discount;
import java.util.List;

/**
 *
 * @author Sanduni PC
 */
public interface DiscountDAO {
    void addDiscount(Discount discount) throws Exception;
    void updateDiscount(Discount discount) throws Exception;
    void deleteDiscount(int discountId)throws Exception;
    Discount getDiscountById(int discountId) throws Exception;
    List<Discount> getAllDiscounts() throws Exception;
}

