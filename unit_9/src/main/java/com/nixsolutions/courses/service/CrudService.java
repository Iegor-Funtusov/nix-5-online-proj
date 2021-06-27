package com.nixsolutions.courses.service;

import java.util.List;

public interface CrudService<E> {

    void create(E item);
    void update();
    E findById(String id);
    void delete(String id);
    List<E> readAll();
}
