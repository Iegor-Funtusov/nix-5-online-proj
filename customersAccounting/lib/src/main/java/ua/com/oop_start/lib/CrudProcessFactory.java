package ua.com.oop_start.lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;


public class CrudProcessFactory {

    private final static String ROOT = "ua.com.oop_start.lib";
    private static CrudProcessFactory instance;
    private final Reflections reflections;
    private final Set<Class<? extends ICrudProcess>> crudProcesses;

    private CrudProcessFactory(){
        reflections = new Reflections(ROOT);
        crudProcesses = reflections.getSubTypesOf(ICrudProcess.class);
    }

    public static CrudProcessFactory getInstance(){
        if (instance == null){
         instance = new CrudProcessFactory();
        }
        return instance;
    }

    public ICrudProcess getCrudProcess(){

        Set<Class<? extends ICrudProcess>> classes = crudProcesses
                .stream()
                .filter(crudProcessImpl -> !crudProcessImpl.isAnnotationPresent(Deprecated.class))
                .collect(Collectors.toSet());

        if (classes.size() == 0) {
          throw new RuntimeException("without implementation");
        }

        for (Class<? extends ICrudProcess> crudProcessImpl : crudProcesses) {
            if (!crudProcessImpl.isAnnotationPresent(Deprecated.class)) {
                try {
                    return crudProcessImpl.getDeclaredConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new RuntimeException("can not create impl");
    }
 }

