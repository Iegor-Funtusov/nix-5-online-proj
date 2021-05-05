package com.nixcourse.lib;

import java.util.Collection;
import java.util.UUID;
import java.util.Vector;

public class VectorCrudService<E extends BaseEntity> implements CrudCollectionService<E> {

    private Vector<E> data = new Vector<>();

    private String generateId(String id) {
        if (data.stream().anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
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
        e.setId(generateId(UUID.randomUUID().toString()));
        data.add(e);
    }

    @Override
    public void update(E e) {
        data.set(Integer.getInteger(findElementById(e.getId()).getId()), e);
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
