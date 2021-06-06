package com.nixsolutions.course.service.implementation;

import com.nixsolutions.course.dao.PatientDao;
import com.nixsolutions.course.dao.implementation.PatientDaoImpl;
import com.nixsolutions.course.entity.Patient;
import com.nixsolutions.course.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientServiceImpl implements PatientService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final PatientDao patientDao = new PatientDaoImpl();

    @Override
    public void create(Patient patient) {
        if (isPatientExist(patient)) {
            LOGGER_INFO.info("Start create patient: " + patient.getLastName());
            patientDao.create(patient);
            LOGGER_INFO.info("End create patient: " + patient.getLastName());
        }
    }

    @Override
    public void update(Patient patient) {
        if (isPatientExist(patient) && isPatientExistById(patient.getId())) {
            LOGGER_WARN.warn("Start update patient: " + patient.getId());
            patientDao.update(patient);
            LOGGER_WARN.warn("End update patient: " + patient.getId());
        }
    }

    @Override
    public void delete(String id) {
        if (isPatientExistById(id)) {
            LOGGER_WARN.warn(("Start delete patient by ID: " + id));
            patientDao.delete(id);
            LOGGER_WARN.warn(("End delete patient by ID: " + id));
        }
    }

    @Override
    public Patient findById(String id) {
        return patientDao.findById(id);
    }

    @Override
    public Patient[] findAll() {
        return patientDao.findAll();
    }

    private boolean isPatientExist(Patient patient) {
        if (patient != null) {
            return true;
        } else {
            LOGGER_ERROR.error("Patient doesn't exist.");
            return false;
        }
    }

    private boolean isPatientExistById(String id) {
        if (patientDao.findById(id) != null) {
            return true;
        } else {
            LOGGER_ERROR.error("Patient doesn't exist.");
            return false;
        }
    }
}
