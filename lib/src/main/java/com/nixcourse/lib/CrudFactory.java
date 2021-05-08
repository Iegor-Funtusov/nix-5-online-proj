package com.nixcourse.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudFactory {

    private static CrudFactory instance;
    private static final String ROOT_PACKAGE = "com.nixcourse.lib";
    private final Set<Class<? extends ICrudCollectionService>> crudServiceSet;

    private CrudFactory() {
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        crudServiceSet = reflections.getSubTypesOf(ICrudCollectionService.class);
    }

    public static CrudFactory getInstance() {
        if (instance == null) {
            instance = new CrudFactory();
        }
        return instance;
    }

    public ICrudCollectionService getCrudCollectionService() {

        Set<Class<? extends ICrudCollectionService>> classes = crudServiceSet
                .stream()
                .filter(crudServiceImpl -> !crudServiceImpl.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (classes.size() != 1) {
            if (classes.size() == 0) {
                throw new RuntimeException("There is no implementation");
            } else {
                throw new RuntimeException("There is more then one implementation");
            }
        }

        for (Class<? extends ICrudCollectionService> crudServiceImpl : crudServiceSet) {
            if (!crudServiceImpl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return crudServiceImpl.getDeclaredConstructor().newInstance();
                } catch (
                        InstantiationException |
                                IllegalAccessException |
                                InvocationTargetException |
                                NoSuchMethodException e) {
                    throw new RuntimeException("Cannot create implementation");
                }
            }
        }
        throw new RuntimeException("Cannot create implementation");
    }
}
