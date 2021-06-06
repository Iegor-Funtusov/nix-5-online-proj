package com.nixsolutions.course.dao;

import com.nixsolutions.course.entity.Patient;

public interface PatientDao {
    void create(Patient patient);

    void update(Patient patient);

    void delete(String id);

    Patient findById(String id);

    Patient[] findAll();
}
