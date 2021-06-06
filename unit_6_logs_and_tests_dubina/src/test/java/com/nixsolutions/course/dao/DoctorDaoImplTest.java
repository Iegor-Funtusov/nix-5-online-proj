package com.nixsolutions.course.dao;

import com.nixsolutions.course.dao.implementation.DoctorDaoImpl;
import com.nixsolutions.course.entity.Doctor;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class DoctorDaoImplTest {
    private static final String NAME = "docName";
    private static final String NEW_NAME = "newDocName";
    private static final DoctorDao DOCTOR_DAO = new DoctorDaoImpl();
    private static Doctor[] doctorArray;

    @BeforeAll
    static void createDoctorsByBulk() {
        for (int i = 0; i < 10; i++) {
            Doctor doctor = new Doctor();
            doctor.setName(NAME + i);
            DOCTOR_DAO.create(doctor);
        }
        doctorArray = DOCTOR_DAO.findAll();
        long countDoctors = Arrays.stream(doctorArray)
                .filter(Objects::nonNull)
                .count();

        Assert.assertEquals(10, countDoctors);
    }

    @Test
    public void create() {
        Doctor doctor = new Doctor();
        doctor.setName(NAME);

        DOCTOR_DAO.create(doctor);
        Doctor[] doctors = DOCTOR_DAO.findAll();
        long countCourses = Arrays.stream(doctors)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(doctor.getId()))
                .count();

        Assert.assertEquals(1, countCourses);
    }

    @Test
    public void update() {
        Doctor doctor = doctorArray[0];
        Doctor doctor1 = new Doctor();
        doctor1.setId(doctor.getId());
        doctor1.setName(NEW_NAME);
        DOCTOR_DAO.update(doctor1);
        Doctor doctorUpdated = DOCTOR_DAO.findById(doctor.getId());
        Assert.assertEquals(NEW_NAME, doctorUpdated.getName());
    }

    @Test
    public void delete() {
        Doctor doctor = doctorArray[9];
        DOCTOR_DAO.delete(doctor.getId());
        Doctor[] doctors = DOCTOR_DAO.findAll();
        long countDoctors = Arrays.stream(doctors)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(doctor.getId()))
                .count();
        Assert.assertEquals(0, countDoctors);
    }

    @Test
    public void findById() {
        Doctor doctor = doctorArray[5];
        Doctor doctorFind = DOCTOR_DAO.findById(doctor.getId());
        Assert.assertNotNull(doctorFind);
    }

    @Test
    public void findByName() {
        String name = doctorArray[2].getName();
        Doctor doctor1 = DOCTOR_DAO.findByName(name);

        Assert.assertEquals(name, doctor1.getName());
    }

    @Test
    public void findAll() {
        Doctor[] doctors = DOCTOR_DAO.findAll();
        Assert.assertNotNull(doctors);
    }
}
