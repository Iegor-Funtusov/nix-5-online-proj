package com.nixsolutions.course.uiMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void actionsList() {
        System.out.println(
                "Please select next number for navigating to menu option (use Enter for selecting):  " +
                        "\n 1 - Patient Menu" +
                        "\n 2 - Doctor Menu" +
                        "\n 3 - Visit Register" +
                        "\n   - any other digit for exit."
        );
    }

    public static void menuList() {
        try {
            actionsList();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            switch (reader.readLine()) {
                case "1": {
                    PatientMenu patientMenu = new PatientMenu(reader);
                    System.out.println("\n_____________________________________________________\n");
                    System.out.println("Patient Menu Selected.");
                    patientMenu.selectPatientMenu();
                }
                break;
                case "2": {
                    DoctorMenu doctorMenu = new DoctorMenu(reader);
                    System.out.println("\n_____________________________________________________\n");
                    System.out.println("Doctor Menu Selected.");
                    doctorMenu.selectDoctorMenu();
                }
                break;
                case "3": {
                    VisitMenu visitMenu = new VisitMenu(reader);
                    System.out.println("\n_____________________________________________________\n");
                    System.out.println("Visit Register Selected.");
                    visitMenu.visitMenu();
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
