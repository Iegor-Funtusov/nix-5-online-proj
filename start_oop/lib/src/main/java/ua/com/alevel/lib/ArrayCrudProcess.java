package ua.com.alevel.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

//@Deprecated
public class ArrayCrudProcess<E extends BaseEntity> implements CrudProcess<E> {

    private final int CAPACITY = 100;
    private final Object[] arr = new Object[CAPACITY];

    public ArrayCrudProcess() {
        System.out.println("ArrayCrudProcess");
    }

    public void create(E e) {
        if (e == null) {
            throw new IllegalArgumentException("entity is null");
        }
        e.setId(generateId(UUID.randomUUID().toString()));
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == null) {
                arr[i] = e;
                break;
            }
        }
    }

    public void update(E e) {
        if(StringUtils.isNotBlank(e.getId())) {
            E current = getById(e.getId());
            if(current == null) {
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
        if(StringUtils.isNotBlank(id)) {
            E current = getById(id);
            if(current == null) {
                throw new RuntimeException("entity is not exist");
            }
            for(int i = 0; i < arr.length; i++) {
                if(((E)arr[i]).getId().equals(id)) {
                    arr[i] = null;
                }
            }
        } else {
            throw new RuntimeException("entity is not exist");
        }

    }

    public Collection<E> read() {
        return Arrays.stream(arr)
                .map(e -> ((E)e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public E read(String id) {
        if(StringUtils.isNotBlank(id)) {
            E current = getById(id);
            if(current == null) {
                throw new RuntimeException("entity is not exist");
            }
            return current;
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    private E getById(String id) {
        return (E) Arrays.stream(arr)
                .filter(e -> ((E)e).getId().equals(id))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if(Arrays.stream(arr).filter(Objects::nonNull).anyMatch(e -> ((E)e).getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}