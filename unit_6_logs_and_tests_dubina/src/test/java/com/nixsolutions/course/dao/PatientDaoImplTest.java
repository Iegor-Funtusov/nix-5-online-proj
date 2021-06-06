package com.nixsolutions.course.dao;

import com.nixsolutions.course.dao.implementation.PatientDaoImpl;
import com.nixsolutions.course.entity.Patient;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class PatientDaoImplTest {
    private static final String FIRST_NAME = "firstNamePatient";
    private static final String LAST_NAME = "lastNamePatient";
    private static final String LAST_NAME_UPDATE = "lastNamePatientUpdated";
    private static final PatientDao PATIENT_DAO = new PatientDaoImpl();
    private static Patient[] patientArray;

    @BeforeAll
    static void createPatientsByBulk(){
        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setFirstName(FIRST_NAME+i);
            patient.setLastName(LAST_NAME+i);
            PATIENT_DAO.create(patient);
        }
        patientArray = PATIENT_DAO.findAll();
        long countStudents = Arrays.stream(patientArray)
                .filter(s -> s != null && !s.isDeleted())
                .count();
        Assert.assertEquals(10, countStudents);
    }

    @Test
    public void create() {
        Patient patient = new Patient();
        patient.setFirstName(FIRST_NAME);
        patient.setLastName(LAST_NAME);
        PATIENT_DAO.create(patient);
        Patient[] patients = PATIENT_DAO.findAll();
        long countStudents = Arrays.stream(patients)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(patient.getId()))
                .count();
        Assert.assertEquals(1, countStudents);
    }

    @Test
    public void update() {
        Patient patient = patientArray[0];
        Patient patient1 = new Patient();
        patient1.setId(patient.getId());
        patient1.setLastName(LAST_NAME_UPDATE);
        PATIENT_DAO.update(patient1);
        Patient patientUpd = PATIENT_DAO.findById(patient.getId());
        Assert.assertEquals(LAST_NAME_UPDATE, patientUpd.getLastName());
    }

    @Test
    public void delete() {
        Patient patient = patientArray[9];
        PATIENT_DAO.delete(patient.getId());
        Patient[] patients = PATIENT_DAO.findAll();
        long countStudents = Arrays.stream(patients)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(patient.getId()))
                .count();
        Assert.assertEquals(0, countStudents);
    }

    @Test
    public void findById() {
        Patient patient = patientArray[5];
        Patient patientFind = PATIENT_DAO.findById(patient.getId());
        Assert.assertNotNull(patientFind);
    }

    @Test
    public void findAll() {
        Patient[] patients = PATIENT_DAO.findAll();
        Assert.assertNotNull(patients);
    }
}