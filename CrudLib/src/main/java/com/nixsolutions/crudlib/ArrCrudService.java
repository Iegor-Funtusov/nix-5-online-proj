package com.nixsolutions.crudlib;/*
package com.nixsolutions.lib;

import com.nixsolutions.arrlist.ArrList;
import com.nixsolutions.arrlist.SimpleList;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Collection;


public class ArrCrudService<E extends BaseEntity> implements CrudService<E> {

    private static int OBJECT_COUNT;
    SimpleList<E> arrList = new ArrList<>();

    public ArrCrudService() {
        System.out.println("ArrCrudService.ArrCrudService");
    }

    public void create(E e) {
        e.setId(++OBJECT_COUNT);
        arrList.add(e);
    }

    public void update(E e) {
        E current = findById(e.getId());
        try {
            BeanUtils.copyProperties(current, e);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void delete(int id) {
        arrList.remove(id);
    }

    @Override
    public Collection<E> read() {
        return (Collection<E>) arrList;
    }

    public E read(int id) {
        return findById(id);
    }


    private E findById(int id) {
        E entity = arrList.get(id);
        for (int i = 0; i < arrList.size(); i++) {
            if (entity.getId() == id)
                return entity;
            }
        return null;
        }
    }

*/
