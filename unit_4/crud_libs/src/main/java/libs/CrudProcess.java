package libs;

import java.util.Collection;

public interface CrudProcess<E extends BaseInput> {
    void create(E e);
    void update(E e);
    void delete(String id);
    Collection<E> list();
    E read(String id);
}
