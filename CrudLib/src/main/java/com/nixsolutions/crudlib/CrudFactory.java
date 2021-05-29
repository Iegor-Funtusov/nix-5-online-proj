package com.nixsolutions.crudlib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudFactory {

    private static CrudFactory instance;
    private static final String ROOT_PACKAGE = "com.nixsolutions.crudlib";
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

    public CrudService getCrudService() {
        Set<Class<? extends CrudService>> crudService = crudServiceList.stream()
                .filter(aClass -> !aClass.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (crudService.size() != 1) {
            throw new RuntimeException(crudService + "Singleton Factory has > 1 instance");
        }

        try {
            return crudService.iterator().next().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Can't create new Factory instance");
        }
    }
}
