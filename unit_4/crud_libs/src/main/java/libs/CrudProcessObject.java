package libs;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
@Deprecated
public class CrudProcessObject<E extends BaseInput> implements CrudProcess<E> {
    private final int MAX = 50;
    private Object[] objects = new Object[MAX];

    public CrudProcessObject() {
        System.out.println("CrudProcessObject");
    }

    public void create(E e) {
        e.setId(generateId(UUID.randomUUID().toString()));
        for (int k = 0; k < objects.length; k++) {
            if (objects[k] == null) {
                objects[k] = e;
                break;
            }
        }
    }

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

    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            E current = getEById(id);
            if (current == null) {
                throw new RuntimeException("There is no such record");
            }
            for(int k = 0; k < objects.length; k++) {
                if(((E)objects[k]).getId().equals(id)) {
                    objects[k] = null;
                    break;
                }
            }
        } else {
            throw new RuntimeException("There is no such record");
        }
    }

    public Set list() {
        return Arrays.stream(objects).filter(e -> Objects.nonNull(e)).collect(Collectors.toSet());
    }

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
        if(Arrays.stream(objects).filter(e -> Objects.nonNull(e)).anyMatch(e -> ((E)e).getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
