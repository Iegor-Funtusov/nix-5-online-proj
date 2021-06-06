package com.nixsolutions.course.uiMenu;

import com.nixsolutions.course.communication.DoctorCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class DoctorMenu {
    private final BufferedReader reader;
    private final DoctorCommunication doctorCommunication;
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public DoctorMenu(BufferedReader reader) {
        this.reader = reader;
        doctorCommunication = new DoctorCommunication(reader);
    }

    public static void actionsList() {
        System.out.println(
                "Please select next number for any action (use Enter for selecting):  " +
                        "\n 1 - Navigate back to main menu" +
                        "\n 2 - Create new Doctor" +
                        "\n 3 - Update Doctor" +
                        "\n 4 - Delete Doctor" +
                        "\n 5 - Show all Doctors" +
                        "\n 6 - Show Doctor by ID" +
                        "\n   - any other digit for exit."
        );
    }

    public void selectDoctorMenu() {
        try {
            actionsList();
            switch (reader.readLine()) {
                case "1": {
                    System.out.println("\n_____________________________________________________\n");
                    MainMenu.menuList();
                }
                break;
                case "2": {
                    System.out.println("Create Doctor:");
                    doctorCommunication.createDoctor();
                    System.out.println("\n_____________________________________________________\n");
                    selectDoctorMenu();
                }
                break;
                case "3": {
                    System.out.println("Update Doctor:");
                    doctorCommunication.findAllDoctors();
                    doctorCommunication.updateDoctor();
                    System.out.println("\n_____________________________________________________\n");
                    selectDoctorMenu();
                }
                break;
                case "4": {
                    System.out.println("Delete Doctor:");
                    doctorCommunication.findAllDoctors();
                    doctorCommunication.deleteDoctorById();
                    System.out.println("\n_____________________________________________________\n");
                    selectDoctorMenu();
                }
                break;
                case "5": {
                    System.out.println("Show all Doctors list:");
                    doctorCommunication.findAllDoctors();
                    System.out.println("\n_____________________________________________________\n");
                    selectDoctorMenu();
                }
                break;
                case "6": {
                    System.out.println("Fin Doctor by ID:");
                    doctorCommunication.findDoctorById();
                    System.out.println("\n_____________________________________________________\n");
                    selectDoctorMenu();
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
