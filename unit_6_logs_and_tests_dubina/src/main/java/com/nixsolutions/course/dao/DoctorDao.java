package com.nixsolutions.course.dao;

import com.nixsolutions.course.entity.Doctor;

public interface DoctorDao {
    void create(Doctor doctor);

    void update(Doctor doctor);

    void delete(String id);

    Doctor findById(String id);

    Doctor findByName(String name);

    Doctor[] findAll();
}
