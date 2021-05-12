package lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

//@Deprecated
public class CrudProcessArray<E extends BaseEntity> implements CrudProcess<E> {
    private static final int CAPACITY = 10;
    private Object[] array = new Object[CAPACITY];
   // private Object[] array = new Object[10];
    private int size = 0;

    public CrudProcessArray() {
        System.out.println("CrudProcessArray.CrudProcessArray");
    }

    public void create(E e) {
        if (size == array.length) {
            increaseCapacity();
        }
        e.setId(generateId(UUID.randomUUID().toString()));
        array[size++] = e;
    }

    public void update(E e) {
        if (StringUtils.isNotBlank(e.getId())) {
            E current = getEById(e.getId());
            if (current == null) throw new RuntimeException("Entity is not exist!");
            try {
                BeanUtils.copyProperties(current, e);
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            } catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            }
        } else
        throw new RuntimeException("Entity is not exist!");
    }

    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEById(id);
            if (current == null) throw new RuntimeException("Entity is not exist!");
           for (int i = 0; i < size; i++) {
                if (((E) array[i]).getId().equals(id)) {
                    array[i] = null;
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                    size--;
                }
           }
        } else
        throw new RuntimeException("Entity is not exist!");
    }

    public Collection<E> read() {
        return Arrays.stream(array).map(e -> ((E) e)).filter(Objects::nonNull).collect(Collectors.toList());

    }
    public E read(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEById(id);
            if (current == null) throw new RuntimeException("Entity is not exist!");
            return current;
        }
        throw new RuntimeException("Entity is not exist!");
    }

    private E getEById(String id) {
        return (E) Arrays.stream(array)
            .filter(e -> ((E) e).getId().equals(id))
            .findAny()
            .orElse(null);
    }

    private String generateId(String id) {
        if (Arrays.stream(array)
            .filter(Objects::nonNull)
            .anyMatch(e -> ((E) e).getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }

    private void increaseCapacity() {
        int IncreasedCapacity = array.length * 2;
        array = Arrays.copyOf(array, IncreasedCapacity);
    }
}
