package ua.com.alevel.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class CrudServiceFactory {

    private static CrudServiceFactory instance;
    private static final String ROOT_PACKAGE = "ua.com.alevel.lib";

    private final Set<Class<? extends CrudService>> crudServicesSet;

    private CrudServiceFactory(){
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        crudServicesSet = reflections.getSubTypesOf(CrudService.class);
    }

    public static CrudServiceFactory getInstance(){
        if (instance == null) {
            instance = new CrudServiceFactory();
        }
        return instance;
    }

    public CrudService getCrudService(){
        Set<Class<? extends CrudService>> classes = new HashSet<>();
        for (Class<? extends CrudService> crudServiceImpl : crudServicesSet) {
            if(!crudServiceImpl.isAnnotationPresent(Deprecated.class))
                classes.add(crudServiceImpl);
        }
        if(classes.size() != 1)
            throw new RuntimeException("Invalid number of implementations(must be one)!");
        for (Class<? extends CrudService> crudServiceImpl : classes) {
            if(!crudServiceImpl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return crudServiceImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException
                        | IllegalAccessException
                        | InvocationTargetException
                        | NoSuchMethodException e) {
                    throw new RuntimeException("Can't create implementation!");
                }
            }
        }
        throw new RuntimeException("Can't create implementation!!");
    }

}
