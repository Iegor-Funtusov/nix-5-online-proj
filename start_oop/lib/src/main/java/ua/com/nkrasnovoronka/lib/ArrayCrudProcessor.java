package ua.com.nkrasnovoronka.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Deprecated
public class ArrayCrudProcessor<E extends  Entity> implements Crud<E> {
    private final int DATA_CAPACITY = 25;
    private Object[] dataStorage = new Object[DATA_CAPACITY];
    private int size;

    public ArrayCrudProcessor() {
        System.out.println(this.getClass().getSimpleName());
    }

    @Override
    public void create(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        resizeIfNeeded();
        e.setId(generateId(UUID.randomUUID().toString()));
        dataStorage[size++] = e;
    }

    @Override
    public void update(E e) {
        if (StringUtils.isNotBlank(e.getId())) {
            E current = getEntityById(e.getId());
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    private E getEntityById(String id) {
        return (E) Arrays.stream(dataStorage)
                .filter(o -> ((E)o).getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find entity with id " + id));
    }

    @Override
    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEntityById(id);
            removeEntity(current);
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    private void removeEntity(E current) {
        for (int i = 0; i < size; i++) {
            if(dataStorage[i].equals(current)){
                System.arraycopy(dataStorage, i + 1, dataStorage, i, size - i - 1);
                size--;
            }
        }
    }

    @Override
    public Collection<E> readAll() {
        List<E> eList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            eList.add((E) dataStorage[i]);
        }
        return eList;
    }

    @Override
    public E read(String id) {
        if (StringUtils.isNotBlank(id)) {
            return getEntityById(id);
        }
        throw new RuntimeException("entity is not exist");
    }

    private void resizeIfNeeded(){
        if(dataStorage.length == size){
            E[] newDataStorage = (E[]) new Object[DATA_CAPACITY * 2];
            System.arraycopy(dataStorage, 0, newDataStorage, 0, size);
            dataStorage = newDataStorage;
        }
    }

    private String generateId(String id) {
        if(size > 0 && Arrays.stream(dataStorage).anyMatch(o -> ((E) o).getId().equals(id)) ){
            return generateId(generateId(UUID.randomUUID().toString()));
        }
        return id;
    }

}
