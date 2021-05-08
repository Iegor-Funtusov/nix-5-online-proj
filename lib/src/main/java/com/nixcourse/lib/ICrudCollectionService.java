package com.nixcourse.lib;

import java.util.Collection;

public interface ICrudCollectionService<E extends BaseEntity> {
    void create(E e);
    E read(String id);
    Collection<E> read();
    void update(E e);
    void delete(String id);
}
