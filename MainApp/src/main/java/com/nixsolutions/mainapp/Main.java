package com.nixsolutions.mainapp;

import com.nixsolutions.mainapp.controller.CompanyLeadController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static CompanyLeadController companyLeadController = new CompanyLeadController();

    public static void main(String[] args) throws IOException {
        mainInterface();
    }
        private static void mainInterface() throws IOException {
            int cycle = 1;
            while (cycle != 3) {
                System.out.println("Please, input the number of you choose from 1 to 3 and press Enter" +
                        "\nInput 1 for operation with Lead." +
                        "\nInput 2 for operation with Company." +
                        "\nInput 3 to Exit.");
                cycle = Integer.parseInt(bufferedReader.readLine());
                switch (cycle) {
                    case 1: {
                        LeadInterface();
                        break;
                    }
                    case 2: {
                        companyInterface();
                        break;
                    }
                    default: {
                        System.out.println(" Incorrect input, please try again!");
                    }
                }
            }
        }

        public static void LeadInterface() throws IOException {
            int cycle = 1;
            while (cycle != 8) {
                System.out.println("What operation do you want to perform with Lead? " +
                        "\n Please, input the number of you choose from 1 to 8 and press Enter.");
                System.out.println("1. Create Lead." +
                        "\n2. Read Lead by Name." +
                        "\n3. Read All Leads." +
                        "\n4. Read All Leads by Company Name." +
                        "\n5. Update Lead." +
                        "\n6. Delete Lead." +
                        "\n7. Return to the previous main menu." +
                        "\n8. Exit.");

                cycle = Integer.parseInt(bufferedReader.readLine());
                switch (cycle) {
                    case 1: {
                        companyLeadController.createLead();
                        break;
                    }
                    case 2: {
                        companyLeadController.getLeadByName();
                        break;
                    }
                    case 3: {
                        companyLeadController.showAllLeads();
                        break;
                    }
                    case 4: {
                        companyLeadController.showAllLeadsByCompanyName();
                        break;
                    }
                    case 5: {
                        companyLeadController.updateLead();
                        break;
                    }
                    case 6: {
                        companyLeadController.removeLead();
                        break;
                    }
                    case 7: {
                        mainInterface();
                        break;
                    }
                    default: {
                        System.out.println(" Incorrect input, please try again!");
                    }
                }
            }
        }

        public static void companyInterface() throws IOException {
            int cycle = 1;
            while (cycle != 8) {
                System.out.println("What operation do you want to perform with Company?" +
                        "\n Please, input the number of you choose from 1 to 8 and press Enter.");
                System.out.println("1. Create Company." +
                        "\n2. Read Company by Name." +
                        "\n3. Read All Companies." +
                        "\n4. Read All Leads by Company Name." +
                        "\n5. Update Company." +
                        "\n6. Delete Company." +
                        "\n7. Return to the previous main menu." +
                        "\n8. Exit");
                cycle = Integer.parseInt(bufferedReader.readLine());
                switch (cycle) {
                    case 1: {
                        companyLeadController.createCompany();
                        break;
                    }
                    case 2: {
                        companyLeadController.getCompanyByName();
                        break;
                    }
                    case 3: {
                        companyLeadController.showAllCompanies();
                        break;
                    }
                    case 4: {
                        companyLeadController.showAllLeadsByCompanyName();
                        break;
                    }
                    case 5: {
                        companyLeadController.updateCompany();
                        break;
                    }
                    case 6: {
                        companyLeadController.removeCompany();
                        break;
                    }
                    case 7: {
                        mainInterface();
                        break;
                    }
                    default: {
                        System.out.println(" Incorrect input, please try again!");
                    }
                }
            }
        }
    }
