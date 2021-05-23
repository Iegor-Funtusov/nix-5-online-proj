package manufacturers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class ManufacturerService {
    private static final Logger loggerInfo = LoggerFactory.getLogger("infoManufacturer");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warnManufacturer");

    private static ManufacturerDao manufacturerDao = new ManufacturerDao();

    public static void create (Manufacturer manufacturer){
        loggerInfo.info("Adding the manufacturer: " + manufacturer.getName());
        manufacturerDao.create(manufacturer);
        loggerInfo.info("Manufacturer was added");
    }

    public static void update(Manufacturer manufacturer){
        loggerInfo.info("Updating the manufacturer: " + manufacturer.getName());
        manufacturerDao.update(manufacturer);
        loggerInfo.info("Updating was ended");
    }


    public static void delete(String id) {
        loggerWarn.warn("Start removing of manufacturer by id: " + id);
        manufacturerDao.delete(id);
        loggerWarn.warn("End removing of manufacturer");
    }

    public Collection<Manufacturer> find(){
        loggerInfo.info("Find all manufacturers");
        return manufacturerDao.list();
    }

    public Manufacturer read(String id){
        loggerInfo.info("Find all products of manufacturer by id" + id);
        return manufacturerDao.read(id);
    }
}
