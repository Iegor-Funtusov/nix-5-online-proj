package ua.com.nkrasnovoronka.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudProcessorFactory {
    private static CrudProcessorFactory instance;
    private static Set<Class<? extends Crud>> classSet;

    public CrudProcessorFactory() {
        Reflections reflections = new Reflections("ua.com.nkrasnovoronka");
        classSet = reflections.getSubTypesOf(Crud.class);
    }

    public static CrudProcessorFactory getInstance() {
        if (instance == null) {
            return new CrudProcessorFactory();
        }
        return instance;
    }

    public Crud getCrudProcessorImpl() {
        Set<Class<? extends Crud>> crudImpl = classSet.stream()
                .filter(aClass -> !aClass.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (crudImpl.size() != 1) {
            throw new RuntimeException(crudImpl + "has 0 or more than 1 implementation");
        }

        try {
            return crudImpl.iterator().next().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
           e.printStackTrace();
        }
        return null;
    }
}
