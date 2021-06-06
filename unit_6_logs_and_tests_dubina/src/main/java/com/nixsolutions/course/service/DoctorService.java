package com.nixsolutions.course.service;

import com.nixsolutions.course.entity.Doctor;

public interface DoctorService {
    void create(Doctor doctor);

    void update(Doctor doctor);

    void delete(String id);

    Doctor findById(String id);

    Doctor findByName(String name);

    Doctor[] findAll();
}
