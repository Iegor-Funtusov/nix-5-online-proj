package com.nixsolutions.courses.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

//@Active
public class ObjectCrudProcess<E extends BaseEntity> implements CrudProcess<E> {

    private final int STORAGE_CAPACITY = 10;
    private final Object[] array = new Object[STORAGE_CAPACITY];

    public ObjectCrudProcess() {
        System.out.println("ObjectCrudProcess");
    }

    public void create(E e) {
        e.setId(generateId(UUID.randomUUID().toString()));
        for (int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                array[i] = e;
                break;
            }
        }
    }

    public void update(E e) {
        if(StringUtils.isNotBlank(e.getId())) {
            E current = getById(e.getId());
            if(current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public void delete(String id) {
        if(StringUtils.isNotBlank(id)) {
            E current = getById(id);
            if(current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            for(int i = 0; i < array.length; i++) {
                if(((E)array[i]).getId().equals(id)) {
                    array[i] = null;
                }
            }
//            list.remove(current);
        } else {
            throw new RuntimeException("Entity does not exist");
        }

    }

    public E read(String id) {
        if(StringUtils.isNotBlank(id)) {
            E current = getById(id);
            if(current == null) {
                throw new RuntimeException("Entity does not exist");
            }
            return current;
        } else {
            throw new RuntimeException("Entity does not exist");
        }
    }

    public List readAll() {
        return Arrays.stream(array).filter(i -> Objects.nonNull(i)).collect(Collectors.toList());
    }

    private E getById(String id) {
        return (E) Arrays.stream(array)
                .filter(e -> ((E)e).getId().equals(id))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if(Arrays.stream(array).filter(i -> Objects.nonNull(i)).anyMatch(e -> ((E)e).getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

}
