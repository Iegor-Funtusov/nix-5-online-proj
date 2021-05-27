package com.k4rnaj1k.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.lang.model.UnknownEntityException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Deprecated
public class ArrayCrudProcess<E extends BaseEntity> implements CrudProcess<E> {
    private Object[] arr = new Object[0];
    private int size;

    public void create(E e) {
        e.setId(generateId(UUID.randomUUID().toString()));
        arr = Arrays.copyOf(arr, arr.length+1);
        arr[size++] = e;
    }

    public Set read() {
        return Arrays.stream(arr).collect(Collectors.toSet());
    }

    public E read(String id) throws Exception {
        for (int i = 0; i < arr.length; i++) {
            if(((E) arr[i]).getId().equals(id)){
                return (E) arr[i];
            }
        }
        throw new Exception("Item not found");
    }

    public ArrayCrudProcess() {
        System.out.println("ArrayCrudProcess");
    }

    public void update(E e) {
        if (StringUtils.isNotBlank(e.getId())) {
            E current = getEById(e.getId());
            if (current == null) {
                throw new RuntimeException("Entity doesn't exist.");
            }
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessError | InvocationTargetException | IllegalAccessException ex) {
                throw new RuntimeException("Entity doesn't exist.");
            }
        } else {
            throw new RuntimeException("Entity doesn't exist.");
        }
    }

    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEById(id);
            if (current == null) {
                throw new RuntimeException("entity is not exist");
            }
            for (int i = 0; i < arr.length; i++) {
                if (((E) arr[i]).getId().equals(id)) {
                    arr[i] = null;
                    break;
                }
            }
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    private E getEById(String id) {
        return (E) Arrays.stream(arr)
                .filter(e -> id.equals(((E) e).getId()))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if (Arrays.stream(arr).anyMatch(e -> ((E) e).getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

}
