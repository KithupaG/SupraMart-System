package lk.supramart.dao;

import java.util.List;
import lk.supramart.dao.Product;

public interface ProductDAO {

    boolean addProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);

}
