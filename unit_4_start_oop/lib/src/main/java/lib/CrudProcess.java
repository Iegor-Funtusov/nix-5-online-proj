package lib;

import java.util.Collection;

public interface CrudProcess <E extends BaseEntity> {
    public void create(E e);
    public void update(E e);
    public void delete(String id);
    public Collection<E> read();
    public E read(String id);

}
