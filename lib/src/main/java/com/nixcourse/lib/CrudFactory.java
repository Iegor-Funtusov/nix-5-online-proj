package com.nixcourse.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudFactory {

    private static CrudFactory instance;
    private static final String ROOT_PACKAGE = "com.nixcourse.lib";
    private final Set<Class<? extends CrudCollectionService>> crudServiceSet;

    private CrudFactory() {
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        crudServiceSet = reflections.getSubTypesOf(CrudCollectionService.class);
    }

    public static CrudFactory getInstance() {
        if (instance == null) {
            instance = new CrudFactory();
        }
        return instance;
    }

    public CrudCollectionService getCrudCollectionService() {

        Set<Class<? extends CrudCollectionService>> classes = crudServiceSet
                .stream()
                .filter(crudProcessImpl -> !crudProcessImpl.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (classes.size() != 1) {
            throw new RuntimeException("more then one implementation");
        }

        for (Class<? extends CrudCollectionService> crudServiceImpl : crudServiceSet) {
            if (!crudServiceImpl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return crudServiceImpl.getDeclaredConstructor().newInstance();
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
