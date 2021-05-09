package ua.com.oop_start.lib;

import java.util.Collection;

public interface ICrudProcess <E extends BaseEntity> {
    void create(E e);
    void update (E e);
    void delete(String s);
    E read(String s);
    Collection<E> readAll();
}
