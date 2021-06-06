package com.nixsolutions.course.entity;

public class Visit {
    private String id;
    private Doctor doctor;
    private Patient patient;
    private boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id='" + id + '\'' +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
}
