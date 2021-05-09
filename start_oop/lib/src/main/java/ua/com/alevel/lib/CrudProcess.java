package ua.com.alevel.lib;

import java.util.Collection;

public interface CrudProcess<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    void delete(String id);
    Collection<E> read();
    E read(String id);
}
