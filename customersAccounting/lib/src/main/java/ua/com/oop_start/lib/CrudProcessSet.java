package ua.com.oop_start.lib;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import static org.apache.commons.beanutils.BeanUtils.copyProperties;


public class CrudProcessSet<E extends BaseEntity> implements ICrudProcess <E>{

    private final Set<E> hashsetCustomers = new HashSet<>();

    public void create(E e){
    e.setId(generateId(UUID.randomUUID().toString()));
    hashsetCustomers.add(e);
    }

    public void update(E e){
    if(StringUtils.isNoneBlank(e.getId())){
                E current = getEbyId(e.getId());
            try {
                copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
     }
    else{
            throw new RuntimeException("Can not update entity - it does not exist");
        }
    }

    public void delete(String id){
        if(StringUtils.isNoneBlank(id)){
            E current = getEbyId(id);
            if(current == null){
                throw new RuntimeException("Can not delete entity - it does not exist");
            }
            hashsetCustomers.removeIf(e -> e.getId().equals(id));
        }
        else{
            throw new RuntimeException("Can not delete entity - it does not exist");
        }
    }

    public E read(String id){
        if(StringUtils.isNoneBlank(id)){
            E current = getEbyId(id);
            if(current == null){
                throw new RuntimeException("Can not read entity - it does not exist");
            }
             return current;
        }
        else{
            throw new RuntimeException("Can not update entity - it does not exist");
        }
    }

    public Set<E> readAll(){
        return hashsetCustomers;
    }


    private E getEbyId (String id){
       return hashsetCustomers.stream()
                .filter(el -> id.equals(el.getId()))
                .findAny()
                .orElse(null);
    }

    private String generateId (String id){
        if(hashsetCustomers.stream()
                .anyMatch(e -> e.getId().equals(id))){
        return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
