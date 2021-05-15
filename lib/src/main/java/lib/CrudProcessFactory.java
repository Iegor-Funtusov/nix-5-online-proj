package lib;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

public class CrudProcessFactory {
        private static CrudProcessFactory instanse;
        private final Reflections reflections;
        private final Set<Class<? extends CrudProcess>> crudProcessesSet;
        private final String ROOT_PACKAGE = "lib";

        private CrudProcessFactory() {
            reflections = new Reflections(ROOT_PACKAGE);
            crudProcessesSet = reflections.getSubTypesOf(CrudProcess.class);
        }

        public  static CrudProcessFactory getInstance(){
            if(instanse == null){
                instanse = new CrudProcessFactory();
            }
            return instanse;
        }

        public CrudProcess getCrudProcess(){
            Set<Class<? extends CrudProcess>> classes = crudProcessesSet
                    .stream()
                    .filter(CrudProcessImpl -> !CrudProcessImpl.isAnnotationPresent(Deprecated.class))
                    .collect(Collectors.toSet());

            if(classes.size() == 0){
                throw new RuntimeException("there is no implementation");
            }

            if(classes.size() > 1){
                throw new RuntimeException("more than one implementation");
            }


            for (Class<? extends CrudProcess> CrudProcessImpl : crudProcessesSet) {
                if(!CrudProcessImpl.isAnnotationPresent(Deprecated.class)){
                    try {
                        return CrudProcessImpl.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException("can not create new impl");
                    }
                }
            }

            throw new RuntimeException("can not create new impl");
        }
}
