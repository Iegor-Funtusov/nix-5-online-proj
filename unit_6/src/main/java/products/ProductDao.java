package products;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductDao {
    private final static int SIZE = 100;
    private static Object[] objects = new Object[SIZE];

    private static final Logger loggerWarnPr = LoggerFactory.getLogger("warnProduct");
    private static final Logger loggerErrorPr = LoggerFactory.getLogger("errorProduct");

    public static void create(Product product){
        resize();
        product.setId(generateId(UUID.randomUUID().toString()));
        for (int k = 0; k < objects.length; k++) {
            if (objects[k] == null) {
                objects[k] = product;
                break;
            }
        }
    }

    public void update(Product product) {
        if (StringUtils.isNotBlank(product.getId())) {
            Product current = getById(product.getId());
            if (current == null) {
                loggerErrorPr.error("Product wasn't found (id = null)");
                throw new RuntimeException("There is no such product");
            }
            try {
                BeanUtils.copyProperties(current, product);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            loggerErrorPr.error("Product wasn't found(id doesn't exist)");
            throw new RuntimeException("There is no such product");
        }
    }

    public static void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            Product current = getById(id);
            if (current == null) {
                loggerErrorPr.error("Product wasn't found (id = null)");
                throw new RuntimeException("There is no such product");
            }
            for(int k = 0; k < objects.length; k++) {
                if(((Product)objects[k]).getId().equals(id)) {
                    objects[k] = null;
                    break;
                }
            }
        } else {
            loggerErrorPr.error("Product wasn't found(id doesn't exist)");
            throw new RuntimeException("There is no such product");
        }
    }

    public List list() {
        return Arrays.stream(objects).filter(e -> Objects.nonNull(e)).collect(Collectors.toList());
    }

    public Product read(String id) {
        if (StringUtils.isNotBlank(id)) {
            Product current = getById(id);
            if (current == null) {
                loggerErrorPr.error("Product wasn't found (id = null)");
                throw new RuntimeException("There is no such product");
            }
            return current;
        }
        loggerErrorPr.error("Product wasn't found(id doesn't exist)");
        throw new RuntimeException("There is no such product");
    }


    private static String generateId(String id) {
        if(Arrays.stream(objects).filter(product -> Objects.nonNull(product)).anyMatch(product -> ((Product)product).getId().equals(id))) {
            loggerWarnPr.warn("Such id already exists");
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

    private static void resize(){
        if(objects[objects.length-1] != null){
            objects = Arrays.copyOf(objects, objects.length*2);
        }
    }

    private static Product getById(String id) {
        return (Product) Arrays.stream(objects)
                .filter(product -> ((Product)product).getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
