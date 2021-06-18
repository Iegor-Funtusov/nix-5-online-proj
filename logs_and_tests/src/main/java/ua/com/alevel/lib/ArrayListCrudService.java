package ua.com.alevel.lib;

public class ArrayListCrudService<T extends BaseEntity> implements CRUDService<T> {
    private final BaseEntityContainer<T> entitiesList = new BaseEntityContainer<>();

    @Override
    public void update(long id, T entity) {
        if (entity == null) {
            throw new NullPointerException();
        }
        int updatingEntity = locateEntity(id);
        if (updatingEntity != -1) {
            entity.setId(id);
            entitiesList.set(updatingEntity, entity);
        }
    }

    @Override
    public void delete(long id) {
        int updatingEntity = locateEntity(id);
        if (updatingEntity != -1) {
            entitiesList.remove(updatingEntity);
        }
    }

    @Override
    public void create(T entity) {
        if (entity == null)
            throw new NullPointerException("Entity is null!");
        int id = Math.abs(entity.hashCode());
        entity.setId(id);
        entitiesList.add(entity);
    }

    @Override
    public T read(long id) {
        int readingEntity = locateEntity(id);
        if (readingEntity == -1)
            return null;
        return entitiesList.get(readingEntity);
    }

    @Override
    public BaseEntityContainer<T> readAll(){
        BaseEntityContainer<T> temp = new BaseEntityContainer<>();
        for(int i = 0; i < entitiesList.size(); i++){
            temp.add(entitiesList.get(i));
        }
        return temp;
    }

    private int locateEntity(long id) {
        for (int i = 0; i < entitiesList.size(); i++) {
            if (entitiesList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}