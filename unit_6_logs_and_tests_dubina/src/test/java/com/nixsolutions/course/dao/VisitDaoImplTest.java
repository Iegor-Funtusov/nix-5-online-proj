package com.nixsolutions.course.dao;

import com.nixsolutions.course.dao.implementation.VisitDaoImpl;
import com.nixsolutions.course.entity.Doctor;
import com.nixsolutions.course.entity.Visit;
import com.nixsolutions.course.entity.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class VisitDaoImplTest {
    private static final String PATIENT_FIRST_NAME = "firstNamePatient";
    private static final String PATIENT_LAST_NAME = "lastNamePatient";
    private static final String DOCTOR_NAME = "doctorName";
    private static final String NEW_DOCTOR_NAME = "newDoctorName";
    private static final VisitDao VISIT_DAO = new VisitDaoImpl();
    private static Visit[] visitArray;

    @BeforeAll
    static void createVisitByBulk(){
        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setFirstName(PATIENT_FIRST_NAME +i);
            patient.setLastName(PATIENT_LAST_NAME +i);
            Doctor doctor = new Doctor();
            doctor.setName(DOCTOR_NAME +i);
            Visit visit = new Visit();
            visit.setPatient(patient);
            visit.setDoctor(doctor);
            VISIT_DAO.create(visit);
        }

        visitArray = VISIT_DAO.findAll();
        long countVisits = Arrays.stream(visitArray)
                .filter(Objects::nonNull)
                .count();
        Assert.assertEquals(10, countVisits);
    }

    @Test
    public void create() {
        Patient patient = new Patient();
        patient.setFirstName(PATIENT_FIRST_NAME);
        patient.setLastName(PATIENT_LAST_NAME);

        Doctor doctor = new Doctor();
        doctor.setName(DOCTOR_NAME);

        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);

        VISIT_DAO.create(visit);

        Visit[] visits = VISIT_DAO.findAll();
        long countVisits = Arrays.stream(visits)
                .filter(s -> s != null)
                .filter(s -> s.getId().equals(visit.getId()))
                .count();

        Assert.assertEquals(1, countVisits);
    }

    @Test
    public void update() {
        Doctor doctor = new Doctor();
        doctor.setName(NEW_DOCTOR_NAME);

        Visit visit = visitArray[0];
        Visit visit1 = new Visit();

        visit1.setId(visit.getId());
        visit1.setDoctor(doctor);
        visit1.setPatient(new Patient());

        VISIT_DAO.update(visit1);
        Visit visitUpdated = VISIT_DAO.findById(visit.getId());

        Assert.assertEquals(doctor, visitUpdated.getDoctor());
    }

    @Test
    public void delete() {
        Visit visit = visitArray[9];

        VISIT_DAO.delete(visit.getId());

        Visit[] visits = VISIT_DAO.findAll();
        long countCourses = Arrays.stream(visits)
                .filter(s -> s != null && !s.isDeleted())
                .filter(s -> s.getId().equals(visit.getId()))
                .count();
        Assert.assertEquals(0, countCourses);
    }

    @Test
    public void findById() {
        Visit visit = visitArray[5];
        Visit visitFind = VISIT_DAO.findById(visit.getId());
        Assert.assertNotNull(visitFind);
    }

    @Test
    public void findAll() {
        Visit[] visits = VISIT_DAO.findAll();
        Assert.assertNotNull(visits);
    }
}