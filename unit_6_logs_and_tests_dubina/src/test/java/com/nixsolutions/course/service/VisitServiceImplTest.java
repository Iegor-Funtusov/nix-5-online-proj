package com.nixsolutions.course.service;

import com.nixsolutions.course.entity.Doctor;
import com.nixsolutions.course.entity.Visit;
import com.nixsolutions.course.entity.Patient;
import com.nixsolutions.course.service.implementation.VisitServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class VisitServiceImplTest {
    private static final String PATIENT_FIRST_NAME = "patientFirstName";
    private static final String PATIENT_LAST_NAME = "patientLastName";
    private static final String DOCTOR_NAME = "doctorName";
    private static final String NEW_DOCTOR_NAME = "newDoctorName";
    private static final VisitService VISIT_SERVICE = new VisitServiceImpl();

    @Test
    public void create() {
        Visit visit = createVisit();
        Visit[] visits = VISIT_SERVICE.findAll();
        long countPatients = Arrays.stream(visits)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(visit.getId()))
                .count();

        Assert.assertEquals(1, countPatients);
    }

    @Test
    public void update() {
        Doctor doctor = new Doctor();
        doctor.setName(NEW_DOCTOR_NAME);

        Visit visit = createVisit();
        Visit visit1 = new Visit();

        visit1.setId(visit.getId());
        visit1.setDoctor(doctor);
        visit1.setPatient(new Patient());

        VISIT_SERVICE.update(visit1);
        Visit visitUpdated = VISIT_SERVICE.findById(visit.getId());

        Assert.assertEquals(doctor, visitUpdated.getDoctor());
    }

    @Test
    public void delete() {
        Visit courseReg = createVisit();

        VISIT_SERVICE.delete(courseReg.getId());
        Visit[] visits = VISIT_SERVICE.findAll();
        long countDoctors = Arrays.stream(visits)
                .filter(Objects::nonNull)
                .filter(s -> s.getId().equals(courseReg.getId()))
                .count();

        Assert.assertEquals(0, countDoctors);
    }

    @Test
    public void notCreateWithoutDoctor(){
        Visit visit = new Visit();
        visit.setPatient(new Patient());

        VISIT_SERVICE.create(visit);
        Visit visit1 = VISIT_SERVICE.findById(visit.getId());
        Assert.assertNull(visit1);
    }

    @Test
    public void notCreateWithoutPatient(){
        Visit visit = new Visit();
        visit.setDoctor(new Doctor());
        VISIT_SERVICE.create(visit);
        Visit visit1 = VISIT_SERVICE.findById(visit.getId());
        Assert.assertNull(visit1);
    }

    private Visit createVisit(){
        Patient patient = new Patient();
        patient.setFirstName(PATIENT_FIRST_NAME);
        patient.setLastName(PATIENT_LAST_NAME);
        Doctor doctor = new Doctor();
        doctor.setName(DOCTOR_NAME);
        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        VISIT_SERVICE.create(visit);
        return VISIT_SERVICE.findById(visit.getId());
    }
}
