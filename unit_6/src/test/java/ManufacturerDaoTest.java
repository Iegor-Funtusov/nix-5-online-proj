import manufacturers.Manufacturer;
import manufacturers.ManufacturerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import products.Product;
import products.ProductService;


import java.util.Collection;

public class ManufacturerDaoTest {
    private static ManufacturerService service = new ManufacturerService();
    private static ProductService serviceProduct = new ProductService();
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
    @Order(5)
    public void findAll() {
        Collection<Manufacturer> manufacturers = service.find();
        Assertions.assertTrue(manufacturers.size() != 0);
    }

    @Test
    @Order(2)
    public void update(){
        Collection<Manufacturer> list = service.find();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals("Name48")) {
                manufacturer.setName("NameN");
                service.update(manufacturer);
                break;
            }
        }
        flag = read("NameN");
        Assertions.assertTrue(list.size() != 0 && flag == true);
    }

    @Test
    @Order(3)
    public void delete(){
        Collection<Manufacturer> list = service.find();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals("Name101")) {
                service.delete(manufacturer.getId());
                break;
            }
        }
        flag = read("Name101");
        Assertions.assertTrue( flag == false);
    }

    @Test
    @Order(6)
    public void readProducts(){
        Collection<Manufacturer> list = service.find();

        Collection<Product> list1 = serviceProduct.find();
        System.out.println(list1);
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals("Name10")) {
                for(Product product : list1){
                    if(product.getManufId().equals(manufacturer.getId())){
                        System.out.println(serviceProduct.read(product.getId()));
                    }
                }
                break;
            }
        }
    }

    private static boolean read(String name){
        Collection<Manufacturer> list = service.find();
        for (Manufacturer manufacturer : list) {
            if (manufacturer.getName().equals(name)) {
                System.out.println(manufacturer.getName());
                return true;
            }
        }
        return false;
    }
}
