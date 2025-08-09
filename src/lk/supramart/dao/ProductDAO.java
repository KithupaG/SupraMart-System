package lk.supramart.dao;

import java.util.List;
import lk.supramart.model.Product;

public interface ProductDAO {

    boolean addProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);

}
