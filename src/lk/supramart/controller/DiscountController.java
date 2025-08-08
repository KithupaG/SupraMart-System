package lk.supramart.controller;

import lk.supramart.dao.DiscountDAO;
import lk.supramart.dao.DiscountDAOImpl;
import lk.supramart.model.Discount;

import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Sanduni PC
 */
public class DiscountController {
   private DiscountDAO discountDAO;
   
   public DiscountController(){
       this.discountDAO = new DiscountDAOImpl();
   }
   
   public void createSampleDiscounts
}
