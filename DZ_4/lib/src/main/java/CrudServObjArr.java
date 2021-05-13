import org.apache.commons.beanutils.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Deprecated
public class CrudServObjArr<E extends BaseEntity> implements CrudInt {


    private Object[] objects = new Object[20];
    private int count = 0;


    @Override
    public void create(BaseEntity e) {
        if (count + 1 > objects.length) {
            changeSize();
        }
        e.setId(UUID.randomUUID().toString());
        objects[count] = e;
        count++;
    }

    @Override
    public void update(BaseEntity e) {
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
        for (int i = 0; i < count; i++) {
            if (((E) objects[i]).getId().equals(id)) {
                location = i;
            }
        }
        if (location < 0) {
            throw new RuntimeException("Element does not exist");
        }

        objects[location] = null;
        if (location < count - 1) {
            System.arraycopy(objects, location + 1, objects, location, count - 1 - location);
        }
        count--;
    }

    @Override
    public List<E> read() {
        System.out.println("Array reader");
        return Arrays
                .stream(objects)
                .limit(count)
                .map(o -> ((E) o))
                .collect(Collectors.toList());
    }

    @Override
    public E read(String id) {
        return (E) findById(id);
    }

    private void changeSize() {
        int newCapacity = objects.length + 1 + objects.length / 2;
        objects = Arrays.copyOf(objects, newCapacity);
    }

    private Object findById(String id) {
        Object entity = Arrays.stream(objects)
                .filter(o -> ((E) o).getId().equals(id)).findAny().orElse(null);
        if (entity == null) {
            throw new RuntimeException("Element does not exist");
        }
        return entity;
    }
}
