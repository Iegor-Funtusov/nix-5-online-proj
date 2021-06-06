package com.nixsolutions.course.communication;

import com.nixsolutions.course.entity.Patient;
import com.nixsolutions.course.service.PatientService;
import com.nixsolutions.course.service.implementation.PatientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class PatientCommunication {
    private final BufferedReader reader;
    private final PatientService patientService = new PatientServiceImpl();

    public PatientCommunication(BufferedReader reader) {
        this.reader = reader;
    }

    public void createPatient() throws IOException {
        Patient patient = new Patient();
        System.out.println("Please enter Patient first name: ");
        patient.setFirstName(reader.readLine());
        System.out.println("Please enter Patient last name: ");
        patient.setLastName(reader.readLine());
        patientService.create(patient);
        System.out.println("Patient is created: " + patient.getLastName() + " " + patient.getFirstName() + " " + patient.getId());
    }

    public void updatePatient() throws IOException {
        System.out.println("Please enter ID of Patient to update: ");
        String patientId = reader.readLine();
        Patient patient = patientService.findById(patientId);
        setPatientInformation(patient);
        patientService.update(patient);
        System.out.println("Patient is updated: " + patient.getLastName() + " " + patient.getFirstName() + " " + patient.getId());
    }

    private void setPatientInformation(Patient patient) throws IOException {
        System.out.println("Please enter new Patient first name: ");
        String firstName = reader.readLine();
        System.out.println("Please enter new Patient last name: ");
        String lastName = reader.readLine();
        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
        } else
            System.out.println("Please enter valid information.");
    }

    public void deletePatientById() throws IOException {
        System.out.println("Please enter ID of Patient which you want to delete: ");
        String idPatient = reader.readLine();
        patientService.delete(idPatient);
        System.out.println("Patient is deleted.");
    }

    public void findAllPatients() {
        Patient[] patients = patientService.findAll();
        System.out.println("This is a list of Patients: ");
        System.out.println(Arrays.toString(patients));
    }

    public void findPatientById() throws IOException {
        System.out.println("Please enter ID of Patient which information you want to see: ");
        String id = reader.readLine();
        Patient patient = patientService.findById(id);
        System.out.println(patient);
    }
}
