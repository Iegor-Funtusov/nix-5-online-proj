package lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudProcessFactory {
    private static CrudProcessFactory instance;
    private static final String ROOT_PACKAGE = "lib";

    private final Set<Class<? extends CrudProcess>> crudProcesses;

    private CrudProcessFactory() {
       Reflections reflections = new Reflections(ROOT_PACKAGE);
       crudProcesses = reflections.getSubTypesOf(CrudProcess.class);
    }

    public static CrudProcessFactory getInstance() {
        if (instance == null) {
            instance = new CrudProcessFactory();
        }
        return instance;
    }

    public CrudProcess getCrudProcess() {
        Set<Class<? extends CrudProcess>> classes = crudProcesses
             .stream()
             .filter(crudProcessImpl -> !crudProcessImpl.isAnnotationPresent(Deprecated.class))
             .collect(Collectors.toSet());

        if (classes.size() == 0 || classes.size() > 1) {
            throw new RuntimeException("More than one implementation!");
        }

        for (Class<? extends CrudProcess> crudProcessImpl :
             crudProcesses) {
            if (!crudProcessImpl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return crudProcessImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException
                    | NoSuchMethodException
                    | InvocationTargetException
                    | IllegalAccessException e) {
                  throw new RuntimeException("Can't create implementation!");
                }
            }
        }
        throw new RuntimeException("Can't create implementation!");
    }
}
