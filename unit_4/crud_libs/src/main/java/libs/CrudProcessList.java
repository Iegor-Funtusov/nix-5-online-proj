package libs;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CrudProcessList<E extends BaseInput> implements CrudProcess<E> {
    private final List<E> list = new ArrayList<>();

    public CrudProcessList() {
        System.out.println("CrudProcessList");
    }


    public void create(E e) {
        e.setId(generateId(UUID.randomUUID().toString()));
        list.add(e);
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
            list.removeIf(e -> e.getId().equals(id));
        } else {
            throw new RuntimeException("There is no such record");
        }
    }

    public List<E> list() {
        return list;
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
        return list.stream()
                .filter(e -> id.equals(e.getId()))
                .findAny()
                .orElse(null);
    }

    private String generateId(String id) {
        if (list.stream().anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
