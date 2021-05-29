import manufacturers.Manufacturer;
import manufacturers.ManufacturerService;
import org.junit.jupiter.api.*;
import products.Product;
import products.ProductService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductDaoTest {
    private final static ProductService service = new ProductService();
    private final static ManufacturerService serviceManuf = new ManufacturerService();
    private static boolean flag;

    @BeforeAll
    public static void set(){
        for (int i = 0; i < 100; i++){
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName("Name" + i);
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
    @Order(2)
    public void update(){
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals("Product5")) {
                product.setName("UPDATED");
                product.setPrice(0.01);
                service.update(product);
                break;
            }
        }
        flag = read("UPDATED");
        Assertions.assertTrue(list.size() != 0  && flag == true);
    }

    @Test
    @Order(3)
    public void delete(){
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals("Product12")) {
                service.delete(product.getId());
                break;
            }
        }
        flag = read("Product12");
        Assertions.assertTrue( flag == false);
    }

    @Test
    @Order(4)
    public void findAll() {
        Collection<Product> products = service.find();
        Assertions.assertTrue(products.size() != 0);
    }

    @Test
    @Order(5)
    public void readProducts(){
        flag = false;
        Collection<Product> list = service.find();
        for (Product product : list) {
            if (product.getName().equals("Product10")) {
                flag = true;
                break;
            }
        }
        Assertions.assertTrue(flag);
    }

    @Test
    @Order(6)
    public void ProductCreateNull(){
        assertThrows(NullPointerException.class, () -> service.create(null));

    }

    @Test
    @Order(7)
    public void ProductDeleteNull(){
        assertThrows(RuntimeException.class, () -> service.delete(null));
    }

    @Test
    @Order(8)
    public void ProductDeleteEmpty(){
        assertThrows(RuntimeException.class, () -> service.delete(""));
        assertThrows(RuntimeException.class, () -> service.delete(" "));
    }

    @Test
    @Order(9)
    public void ProductUpdateNull(){
        assertThrows(NullPointerException.class, () -> service.update(null));
    }

    @Test
    @Order(10)
    public void ProductReadNull(){
        assertThrows(RuntimeException.class, () -> service.read(null));
    }


    @Test
    @Order(11)
    public void ProductReadEmpty(){
        assertThrows(RuntimeException.class, () -> service.read(""));
        assertThrows(RuntimeException.class, () -> service.read(" "));
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
