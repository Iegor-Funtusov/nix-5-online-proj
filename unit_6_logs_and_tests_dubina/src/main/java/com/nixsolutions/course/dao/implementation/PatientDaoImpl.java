package com.nixsolutions.course.dao.implementation;

import com.nixsolutions.course.dao.PatientDao;
import com.nixsolutions.course.entity.Patient;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Arrays;
import java.util.UUID;

public class PatientDaoImpl implements PatientDao {
    private static int sizeOfPatientList = 100;
    private int countPatient = 0;

    private static Patient[] patients = new Patient[sizeOfPatientList];

    @Override
    public void create(Patient patient) {
        if (countPatient++ > sizeOfPatientList) {
            increaseListSize();
        }
        patient.setId(generateId(UUID.randomUUID().toString()));
        patients[countPatient] = patient;
        countPatient++;
    }


    @Override
    public void update(Patient patient) {
        Patient current = findById(patient.getId());
        try {
            BeanUtils.copyProperties(current, patient);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        Patient patient = findById(id);
        patient.setDeleted(true);
    }

    @Override
    public Patient findById(String id) {
        for (Patient s : patients) {
            if (s != null) {
                if (!s.isDeleted() && s.getId().equals(id)) {
                    return s;
                }
            }
        }
        return null;
    }

    @Override
    public Patient[] findAll() {
        return Arrays.stream(patients)
                .filter(e -> e != null && !e.isDeleted())
                .toArray(Patient[]::new);
    }

    private void increaseListSize() {
        sizeOfPatientList = patients.length * 2;
        patients = Arrays.copyOf(patients, sizeOfPatientList);
    }

    private String generateId(String id) {
        if (Arrays.stream(patients).filter(e -> e != null && !e.isDeleted()).anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
