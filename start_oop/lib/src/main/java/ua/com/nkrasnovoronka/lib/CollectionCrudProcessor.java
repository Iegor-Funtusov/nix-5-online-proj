package ua.com.nkrasnovoronka.lib;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CollectionCrudProcessor<E extends Entity> implements Crud<E> {

    private final Set<E> dataStorage = new HashSet<>();

    public CollectionCrudProcessor() {
        System.out.println(this.getClass().getSimpleName());
    }

    @Override
    public void create(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        e.setId(generateId(UUID.randomUUID().toString()));
        dataStorage.add(e);
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

    @Override
    public void delete(String id) {
        if (StringUtils.isNotBlank(id)) {
            dataStorage.removeIf(e -> e.getId().equals(id));
        } else {
            throw new RuntimeException("entity is not exist");
        }
    }

    @Override
    public Collection<E> readAll() {
        return dataStorage;
    }

    @Override
    public E read(String id) {
        if (StringUtils.isNotBlank(id)) {
            return getEntityById(id);
        }
        throw new RuntimeException("entity is not exist");
    }

    private E getEntityById(String id) {
        return dataStorage.stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Cannot find entity with id " + id));
    }

    private String generateId(String id) {
        if (dataStorage.stream().anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
