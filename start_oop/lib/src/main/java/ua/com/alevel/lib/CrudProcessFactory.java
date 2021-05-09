package ua.com.alevel.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudProcessFactory {

    private static CrudProcessFactory instance;
    private static final String ROOT_PACKAGE = "ua.com.alevel.lib";

    private final Set<Class<? extends CrudProcess>> crudProcessSet;

    private CrudProcessFactory(){
        Reflections reflections = new Reflections(ROOT_PACKAGE);
        crudProcessSet = reflections.getSubTypesOf(CrudProcess.class);
    }

    public static CrudProcessFactory getInstance(){
        if(instance == null){
            instance = new CrudProcessFactory();
        }
        return instance;
    }

    public CrudProcess getCrudProcess(){

        Set<Class<? extends  CrudProcess>> classes = crudProcessSet
                .stream()
                .filter(crudProcessImpl -> !crudProcessImpl.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if(classes.size() > 1){
            throw new RuntimeException("more than one implementation");
        }

        for(Class<? extends CrudProcess> crudProcessImpl : crudProcessSet){
            if(!crudProcessImpl.isAnnotationPresent(Deprecated.class)){
                try {
                    return crudProcessImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException
                        | IllegalAccessException
                        | InvocationTargetException
                        | NoSuchMethodException e) {
                    throw new RuntimeException("can`t create implementation");
                }
            }
        }
        throw new RuntimeException("can`t create implementation");
    }
}
