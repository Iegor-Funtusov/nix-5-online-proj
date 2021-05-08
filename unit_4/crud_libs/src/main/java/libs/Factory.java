package libs;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class Factory {
    private static Factory instance;
    private static final String ROOT = "libs";

    private final Set<Class<? extends CrudProcess>> crudProcessSet;

    private Factory()
    {
        Reflections reflections = new Reflections(ROOT);
        crudProcessSet = reflections.getSubTypesOf(CrudProcess.class);
    }
    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public CrudProcess getCrudProcess() {

        Set<Class<? extends CrudProcess>> classes = crudProcessSet
                .stream()
                .filter(crudProcessImpl -> !crudProcessImpl.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (classes.size() != 1) {
            throw new RuntimeException("more then one implementation");
        }

        for (Class<? extends CrudProcess> crudProcessImpl : crudProcessSet) {
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
