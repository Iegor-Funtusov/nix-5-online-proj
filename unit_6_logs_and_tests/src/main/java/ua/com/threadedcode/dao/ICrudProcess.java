package ua.com.threadedcode.dao;

import ua.com.threadedcode.dao.crudObject.ICrudAuthor;
import ua.com.threadedcode.dao.crudObject.ICrudBook;
import ua.com.threadedcode.entity.BaseEntity;

public interface ICrudProcess<E extends BaseEntity> extends ICrudAuthor, ICrudBook {

}
