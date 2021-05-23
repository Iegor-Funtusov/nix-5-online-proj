package products;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class ProductService {

    private static final Logger loggerInfoPr = LoggerFactory.getLogger("infoProduct");
    private static final Logger loggerWarnPr = LoggerFactory.getLogger("warnProduct");

    private static ProductDao productDao = new ProductDao();

    public static void create (Product product){
        loggerInfoPr.info("Adding the product: " + product.getName());
        productDao.create(product);
        loggerInfoPr.info("Product was added");
    }

    public static void update(Product product){
        loggerInfoPr.info("Updating the manufacturer: " + product.getName());
        productDao.update(product);
        loggerInfoPr.info("Updating was ended");
    }


    public static void delete(String id) {
        loggerWarnPr.warn("Start removing of product by id: " + id);
        productDao.delete(id);
        loggerWarnPr.warn("End removing of product");
    }

    public Collection<Product> find(){
        loggerInfoPr.info("Find all products");
        return productDao.list();
    }

    public Product read(String id){
        loggerInfoPr.info("Find product by id" + id);
        return productDao.read(id);
    }

}
