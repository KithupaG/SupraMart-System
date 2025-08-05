
import lk.supramart.dao.Product;
import lk.supramart.dao.ProductDAO;
import lk.supramart.dao.ProductDAOImpl;
import java.util.List;

/**
 *
 * @author nimuthu
 */
public class ProductController {
    private final ProductDAO productDAO;

    public ProductController() {
        this.productDAO = new ProductDAOImpl();
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
