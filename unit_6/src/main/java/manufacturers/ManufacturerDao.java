package manufacturers;

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

public class ManufacturerDao {
    private final static int SIZE = 100;
    private static Object[] objects = new Object[SIZE];

    private static final Logger loggerWarn = LoggerFactory.getLogger("warnManufacturer");
    private static final Logger loggerError = LoggerFactory.getLogger("errorManufacturer");

    public static void create(Manufacturer manufacturer){
        resize();
        manufacturer.setId(generateId(UUID.randomUUID().toString()));
        for (int k = 0; k < objects.length; k++) {
            if (objects[k] == null) {
                objects[k] = manufacturer;
                break;
            }
        }
    }

    public void update(Manufacturer manufacturer) {
        if (StringUtils.isNotBlank(manufacturer.getId())) {
            Manufacturer current = getById(manufacturer.getId());
            if (current == null) {
                loggerError.error("Manufacturer wasn't found (id = null)");
                throw new RuntimeException("There is no such manufacturer");
            }
            try {
                BeanUtils.copyProperties(current, manufacturer);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            loggerError.error("Manufacturer wasn't found(id doesn't exist)");
            throw new RuntimeException("There is no such manufacturer");
        }
    }

    public static void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            Manufacturer current = getById(id);
            if (current == null) {
                loggerError.error("Manufacturer wasn't found (id = null)");
                throw new RuntimeException("There is no such manufacturer");
            }
            for(int k = 0; k < objects.length; k++) {
                if(((Manufacturer)objects[k]).getId().equals(id)) {
                    objects[k] = null;
                    break;
                }
            }
        } else {
            loggerError.error("Manufacturer wasn't found(id doesn't exist)");
            throw new RuntimeException("There is no such manufacturer");
        }
    }

    public List list() {
        return Arrays.stream(objects).filter(e -> Objects.nonNull(e)).collect(Collectors.toList());
    }

    public Manufacturer read(String id) {
        if (StringUtils.isNotBlank(id)) {
            Manufacturer current = getById(id);
            if (current == null) {
                loggerError.error("Manufacturer wasn't found (id = null)");
                throw new RuntimeException("There is no such manufacturer");
            }
            return current;
        }
        loggerError.error("Manufacturer wasn't found(id doesn't exist)");
        throw new RuntimeException("There is no such manufacturer");
    }


    private static String generateId(String id) {
        if(Arrays.stream(objects).filter(manufacturer -> Objects.nonNull(manufacturer))
                .anyMatch(manufacturer -> ((Manufacturer)manufacturer).getId().equals(id))) {
            loggerWarn.warn("Such id already exists");
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

    private static void resize(){
        if(objects[objects.length-1] != null){
            objects = Arrays.copyOf(objects, objects.length*2);
        }
    }

    private static Manufacturer getById(String id) {
        return (Manufacturer) Arrays.stream(objects)
                .filter(manufacturer -> ((Manufacturer)manufacturer).getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
