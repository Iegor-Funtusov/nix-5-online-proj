package ua.com.alevel.test.lib;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.alevel.lib.*;

public class ArrayListCrudTest {

    private final ArrayListCrudService<BaseEntity> list = new ArrayListCrudService<>();
    private final BaseEntityContainer<BaseEntity> container = new BaseEntityContainer<>();
    private  final int n = 10;


    @BeforeEach
    public  void init() {
        for (int i = 0; i < n; i++) {
            BaseEntity ent = new BaseEntity();
            BaseEntity ent2 = new BaseEntity();
            list.create(ent);
            ent2.setId(ent.getId());
            container.add(ent2);
        }
    }

    @Test
    public void testCreate() {
        BaseEntity entity = new BaseEntity();
        list.create(entity);
        long id = entity.getId();
        Assert.assertEquals(entity, list.read(id));
    }

    @Test
    public void testDelete() {
        for (int i = 0; i < n; i++) {
            list.delete(container.get(i).getId());
            Assert.assertNull(list.read(container.get(i).getId()));
        }
    }

    @Test
    public void testUpdateIdUnchanged() {
        for (int i = 0; i < n; i++) {
            long oldId = container.get(i).getId();
            container.get(i).setId(oldId+1);
            list.update(oldId, container.get(i));
            Assert.assertEquals(oldId, list.read(container.get(i).getId()).getId());
        }
    }
}
