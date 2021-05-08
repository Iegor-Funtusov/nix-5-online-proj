package libs;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
@Deprecated
public class CrudProcessObject<E extends BaseInput> implements CrudProcess<E> {
    private int MAX = 5;
    private E[] objects;
    private int item;

    public CrudProcessObject() {
        System.out.println("CrudProcessObject");
        objects = (E[]) new Object[MAX];
    }
    @Override
    public void create(E e) {


        e.setId(generateId(UUID.randomUUID().toString()));
        objects[item++] = e;
    }
    @Override
    public void update(E e) {
        if (StringUtils.isNotBlank(e.getId())) {
            E current = getEById(e.getId());
            if (current == null) {
                throw new RuntimeException("There is no such record");
            }
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException | InvocationTargetException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
        } else {
            throw new RuntimeException("There is no such record");
        }
    }
    @Override
    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEById(id);
            if (current == null) {
                throw new RuntimeException("There is no such record");
            }
            for(int i = 0; i < item; i++){
                if(objects[i] == current){
                    System.arraycopy(objects, i+1, objects, i, item-i-1);
                    item--;
                }
            }
        } else {
            throw new RuntimeException("There is no such record");
        }
    }
    @Override
    public Collection<E> list() {
        return Arrays.stream(objects).map(e -> ((E)e)).collect(Collectors.toList());
    }
    @Override
    public E read(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEById(id);
            if (current == null) {
                throw new RuntimeException("There is no such record");
            }
            return current;
        }
        throw new RuntimeException("There is no such record");
    }

    private E getEById(String id) {
        return (E) Arrays.stream(objects)
                .filter(e -> ((E)e).getId().equals(id))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if(item > 0 && Arrays.stream(objects).anyMatch(e -> ((E) e).getId().equals(id)) ){
            return generateId(generateId(UUID.randomUUID().toString()));
        }
        return id;
    }
}
