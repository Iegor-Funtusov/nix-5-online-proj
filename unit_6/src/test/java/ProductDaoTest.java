import manufacturers.Manufacturer;
import manufacturers.ManufacturerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductService;

import java.util.Collection;

public class ProductDaoTest {
    private static ProductService service = new ProductService();
    private static ManufacturerService serviceManuf = new ManufacturerService();
    private static boolean flag;

    @BeforeAll
    public static void set(){
        for (int i = 0; i < 100; i++){
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName("Product" + i);
            serviceManuf.create(manufacturer);
            Product product = new Product();
            product.setName("Product" + i);
            product.setPrice(0.15+i);
            product.setManufId(manufacturer.getId());
            product.setManufName(manufacturer.getName());
            service.create(product);
        }
        Assertions.assertTrue(service.find().size() != 0);
    }

    @Test
    @Order(1)
    public void create() {
        Product product = new Product();
        product.setName("Product");
        product.setPrice(1);
        product.setManufId("id");
        product.setManufName("NameName");
        service.create(product);

        Collection<Product> list = service.find();
        flag = read("Product");
        Assertions.assertTrue(list.size() != 0 && flag == true);
    }

    @Test
    @Order(5)
    public void findAll() {
        Collection<Product> products = service.find();
        Assertions.assertTrue(products.size() != 0);
    }

    @Test
    @Order(2)
    public void update(){
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals("Product48")) {
                product.setName("Product101");
                service.update(product);
                break;
            }
        }
        flag = read("Product101");
        Assertions.assertTrue(list.size() != 0  && flag == true);
    }

    @Test
    @Order(3)
    public void delete(){
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals("Product101")) {
                service.delete(product.getId());
                break;
            }
        }
        flag = read("Product101");
        Assertions.assertTrue( flag == false);
    }

    @Test
    @Order(6)
    public void readProducts(){
        flag = false;
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals("Product")) {
                flag = true;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }

    private static boolean read(String name){
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
