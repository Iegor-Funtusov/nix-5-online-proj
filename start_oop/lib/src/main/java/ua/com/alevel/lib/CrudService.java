package ua.com.alevel.lib;

import java.util.Collection;

public interface CrudService<E extends BaseEntity> {

    void create(E e);
    void update(E e);
    void delete(String id);
    E read(String id);
    Collection<E> read();

}
