package com.nixsolutions.course.uiMenu;

import com.nixsolutions.course.communication.PatientCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class PatientMenu {
    private final BufferedReader reader;
    private final PatientCommunication patientCommunication;
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public PatientMenu(BufferedReader reader) {
        this.reader = reader;
        patientCommunication = new PatientCommunication(reader);
    }

    public static void actionsList() {
        System.out.println(
                "Please select next number for any action (use Enter for selecting):  " +
                        "\n 1 - Navigate back to main menu" +
                        "\n 2 - Create new Patient" +
                        "\n 3 - Update Patient" +
                        "\n 4 - Delete Patient" +
                        "\n 5 - Show all Patients" +
                        "\n 6 - Show Patient by ID" +
                        "\n   - any other digit for exit."
        );
    }

    public void selectPatientMenu() {
        try {
            actionsList();
            switch (reader.readLine()) {
                case "1": {
                    System.out.println("\n_____________________________________________________\n");
                    MainMenu.menuList();
                }
                break;
                case "2": {
                    System.out.println("Create Patient:");
                    patientCommunication.createPatient();
                    System.out.println("\n_____________________________________________________\n");
                    selectPatientMenu();
                }
                break;
                case "3": {
                    System.out.println("Update Patient:");
                    patientCommunication.findAllPatients();
                    patientCommunication.updatePatient();
                    System.out.println("\n_____________________________________________________\n");
                    selectPatientMenu();
                }
                break;
                case "4": {
                    System.out.println("Delete Patient:");
                    patientCommunication.findAllPatients();
                    patientCommunication.deletePatientById();
                    System.out.println("\n_____________________________________________________\n");
                    selectPatientMenu();
                }
                break;
                case "5": {
                    System.out.println("Show Patients List:");
                    patientCommunication.findAllPatients();
                    System.out.println("\n_____________________________________________________\n");
                    selectPatientMenu();
                }
                break;
                case "6": {
                    System.out.println("Show Patient by ID:");
                    patientCommunication.findPatientById();
                    System.out.println("\n_____________________________________________________\n");
                    selectPatientMenu();
                }
                break;
                default: {
                    System.out.println("Thank you for using this app.");
                    System.exit(0);
                }
            }
        } catch (IOException ex) {
            LOGGER_ERROR.error(ex.getMessage());
        }
    }
}
