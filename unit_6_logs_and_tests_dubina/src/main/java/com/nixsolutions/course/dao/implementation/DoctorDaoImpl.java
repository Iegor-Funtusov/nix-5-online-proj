package com.nixsolutions.course.dao.implementation;

import com.nixsolutions.course.dao.DoctorDao;
import com.nixsolutions.course.entity.Doctor;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Arrays;
import java.util.UUID;

public class DoctorDaoImpl implements DoctorDao {
    private static int sizeOfDoctorsList = 100;
    private int countDoctors = 0;

    private static Doctor[] doctors = new Doctor[sizeOfDoctorsList];

    @Override
    public void create(Doctor doctor) {
        if (countDoctors + 1 > doctors.length) {
            increaseListSize();
        }
        doctor.setId(generateId(UUID.randomUUID().toString()));
        doctors[countDoctors] = doctor;
        countDoctors++;
    }


    @Override
    public void update(Doctor doctor) {
        Doctor current = findById(doctor.getId());
        try {
            BeanUtils.copyProperties(current, doctor);
        } catch (Exception ex) {
            System.out.println("Doctor is not created.");
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        Doctor doctor = findById(id);
        doctor.setDeleted(true);
    }

    @Override
    public Doctor findById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor != null) {
                if (!doctor.isDeleted() && doctor.getId().equals(id)) {
                    return doctor;
                }
            }
        }
        return null;
    }

    @Override
    public Doctor findByName(String name) {
        for (Doctor entity : doctors) {
            if (entity != null) {
                if (!entity.isDeleted() && entity.getName().equals(name)) {
                    return entity;
                }
            }
        }
        return null;
    }

    @Override
    public Doctor[] findAll() {
        return Arrays.stream(doctors)
                .filter(e -> e != null && !e.isDeleted())
                .toArray(Doctor[]::new);
    }

    private void increaseListSize() {
        sizeOfDoctorsList = doctors.length * 2;
        doctors = Arrays.copyOf(doctors, sizeOfDoctorsList);
    }

    private String generateId(String id) {
        if (Arrays.stream(doctors).filter(e -> e != null && !e.isDeleted()).anyMatch(e -> e.getId().equals(id))) {
            return generateId(UUID.randomUUID().toString());
        }
        return id;
    }
}
