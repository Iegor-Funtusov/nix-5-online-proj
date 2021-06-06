package com.nixsolutions.course.service;

import com.nixsolutions.course.entity.Patient;
import com.nixsolutions.course.service.implementation.PatientServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class PatientServiceImplTest {
    private static final String PATIENT_FIRST_NAME = "patientFirstName";
    private static final String PATIENT_LAST_NAME = "patientLastName";
    private static final String LAST_NAME_UPDATE = "updatePatientLastName";
    private static final PatientService PATIENT_SERVICE = new PatientServiceImpl();

    @Test
    public void create() {
        Patient patient = createPatient();
        Patient[] patients = PATIENT_SERVICE.findAll();
        long countPatients = Arrays.stream(patients)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(patient.getId()))
                .count();

        Assert.assertEquals(1, countPatients);
    }

    @Test
    public void update() {
        Patient patient = createPatient();
        Patient patient1 = new Patient();
        patient1.setId(patient.getId());
        patient1.setLastName(LAST_NAME_UPDATE);
        PATIENT_SERVICE.update(patient1);
        Patient patientUpadated = PATIENT_SERVICE.findById(patient.getId());

        Assert.assertEquals(LAST_NAME_UPDATE, patientUpadated.getLastName());
    }

    @Test
    public void delete() {
        Patient patient = createPatient();

        PATIENT_SERVICE.delete(patient.getId());

        Patient[] patients = PATIENT_SERVICE.findAll();
        long countPatients = Arrays.stream(patients)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(patient.getId()))
                .count();

        Assert.assertEquals(0, countPatients);
    }

    private Patient createPatient() {
        Patient patient = new Patient();
        patient.setFirstName(PATIENT_FIRST_NAME);
        patient.setLastName(PATIENT_LAST_NAME);

        PATIENT_SERVICE.create(patient);

        return PATIENT_SERVICE.findById(patient.getId());
    }
}
