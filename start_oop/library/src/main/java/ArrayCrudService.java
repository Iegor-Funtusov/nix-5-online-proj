import org.apache.commons.beanutils.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Deprecated
public class ArrayCrudService<E extends BaseEntity> implements CrudService<E> {

    private static final int SIZE = 10;

    private int counter = 0;
    private Object[] container = new Object[SIZE];

    @Override
    public void create(E e) {
        if (counter + 1 > container.length) {
            changeSize();
        }
        e.setId(UUID.randomUUID().toString());
        container[counter] = e;
        counter++;
    }

    @Override
    public void update(E e) {
        Object current = findById(e.getId());
        try {
            BeanUtils.copyProperties(current, e);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        int location = -1;
        for (int i = 0; i < counter; i++) {
            if (((E) container[i]).getId().equals(id)) {
                location = i;
            }
        }
        if (location < 0) {
            throw new RuntimeException("Element does not exist");
        }

        container[location] = null;
        if (location < counter - 1) {
            System.arraycopy(container, location + 1, container, location, counter - 1 - location);
        }
        counter--;
    }

    @Override
    public List<E> read() {
        System.out.println("Array reader");
        return Arrays
                .stream(container)
                .limit(counter)
                .map(o -> ((E) o))
                .collect(Collectors.toList());
    }

    @Override
    public E read(String id) {
        return (E) findById(id);
    }

    private void changeSize() {
        int newCapacity = container.length + 1 + container.length / 2;
        container = Arrays.copyOf(container, newCapacity);
    }

    private Object findById(String id) {
        Object entity = Arrays.stream(container)
                .filter(o -> ((E) o).getId().equals(id)).findAny().orElse(null);
        if (entity == null) {
            throw new RuntimeException("Element does not exist");
        }
        return entity;
    }
}