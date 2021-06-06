package com.nixsolutions.course.communication;

import com.nixsolutions.course.entity.Doctor;
import com.nixsolutions.course.entity.Patient;
import com.nixsolutions.course.entity.Visit;
import com.nixsolutions.course.service.DoctorService;
import com.nixsolutions.course.service.PatientService;
import com.nixsolutions.course.service.VisitService;
import com.nixsolutions.course.service.implementation.DoctorServiceImpl;
import com.nixsolutions.course.service.implementation.PatientServiceImpl;
import com.nixsolutions.course.service.implementation.VisitServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class VisitCommunication {
    private final BufferedReader reader;
    private final DoctorService doctorService = new DoctorServiceImpl();
    private final PatientService patientService = new PatientServiceImpl();
    private final VisitService visitService = new VisitServiceImpl();

    public VisitCommunication(BufferedReader reader) {
        this.reader = reader;
    }

    public void createVisit() throws IOException {
        System.out.println("For creating Visit please enter Doctor ID and Patient ID.");
        System.out.println("Please enter Patient ID: ");
        String patientId = reader.readLine();
        System.out.println("Please enter Doctor ID: ");
        String doctorId = reader.readLine();
        Patient patient = patientService.findById(patientId);
        Doctor doctor = doctorService.findById(doctorId);
        Visit visit = new Visit();
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visitService.create(visit);
        System.out.println("Visit is created with ID: " + visit.getId() +
                "\nPatient name: " + patient.getLastName() + " " + patient.getFirstName() + " Patient ID: " + patient.getId() +
                "\nDoctor name: " + doctor.getName() + " Doctor ID: " + doctor.getId());
    }

    public void updateVisitID() throws IOException {
        System.out.println("Please enter ID of Visit you want to change: ");
        String visitId = reader.readLine();
        Visit visit = visitService.findById(visitId);
        updateVisitInformation(visit);
        visitService.update(visit);
        System.out.println("Visit is updated.");
    }

    private void updateVisitInformation(Visit visit) throws IOException {
        System.out.println("Please enter new Patient ID: ");
        String patientId = reader.readLine();
        System.out.println("Please enter new Patient ID: ");
        String doctorId = reader.readLine();
        if (!patientId.isEmpty() && !doctorId.isEmpty()) {
            visit.setPatient(patientService.findById(patientId));
            visit.setDoctor(doctorService.findById(doctorId));
        }
        System.out.println("Sorry, your visit is not updated.");
    }

    public void deleteVisitById() throws IOException {
        System.out.println("Please enter ID of Visit which you want to delete: ");
        String id = reader.readLine();
        visitService.delete(id);
        System.out.println("Visit is deleted.");
    }

    public void findAllVisits() {
        Visit[] visits = visitService.findAll();
        System.out.println("This is a list of Visits: ");
        System.out.println(Arrays.toString(visits));
    }

    public void findVisitById() throws IOException {
        System.out.println("Please enter ID of Visit which information you want to see: ");
        String id = reader.readLine();
        Visit visit = visitService.findById(id);
        System.out.println(visit);
    }
}
