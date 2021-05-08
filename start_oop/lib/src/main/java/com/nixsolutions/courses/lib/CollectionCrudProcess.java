package com.nixsolutions.courses.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Active
public class CollectionCrudProcess<E extends BaseEntity> implements CrudProcess<E> {

    private final Set<E> set = new HashSet<>();

    public CollectionCrudProcess() {
        System.out.println("CollectionCrudProcess");
    }

    public void create(E e) {
        e.setId(generateId(UUID.randomUUID().toString()));
        set.add(e);
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
            set.remove(current);
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

    public Set<E> readAll() {
        return set;
    }

    private E getById(String id) {
        return set.stream()
                .filter(e -> id.equals(e.getId()))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if(set.stream().anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

}
