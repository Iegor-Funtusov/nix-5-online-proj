package ua.com.alevel.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Deprecated
public class ListCrudProcess<E extends BaseEntity> implements CrudProcess<E> {

    private final List<E> list = new ArrayList<>();

    public void create(E e) {
        e.setId(generateId(UUID.randomUUID().toString()));
        list.add(e);
    }

    public void update(E e) {
        if(StringUtils.isNotBlank(e.getId())){
            E current = getEById(e.getId());
            if(current == null){
                throw new RuntimeException("entity is not exist");
            }
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    public void delete(String id) {
        if(StringUtils.isNotBlank(id)){
            E current = getEById(id);
            if(current == null){
                throw new RuntimeException("entity is not exist");
            }
            list.removeIf(e -> e.getId().equals(id));
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    public Collection<E> read() {
        return list;
    }

    public E read(String id) {
        if(StringUtils.isNotBlank(id)){
            E current = getEById(id);
            if(current == null){
                throw new RuntimeException("entity is not exist");
            }
            return current;
        }
        throw new RuntimeException("entity is not exist");
    }

    private E getEById(String id){
        return list.stream()
                .filter(e -> id.equals(e.getId()))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if(list.stream().anyMatch(e -> e.getId().equals(id))){
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
