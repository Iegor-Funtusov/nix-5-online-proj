package ua.com.alevel.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

//@Deprecated
public class SetCrudService<E extends BaseEntity>
        implements CrudService<E> {

    private Set<E> entities = new HashSet<>();

    @Override
    public void create(E e) {
        e.setId(generateId());
        entities.add(e);
    }

    private String generateId(){
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (!isIdValid(id));
        return id;
    }

    private boolean isIdValid(String id){
        for (E e : entities) {
            if (e.getId().equals(id))
                return false;
        }
        return true;
    }

    @Override
    public void update(E e) {
        if(StringUtils.isNotBlank(e.getId())) {
            E currentE = getEById(e.getId());
            if (currentE == null)
                throw new RuntimeException("entity is not exist");
            try {
                BeanUtils.copyProperties(currentE, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        }else {
            throw new RuntimeException("entity is not exist");
        }
    }

    private E getEById(String id) {
        for (E e : entities)
            if(e.getId().equals(id))
                return e;
        return null;
    }

    @Override
    public void delete(String id) {
        if(StringUtils.isNotBlank(id)) {
            E currentE = getEById(id);
            if (currentE == null) {
                throw new RuntimeException("entity is not exist");
            }
            for (E e : entities) {
                if(e.getId().equals(id))
                    entities.remove(e);
            }
        }else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public E read(String id) {
        if(StringUtils.isNotBlank(id)) {
            E currentE = getEById(id);
            if (currentE == null) {
                throw new RuntimeException("entity is not exist");
            }
            return currentE;
        }else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public Set<E> read() {
        return entities;
    }
}
