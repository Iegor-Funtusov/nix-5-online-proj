package com.nixsolutions.course.service.implementation;

import com.nixsolutions.course.dao.DoctorDao;
import com.nixsolutions.course.dao.implementation.DoctorDaoImpl;
import com.nixsolutions.course.entity.Doctor;
import com.nixsolutions.course.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoctorServiceImpl implements DoctorService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public void create(Doctor doctor) {
        if (!findDoctorByName(doctor)) {
            LOGGER_INFO.info("Start to create doctor: " + doctor.getName());
            doctorDao.create(doctor);
            LOGGER_INFO.info("End to create doctor: " + doctor.getName());
        }
    }

    @Override
    public void update(Doctor doctor) {
        if (!findDoctorByName(doctor) && findDoctorById(doctor.getId())) {
            LOGGER_WARN.warn("Start update doctor: " + doctor.getName());
            doctorDao.update(doctor);
            LOGGER_WARN.warn("End update doctor: " + doctor.getName());
        }
    }

    @Override
    public void delete(String id) {
        if (findDoctorById(id)) {
            LOGGER_WARN.warn(("Start delete doctor by ID: " + id));
            doctorDao.delete(id);
            LOGGER_WARN.warn(("End delete doctor by ID: " + id));
        }
    }

    @Override
    public Doctor findById(String id) {
        return doctorDao.findById(id);
    }

    @Override
    public Doctor findByName(String name) {
        return doctorDao.findByName(name);
    }

    @Override
    public Doctor[] findAll() {
        return doctorDao.findAll();
    }

    private boolean findDoctorByName(Doctor doctor) {
        if (doctor != null && doctor.getName() != null) {
            Doctor doctor1 = findByName(doctor.getName());
            if (doctor1 == null) {
                LOGGER_ERROR.error("This doctor doesn't exist.");
                return false;
            } else {
                LOGGER_ERROR.error("This doctor is exist: " + doctor.getName());
            }
        } else {
            LOGGER_ERROR.error("This doctor doesn't exist.");
        }
        return true;
    }

    private boolean findDoctorById(String id) {
        if (doctorDao.findById(id) != null) {
            LOGGER_INFO.info("This doctor is exist: " + id);
            return true;
        } else {
            LOGGER_ERROR.error("This doctor doesn't exist.");
            return false;
        }
    }
}
