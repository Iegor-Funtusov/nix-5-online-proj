package com.nixsolutions.course.dao;

import com.nixsolutions.course.entity.Visit;

public interface VisitDao {
    void create(Visit visit);

    void update(Visit visit);

    void delete(String id);

    Visit findById(String id);

    Visit[] findAll();
}