package lk.supramart.controller;

import java.sql.SQLException;
import lk.supramart.dao.DiscountDAO;
import lk.supramart.dao.DiscountDAOImpl;
import lk.supramart.model.Discount;

import java.util.List;
/**
 *
 * @author Sanduni PC
 */
public class DiscountController {
   private final DiscountDAO discountDAO;

    public DiscountController() throws SQLException {
        this.discountDAO = new DiscountDAOImpl();
    }

    public void addDiscountt(Discount discount) throws Exception {
        discountDAO.addDiscount(discount);
    }
    
    public void updateDiscount(Discount discount) throws Exception {
        discountDAO.updateDiscount(discount);
    }
    
    public void deleteDiscount(int discountId) throws Exception {
        discountDAO.deleteDiscount(discountId);
    }
    public Discount getDiscountById(int discountId) throws Exception {
        return discountDAO.getDiscountById(discountId);
    }

    public List<Discount> getAllDiscounts() throws Exception {
        return discountDAO.getAllDiscounts();
    }   
}
