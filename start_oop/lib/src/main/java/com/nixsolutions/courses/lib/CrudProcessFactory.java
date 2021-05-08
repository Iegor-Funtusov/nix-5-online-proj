package com.nixsolutions.courses.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class CrudProcessFactory {

    private static CrudProcessFactory instance;
    private static final String ROOT_PACKAGE = "com.nixsolutions.courses.lib";

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

        for (Class<? extends CrudProcess> crudProcessImpl : crudProcesses) {
            if (crudProcessImpl.isAnnotationPresent(Active.class)) {
                try {
                    return crudProcessImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException |
                        IllegalAccessException |
                        InvocationTargetException |
                        NoSuchMethodException e) {
                    throw new RuntimeException("Unable to create impl");
                }
            }
        }
        throw new RuntimeException("Unable to create impl");
    }
}
