package com.k4rnaj1k.lib;

import javax.lang.model.UnknownEntityException;
import java.util.Collection;

public interface CrudProcess<E extends BaseEntity> {
    void create(E e);
    Collection<E> read();
    E read(String id) throws Exception;
    void update(E e);
    void delete(String id);
}
