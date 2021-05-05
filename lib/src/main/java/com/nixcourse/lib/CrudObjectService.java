package com.nixcourse.lib;

public interface CrudObjectService<E extends BaseEntity> {
    void create(E e);
    void update(E e);
    void delete(String id);
    E[] read();
    E read(String id);
}
