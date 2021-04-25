package ua.example1;

public class PatientInformation {

    public String askName(String name) {
        String patientName = name;
        System.out.println("Please enter your name: ");
        return patientName;
    }

    public int askAge(int age) {
        int patientAge = age;
        System.out.println("Please enter your age: ");
        return patientAge;
    }

    public String askSurname(String surname) {
        String patientSurname = surname;
        System.out.println("Please enter your surname: ");
        return patientSurname;
    }

    public void viewPatientInfo(String name, String surname, int age) {
        System.out.println("Please check your information:" +
                "\n" + "Your name is: " + name +
                "\n" + "Your surname is: " + surname +
                "\n" + "Your age is: " + age);
    }
}
