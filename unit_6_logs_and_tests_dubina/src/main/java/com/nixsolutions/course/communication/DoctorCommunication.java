package com.nixsolutions.course.communication;

import com.nixsolutions.course.entity.Doctor;
import com.nixsolutions.course.entity.Patient;
import com.nixsolutions.course.service.DoctorService;
import com.nixsolutions.course.service.implementation.DoctorServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class DoctorCommunication {
    private final BufferedReader reader;
    private final DoctorService doctorService = new DoctorServiceImpl();

    public DoctorCommunication(BufferedReader reader) {
        this.reader = reader;
    }

    public void createDoctor() throws IOException {
        Doctor doctor = new Doctor();
        System.out.println("Please enter Doctor name: ");
        doctor.setName(reader.readLine());
        doctorService.create(doctor);
        System.out.println("Doctor is created: " + doctor.getName() + " " + doctor.getId());
    }

    public void updateDoctor() throws IOException {
        System.out.println("Please enter ID of Doctor to update: ");
        String doctorId = reader.readLine();
        Doctor doctor = doctorService.findById(doctorId);
        setDoctorInformation(doctor);
        doctorService.update(doctor);
        System.out.println("Doctor is updated: " + doctor.getName() + " " + doctor.getId());
    }

    private void setDoctorInformation(Doctor doctor) throws IOException {
        System.out.println("Please enter new Doctor name: ");
        String docName = reader.readLine();
        if (!docName.isEmpty()) {
            doctor.setName(docName);
        } else
            System.out.println("Please enter valid information.");
    }

    public void deleteDoctorById() throws IOException {
        System.out.println("Please enter ID of Doctor to delete: ");
        String id = reader.readLine();
        doctorService.delete(id);
        System.out.println("Doctor is deleted.");
    }

    public void findAllDoctors() {
        Doctor[] doctors = doctorService.findAll();
        System.out.println("This is a list of Doctors: ");
        System.out.println(Arrays.toString(doctors));
    }

    public void findDoctorById() throws IOException {
        System.out.println("Please enter ID of Doctor which information you want to see: ");
        String id = reader.readLine();
        Doctor doctor = doctorService.findById(id);
        System.out.println(doctor);
    }
}
