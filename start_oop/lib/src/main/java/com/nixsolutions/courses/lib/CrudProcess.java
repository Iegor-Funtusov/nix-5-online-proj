package com.nixsolutions.courses.lib;

import java.util.Collection;

public interface CrudProcess<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    void delete(String id);
    E read(String id);
    Collection<E> readAll();
}
