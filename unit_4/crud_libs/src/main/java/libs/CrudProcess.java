package libs;

import java.util.Collection;

public interface CrudProcess<E extends BaseInput> {
    void create(E e);
    void update(E e);
    void delete(String id);
    E read(String id);
    Collection<E> list();
}
