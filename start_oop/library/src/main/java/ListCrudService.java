import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Deprecated
public class ListCrudService<E extends BaseEntity> implements CrudService<E> {

    List<E> container = new ArrayList<>();

    @Override
    public void create(E e) {
        e.setId(UUID.randomUUID().toString());
        container.add(e);
    }

    @Override
    public void update(E e) {
        E current = findById(e.getId());
        try {
            BeanUtils.copyProperties(current, e);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        container.removeIf(e -> e.getId().equals(id));
    }

    @Override
    public List<E> read() {
        System.out.println("List reader");
        return new ArrayList<>(container);
    }

    @Override
    public E read(String id) {
        return findById(id);
    }

    private E findById(String id) {
        E entity = container
                .stream()
                .filter(e -> e.getId().equals(id))
                .findAny()
                .orElse(null);
        if (entity == null) {
            throw new RuntimeException("Element does not exist");
        }
        return entity;
    }
}
