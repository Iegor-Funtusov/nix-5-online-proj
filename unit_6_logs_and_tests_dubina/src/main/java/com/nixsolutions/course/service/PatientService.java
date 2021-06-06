package com.nixsolutions.course.service;

import com.nixsolutions.course.entity.Patient;

public interface PatientService {
    void create(Patient patient);

    void update(Patient patient);

    void delete(String id);

    Patient findById(String id);

    Patient[] findAll();
}