package ua.example;

import java.util.Scanner;

public class Patient {
    private static String name;
    private static int age;
    private static String surname;

    public static void main(String[] args) {
        Scanner patientInfo = new Scanner(System.in);

        PatientInformation getPatientInfo = new PatientInformation();
        PatientCommunication.greeting();

        PatientCommunication.firstMeet();

        getPatientInfo.askName(name);
        name = patientInfo.nextLine();
        getPatientInfo.askSurname(surname);
        surname = patientInfo.nextLine();
        getPatientInfo.askAge(age);
        age = patientInfo.nextInt();

        PatientCommunication.congratulations();
        getPatientInfo.viewPatientInfo(name, surname, age);

    }
}
