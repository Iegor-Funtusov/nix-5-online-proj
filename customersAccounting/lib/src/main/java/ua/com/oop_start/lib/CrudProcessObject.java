package ua.com.oop_start.lib;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.apache.commons.beanutils.BeanUtils.copyProperties;

@Deprecated
public class CrudProcessObject<E extends BaseEntity> implements ICrudProcess <E>{
    private final int size = 100;
    private final Object[] objects = new Object[size];
    private int position;

    public void create(E e){
    e.setId(generateId(UUID.randomUUID().toString()));
        objects[position++] = e;
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

    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEbyId(id);
            if (current == null) {
                throw new RuntimeException("Can not delete entity - it does not exist");
            }
            for(int i = 0; i < position; i++) {
                if(((E)objects[i]).getId().equals(id)) {
                    System.arraycopy(objects, i+1, objects, i, position-i-1);
                   /* System.out.println(i);
                    System.out.println(position-i-1);
                    System.out.println(objects[i]);*/
                    System.out.println("Enter");
                }
            }
        } else {
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
            throw new RuntimeException("Can not read gentity - it does not exist");
        }
    }

    public Set<E> readAll(){
        Set<E> collect = new HashSet<>();
        for (Object object : objects) {
            if (object!=null){
            collect.add((E) object);
            }
        }
        return collect;
    }

    private E getEbyId(String id) {
        E entity = (E) Arrays.stream(objects)
                .filter(e -> ((E)e).getId().equals(id))
                .findAny()
                .orElse(null);

        return entity;
    }

    private String generateId (String id){
        if(Arrays.stream(objects)
                .filter(Objects::nonNull)
                .anyMatch(e -> ((E)e).getId().equals(id))){
        return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
