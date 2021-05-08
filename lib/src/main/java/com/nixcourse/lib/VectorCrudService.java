package com.nixcourse.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Vector;

@Deprecated
public class VectorCrudService<E extends BaseEntity> implements ICrudCollectionService<E> {

    private Vector<E> data = new Vector<>();

    public VectorCrudService() {
        System.out.println("VectorCrudService.VectorCrudService");
    }

    private E findElementById(String id) {
        E entity = data.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);

        if (entity == null) {
            throw new RuntimeException("There is no such element with id: " + id);
        }

        return entity;
    }

    @Override
    public void create(E e) {
        e.setId(BaseEntity.generateId());
        data.add(e);
    }

    @Override
    public void update(E e) {
        if (StringUtils.isNotBlank(e.getId())) {
            E current = findElementById((e.getId()));

            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public void delete(String id) {
        data.remove(findElementById(id));
    }

    @Override
    public Collection<E> read() {
        return data;
    }

    @Override
    public E read(String id) {
        return findElementById(id);
    }
}
