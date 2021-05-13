import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;



public class CrudProcFactory {

    private static CrudProcFactory instance;
    private static final String ROOT_PACKAGE = "";

    private final Set<Class<? extends CrudInt>> crudProcessSet;

    private CrudProcFactory() {
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        crudProcessSet = reflections.getSubTypesOf(CrudInt.class);
    }

    public static CrudProcFactory getInstance() {
        if (instance == null) {
            instance = new CrudProcFactory();
        }
        return instance;
    }

    public CrudInt getCrudProcess() {

        Set<Class<? extends CrudInt>> classes = crudProcessSet
                .stream()
                .filter(crudProcessImpl -> !crudProcessImpl.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (classes.size() == 0 || classes.size() > 1) {
            throw new RuntimeException("more then one implementation");
        }

        for (Class<? extends CrudInt> crudProcessImpl : crudProcessSet) {
            if (!crudProcessImpl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return crudProcessImpl.getDeclaredConstructor().newInstance();
                } catch (
                        InstantiationException |
                                IllegalAccessException |
                                InvocationTargetException |
                                NoSuchMethodException e) {
                    throw new RuntimeException("can not create impl");
                }
            }
        }
        throw new RuntimeException("can not create impl");
    }
}