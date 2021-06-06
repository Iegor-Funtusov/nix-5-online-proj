package com.nixsolutions.course.service;

import com.nixsolutions.course.entity.Doctor;
import com.nixsolutions.course.service.implementation.DoctorServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class DoctorServiceImplTest {
    private static final String DOCTOR_NAME = "doctorName";
    private static final String NEW_DOCTOR_NAME = "newDoctorName";
    private static final DoctorService DOCTOR_SERVICE = new DoctorServiceImpl();

    @Test
    public void create() {
        Doctor doctor = createDoctor(DOCTOR_NAME);
        Doctor[] doctorServiceAll = DOCTOR_SERVICE.findAll();
        long countCourses = Arrays.stream(doctorServiceAll)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(doctor.getId()))
                .count();
        Assert.assertEquals(1, countCourses);
    }

    @Test
    public void update() {
        Doctor doctor = createDoctor(DOCTOR_NAME + Math.random());
        Doctor doctor1 = new Doctor();
        doctor1.setId(doctor.getId());
        doctor1.setName(NEW_DOCTOR_NAME);
        DOCTOR_SERVICE.update(doctor1);
        Doctor doctorUpdated = DOCTOR_SERVICE.findById(doctor.getId());
        Assert.assertEquals(NEW_DOCTOR_NAME, doctorUpdated.getName());
    }

    @Test
    public void delete() {
        Doctor doctor = createDoctor("docNameDel");
        DOCTOR_SERVICE.delete(doctor.getId());
        Doctor[] doctors = DOCTOR_SERVICE.findAll();
        long countDoctors = Arrays.stream(doctors)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(doctor.getId()))
                .count();
        Assert.assertEquals(0, countDoctors);
    }

    @Test
    public void createWithSameName() {
        createDoctor("docName");
        Doctor doctor1 = createDoctor("docName");
        Assert.assertNull(doctor1);
    }

    @Test
    public void findByName() {
        String name = "docName2";
        createDoctor(name);
        Doctor doctor1 = DOCTOR_SERVICE.findByName(name);

        Assert.assertEquals(name, doctor1.getName());
    }

    private Doctor createDoctor(String name) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        DOCTOR_SERVICE.create(doctor);
        return DOCTOR_SERVICE.findById(doctor.getId());
    }
}
