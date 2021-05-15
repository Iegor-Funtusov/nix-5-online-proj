package lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


public class LinkedListCrudProcess<E extends BaseEntity> implements CrudProcess<E> {
    private final List<E> list = new LinkedList<E>();

    public void create(E e){
        e.setId(generateID(UUID.randomUUID().toString()));
        list.add(e);
    }


    public void update(E e){
        if(StringUtils.isNotBlank(e.getId())){
            E current = getObjByID(e.getId());
            if (current == null){
                throw new RuntimeException("Entity is not exist");
            }
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("Entity is not exist");
        }
    }


    public void delete(String id){
        if(StringUtils.isNotBlank(id)){
            E current = getObjByID(id);
            if (current == null){
                throw new RuntimeException("Entity is not exist");
            }
            list.removeIf(e -> e.getId().equals(id));
        } else{
            throw new RuntimeException("Entity is not exist");
        }
    }


    public List<E> read(){
        return list;
    }

    public E read(String id){
        if(StringUtils.isNotBlank(id)){
            E current = getObjByID(id);
            if (current == null){
                throw new RuntimeException("Entity is not exist");
            }
            return current;
        }
        throw new RuntimeException("Entity is not exist");
    }

    private E getObjByID(String id){
        return list.stream()
                .filter(e -> id.equals(e.getId()))
                .findAny()
                .orElse(null);
    }

    private String generateID(String id){
        if(list.stream().anyMatch(e -> e.getId().equals(id))){
            return generateID(UUID.randomUUID().toString());
        }
        return id;
    }
}
