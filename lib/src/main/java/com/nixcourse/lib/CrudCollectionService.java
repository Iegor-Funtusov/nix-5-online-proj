package com.nixcourse.lib;

import java.util.Collection;

public interface CrudCollectionService<E extends BaseEntity> {
    void create(E e);
    void update(E e);
    void delete(String id);
    Collection<E> read();
    E read(String id);
}
