package ua.com.threadedcode.dao;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CrudFactory {
    private static CrudFactory instance;
    private Reflections reflections;
    private final String REFLECTION_PREFIX = "ua.com.threadedcode.dao";
    private Set<Class<? extends ICrudProcess>> crudSet;

    private CrudFactory() {
        reflections = new Reflections(REFLECTION_PREFIX);
        crudSet = reflections.getSubTypesOf(ICrudProcess.class);
    }

    public static CrudFactory getInstance() {
        if (instance == null) {
            instance = new CrudFactory();
        }
        return instance;
    }

    public ICrudProcess getCrudProcess() {

        for (Class<? extends ICrudProcess> crudProcessImpl : crudSet) {
            if (!crudProcessImpl.isAnnotationPresent(Ignore.class)) {
                try {
                    return (ICrudProcess) crudProcessImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("can't create implementation");
                }
            }
        }
        throw new RuntimeException("can't create implementation");
    }
}
