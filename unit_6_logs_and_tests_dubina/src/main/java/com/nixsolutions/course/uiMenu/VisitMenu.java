package com.nixsolutions.course.uiMenu;

import com.nixsolutions.course.communication.DoctorCommunication;
import com.nixsolutions.course.communication.PatientCommunication;
import com.nixsolutions.course.communication.VisitCommunication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class VisitMenu {
    private final BufferedReader reader;
    private final VisitCommunication visitCommunication;
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    DoctorCommunication doctorCommunication;
    PatientCommunication patientCommunication;

    public VisitMenu(BufferedReader reader) {
        this.reader = reader;
        visitCommunication = new VisitCommunication(reader);
    }

    public static void actionsList() {
        System.out.println(
                "Please select next number for any action (use Enter for selecting):  " +
                        "\n 1 - Navigate back to main menu" +
                        "\n 2 - Create Visit" +
                        "\n 3 - Update Visit" +
                        "\n 4 - Delete Visit" +
                        "\n 5 - Show all Visits" +
                        "\n 6 - Show Visit by ID" +
                        "\n   - any other digit for exit."
        );
    }

    public void visitMenu() {
        try {
            actionsList();
            switch (reader.readLine()) {
                case "1": {
                    System.out.println("\n_____________________________________________________\n");
                    MainMenu.menuList();
                }
                break;
                case "2": {
                    System.out.println("Create Visit:");
                    visitCommunication.createVisit();
                    System.out.println("\n_____________________________________________________\n");
                    visitMenu();
                }
                break;
                case "3": {
                    System.out.println("Update Visit:");
                    visitCommunication.findAllVisits();
                    visitCommunication.updateVisitID();
                    System.out.println("\n_____________________________________________________\n");
                    visitMenu();
                }
                break;
                case "4": {
                    System.out.println("Delete Visit:");
                    visitCommunication.findAllVisits();
                    visitCommunication.deleteVisitById();
                    System.out.println("\n_____________________________________________________\n");
                    visitMenu();
                }
                break;
                case "5": {
                    System.out.println("Show all Visits:");
                    visitCommunication.findAllVisits();
                    System.out.println("\n_____________________________________________________\n");
                    visitMenu();
                }
                break;
                case "6": {
                    System.out.println("Find Visit by ID:");
                    visitCommunication.findVisitById();
                    System.out.println("\n_____________________________________________________\n");
                    visitMenu();
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
