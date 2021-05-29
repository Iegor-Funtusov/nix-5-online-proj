package ua.com.alevel.lib;

import java.util.Collection;

public interface CrudService<E extends BaseEntity> {
    void create(E e);
    void delete(String id);
    void update(E e);
    E read(String id);
    Collection<E> read();
}
