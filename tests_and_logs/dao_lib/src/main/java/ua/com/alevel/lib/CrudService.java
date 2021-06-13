package ua.com.alevel.lib;

public interface CrudService<E extends BaseEntity> {
    void create(E e);
    void delete(String id);
    void update(E e);
    E read(String id);
    Object[] read();
}
