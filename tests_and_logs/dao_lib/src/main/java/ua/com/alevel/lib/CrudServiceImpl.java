package ua.com.alevel.lib;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

public class CrudServiceImpl<E extends BaseEntity>
        implements CrudService<E>{

    private static final int DEFAULT_SIZE = 10;
    private static final double MAX_FILLING = 0.75;

    private Object[] entities = new Object[DEFAULT_SIZE];
    private int size = 0;

    @Override
    public void create(E e) {
        if(isArrayFull())
            entities = arrayGrow();
        e.setId(generateId());
        this.entities[this.size++] = e;
    }

    private boolean isArrayFull(){
        return (entities.length - this.size) / (double) entities.length > MAX_FILLING;
    }

    private Object[] arrayGrow() {
        int newSize = (entities.length * 3) / 2 + 1;
        Object[] newArray = new Object[newSize];
        System.arraycopy(entities, 0, newArray, 0, this.size);
        return newArray;
    }

    @Override
    public void update(E e) {
        if(StringUtils.isNotBlank(e.getId())) {
            int currentIndex = findEntityById(e.getId());
            if (currentIndex == -1)
                throw new RuntimeException("entity is not exist");
            entities[currentIndex] = e;
        }else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public void delete(String id) {
        if(StringUtils.isNotBlank(id)) {
            int currentIndex = findEntityById(id);
            if (currentIndex == -1 || size == 0) {
                throw new RuntimeException("entity is not exist");
            }
            System.arraycopy(entities, currentIndex + 1,
                    entities, currentIndex, size-currentIndex-1);
            this.size--;
        }else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public E read(String id) throws RuntimeException {
        if(StringUtils.isNotBlank(id)) {
            int currentIndex = findEntityById(id);
            if (currentIndex == -1 || size == 0) {
                throw new RuntimeException("entity is not exist");
            }
            return (E) entities[currentIndex];
        }else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public Object[] read() {
        return this.trimToSize();
    }

    private Object[] trimToSize() {
        if(size == 0)
            throw new RuntimeException("empty array");
        Object[] newArr = new Object[size];
        System.arraycopy(entities, 0, newArr, 0, size);
        return newArr;
    }

    private String generateId(){
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (!isIdValid(id));
        return id;
    }

    private boolean isIdValid(String id){
        for (Object e : entities) {
            if(e instanceof BaseEntity)
                if ( ((E) e).getId().equals(id))
                    return false;
        }
        return true;
    }
    private int findEntityById(String id) {
        for (int i = 0; i < this.size; i++) {
            if(entities[i] instanceof BaseEntity) {
                if (((E) entities[i]).getId().equals(id))
                    return i;
            }else
                return -1;
        }
        return -1;
    }
}
