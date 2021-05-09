import java.util.Scanner;

public class PatientRegistry {

    public static void startApp() {
        System.out.println("Hello User. \nYou lunch application which imitating hospital registry work.\n");
        PatientService patientService = new PatientService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select your next action by inputting next number: \n" +
                    "- 1 - Add new patient \n" +
                    "- 2 - Edit existing patient information by ID \n" +
                    "- 3 - Delete patient by ID\n" +
                    "- 4 - View patient's information \n" +
                    "Input any other number for finish session.");

            switch (scanner.next()) {
                case "1": {
                    System.out.println("Enter patient name, age and insurance number (use Space): ");
                    Patient newPatient = new Patient().setName(scanner.next()).setAge(scanner.nextInt())
                            .setInsuranceNumber(scanner.nextInt());
                    patientService.create(newPatient);
                    break;
                }
                case "2": {
                    System.out.println("Enter patient ID:");
                    String id = scanner.next();
                    Patient currentPatient;
                    if ((currentPatient = patientService.read(id)) == null) {
                        System.out.println("Wrong id");
                        break;
                    }
                    System.out.println("Please select your next action by inputting next number: \n" +
                            "- 1 - Edit patient name \n" +
                            "- 2 - Edit patient age \n" +
                            "- 3 - Edit patient insurance information \n" +
                            "Input any other number for finish session.");
                    switch (scanner.next()) {
                        case "1": {
                            System.out.println("Enter new name:");
                            patientService.update(currentPatient.setName(scanner.next()));
                            System.out.println("Information updated");
                            break;
                        }
                        case "2": {
                            System.out.println("Enter new age:");
                            patientService.update(currentPatient.setAge(scanner.nextInt()));
                            System.out.println("Information updated");
                            break;
                        }
                        case "3": {
                            System.out.println("Enter new insurance number:");
                            patientService.update(currentPatient.setInsuranceNumber(scanner.nextInt()));
                            System.out.println("Information updated");
                            break;
                        }
                        default:
                            System.exit(0);
                    }
                    break;
                }
                case "3": {
                    System.out.println("Enter patient ID:");
                    try {
                        patientService.delete(scanner.next());
                    } catch (RuntimeException ex) {
                        System.err.println("Something went wrong :(");
                    }
                    System.out.println("Patient deleted.");
                    break;
                }
                case "4": {
                    System.out.println(patientService.read());
                    break;
                }
                default:
                    System.exit(0);
            }
            scanner.nextLine();
        }
    }
}
