package com.nixsolutions.mainapp.controller;

import com.nixsolutions.mainapp.entities.Company;
import com.nixsolutions.mainapp.entities.Lead;
import com.nixsolutions.mainapp.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompanyLeadController {

    private static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    CompanyService companyService = new CompanyService();
    LeadService leadService = new LeadService();


    public void createCompany() throws IOException {
        System.out.println("To add Company, please, input Company Name and press Enter.");
        String companyName = bufferedReader.readLine();
        companyService.create(new Company(companyName));
    }

    public void createLead() throws IOException {
        System.out.println("To add Lead, please, input Lead Name and press Enter.");
        String leadName = bufferedReader.readLine();
        System.out.println("Please input Lead's Company Name and press Enter.");
        String companyName = bufferedReader.readLine();
        Lead lead = new Lead(leadName);
        Company companyByName = companyService.getCompanyByName(companyName);
        lead.setCompanyId(companyByName.getId());
        leadService.create(lead);
    }

    public void showAllCompanies() {
        System.out.println(companyService.getAllCompanies());
    }

    public void showAllLeads() {
        System.out.println(leadService.getAllLeads());
    }

    public void removeLead() throws IOException {
        System.out.println("Please, input Lead's Name that you want to remove and press Enter.");
        String leadName = bufferedReader.readLine();
        leadService.removeLeadByName(leadName);
    }

    public void removeCompany() throws IOException {
        System.out.println("Please, input Company Name that you want to remove and press Enter.");
        String companyName = bufferedReader.readLine();
        companyService.removeCompanyByName(companyName);
    }

    public void showAllLeadsByCompanyName() throws IOException {
        System.out.println("Please, input Company Name and press Enter.");
        String companyName = bufferedReader.readLine();
        System.out.println(companyService.getAllCompanyLeads(companyName));
    }

    public void getCompanyByName() throws IOException {
        System.out.println("Please, input Company Name and press Enter.");
        String companyName = bufferedReader.readLine();
        System.out.println(companyService.getCompanyByName(companyName));
    }

    public void getLeadByName() throws IOException {
        System.out.println("Please, input Lead Name and press Enter.");
        String leadName = bufferedReader.readLine();
        System.out.println(leadService.getLeadByName(leadName));
    }

    public void updateCompany() throws IOException {
        System.out.println("Please, input Company Name that you want to update and press Enter.");
        String companyName = bufferedReader.readLine();
        Company companyByName = companyService.getCompanyByName(companyName);
        System.out.println("Please, input new Company Name and press Enter.");
        String updatedName = bufferedReader.readLine();
        companyByName.setCompanyName(updatedName);
        companyService.updateCompany(companyByName);
    }

    public void updateLead() throws IOException {
        System.out.println("Please, input Lead Name that you want to update and press Enter.");
        String leadName = bufferedReader.readLine();
        Lead leadByName = leadService.getLeadByName(leadName);
        boolean cycle = true;
        while (cycle) {
            System.out.println(" Please, input 1 to change Lead Name." +
                    "\n Please, input 2 to change Lead's Company Name." +
                    "\n Please, input 3 to Exit." +
                    "\n Please, use Enter after your input.");
            int choose = Integer.parseInt(bufferedReader.readLine());
            switch (choose) {
                case 1: {
                    System.out.println("Please, input Lead's new Name and press Enter.");
                    String updatedLeadName = bufferedReader.readLine();
                    leadByName.setName(updatedLeadName);
                    break;
                }
                case 2: {
                    System.out.println("Please, input new Lead's Company Name and press Enter.");
                    String updatedLeaCompany = bufferedReader.readLine();
                    Company companyByName = companyService.getCompanyByName(updatedLeaCompany);
                    leadByName.setCompanyId(companyByName.getId());
                    break;
                }
                case 3: {
                    cycle = false;
                    break;
                }
                default: {
                    System.out.println(" Incorrect input, please try again!");
                }
            }
        }
        leadService.updateLead(leadByName);
    }
}
