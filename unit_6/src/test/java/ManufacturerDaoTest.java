import manufacturers.Manufacturer;
import manufacturers.ManufacturerService;
import org.junit.jupiter.api.*;
import products.Product;
import products.ProductService;


import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManufacturerDaoTest {
    private final static ManufacturerService service = new ManufacturerService();
    private final static ProductService serviceProduct = new ProductService();
    private static boolean flag;

    @BeforeAll
    public static void set(){
        for (int i = 0; i < 100; i++){
            Manufacturer manufacturer = new Manufacturer();
            manufacturer.setName("Name" + i);
            service.create(manufacturer);
        }
        Assertions.assertTrue(service.find().size() != 0);
    }

    @Test
    @Order(1)
    public void create() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName("Name");
        service.create(manufacturer);

        Collection<Manufacturer> list = service.find();
        flag = read("Name");
        Assertions.assertTrue(list.size() != 0 && flag == true);
    }


    @Test
    @Order(2)
    public void update(){
        Collection<Manufacturer> list = service.find();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals("Name48")) {
                manufacturer.setName("UPDATED");
                service.update(manufacturer);
                break;
            }
        }
        flag = read("UPDATED");
        Assertions.assertTrue(list.size() != 0 && flag == true);
    }

    @Test
    @Order(3)
    public void delete(){
        Collection<Manufacturer> list = service.find();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals("Name")) {
                service.delete(manufacturer.getId());
                break;
            }
        }
        flag = read("Name");
        Assertions.assertTrue( flag == false);
    }

    @Test
    @Order(4)
    public void findAll() {
        Collection<Manufacturer> manufacturers = service.find();
        Assertions.assertTrue(manufacturers.size() != 0);
    }


    @Test
    @Order(5)
    public void readProducts(){
        Collection<Manufacturer> list = service.find();
        Collection<Product> list1;
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals("UPDATED")) {
                Product product1 = new Product();
                product1.setManufName("UPDATED");
                product1.setManufId(manufacturer.getId());
                product1.setPrice(10);
                product1.setName("Milk");
                serviceProduct.create(product1);
                list1 = serviceProduct.find();
                for(Product product : list1){
                    if(product.getManufId().equals(manufacturer.getId())){
                        serviceProduct.read(product.getId());
                    }
                }
                Assertions.assertTrue( list1.size() != 0);
                break;
            }
        }
    }

    @Test
    @Order(6)
    public void ManufacturerCreateNull(){
        assertThrows(NullPointerException.class, () -> service.create(null));
    }

    @Test
    @Order(7)
    public void ManufacturerDeleteNull(){
        assertThrows(RuntimeException.class, () -> service.delete(null));
    }

    @Test
    @Order(8)
    public void ManufacturerDeleteEmpty(){
        assertThrows(RuntimeException.class, () -> service.delete(""));
        assertThrows(RuntimeException.class, () -> service.delete(" "));
    }

    @Test
    @Order(9)
    public void ManufacturerUpdateNull(){
        assertThrows(NullPointerException.class, () -> service.update(null));
    }


    @Test
    @Order(10)
    public void ManufacturerReadNull(){
        assertThrows(RuntimeException.class, () -> service.read(null));
    }

    @Test
    @Order(11)
    public void ManufacturerReadEmpty(){
        assertThrows(RuntimeException.class, () -> service.read(""));
        assertThrows(RuntimeException.class, () -> service.read(" "));
    }

    private static boolean read(String name){
        Collection<Manufacturer> list = service.find();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
