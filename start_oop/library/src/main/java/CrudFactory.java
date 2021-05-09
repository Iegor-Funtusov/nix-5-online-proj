import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CrudFactory {

    private static final String ROOT_PACKAGE = "";
    private static CrudFactory instance;
    private Set<Class<? extends CrudService>> crudServiceList;

    private CrudFactory() {
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        crudServiceList = reflections.getSubTypesOf(CrudService.class);
    }

    public static CrudFactory getInstance() {
        if (instance == null) {
            instance = new CrudFactory();
        }
        return instance;
    }

    public CrudService getCurrent() {
        for (Class<? extends CrudService> aClass : crudServiceList) {
            if (!aClass.isAnnotationPresent(Deprecated.class)) {
                try {
                    return aClass.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("Not valid method for creating Factory. Check ArrayCrudService and ListCrudService files");
    }
}