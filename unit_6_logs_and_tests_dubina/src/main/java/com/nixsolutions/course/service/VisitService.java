package com.nixsolutions.course.service;

import com.nixsolutions.course.entity.Visit;

public interface VisitService {
    void create(Visit visit);

    void update(Visit visit);

    void delete(String id);

    Visit findById(String id);

    Visit[] findAll();
}