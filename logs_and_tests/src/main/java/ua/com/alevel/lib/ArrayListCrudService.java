package ua.com.alevel.lib;

public class ArrayListCrudService <T extends BaseEntity> implements CRUDService<T> {

    private final BaseEntityContainer<T> list = new BaseEntityContainer<>();

    @Override
    public void update(long id, T entity) {
        if (entity == null) {
            throw new NullPointerException();
        }
        int updatingEntity = locateEntity(id);
        if (updatingEntity != -1) {
            entity.setId(id);
            list.set(updatingEntity, entity);
        }
    }

    @Override
    public void delete(long id) {
        int updatingEntity = locateEntity(id);
        if (updatingEntity != -1) {
            list.remove(updatingEntity);
        }
    }

    @Override
    public void create(T entity) {
        if (entity == null)
            throw new NullPointerException("Entity is null!");
        int id = Math.abs(entity.hashCode());
        entity.setId(id);
        list.add(entity);
    }

    @Override
    public T read(long id) {
        int readingEntity = locateEntity(id);
        if (readingEntity == -1)
            return null;
        return list.get(readingEntity);
    }

    @Override
    public BaseEntityContainer<T> readAll(){
        BaseEntityContainer<T> temp = new BaseEntityContainer<>();
        for(int i = 0; i < list.size(); i++){
            temp.add(list.get(i));
        }
        return temp;
    }

    private int locateEntity(long id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}