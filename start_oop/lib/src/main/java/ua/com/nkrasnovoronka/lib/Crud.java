package ua.com.nkrasnovoronka.lib;

import java.util.Collection;

public interface Crud<E extends Entity> {
    void create(E e);

    void update(E e);

    void delete(String id);

    Collection<E> read();

    E read(String id);
}
