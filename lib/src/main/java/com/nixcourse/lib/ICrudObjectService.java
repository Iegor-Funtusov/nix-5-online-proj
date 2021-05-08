package com.nixcourse.lib;

public interface ICrudObjectService<E extends BaseEntity> {
    void create(E e);
    E[] read();
    E read(String id);
    void update(E e);
    void delete(String id);
}
