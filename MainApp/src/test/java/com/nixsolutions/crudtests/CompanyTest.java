package com.nixsolutions.crudtests;

import com.nixsolutions.mainapp.entities.Company;
import com.nixsolutions.mainapp.entities.Lead;
import org.junit.jupiter.api.*;
import com.nixsolutions.mainapp.service.CompanyService;
import com.nixsolutions.mainapp.service.LeadService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CompanyTest {

    private static LeadService leadService = new LeadService();
    private static CompanyService companyService = new CompanyService();

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < 10; i++) {
            Company company = new Company(String.valueOf(i));
            companyService.create(company);
        }
    }

    @Test
    @Order(1)
    public void createCompany() {
        Company company = new Company("Microsoft");
        companyService.create(company);
        Company companyByName = companyService.getCompanyByName(company.getCompanyName());
        assertEquals(company, companyByName);
    }

    private void createCompaniesAndLeads() {
        Company company = new Company("Company and Leads");
        companyService.create(company);
        Lead john = new Lead("John");
        john.setCompanyId(company.getId());
        Lead ivan = new Lead("Ivan");
        ivan.setCompanyId(company.getId());
        leadService.create(john);
        leadService.create(ivan);
    }

    @Test
    @Order(2)
    public void getAllCompanyLeads() {
        createCompaniesAndLeads();
        Collection<Lead> companiesAndLeads = companyService.getAllCompanyLeads("Company and Leads");
        assertEquals(2, companiesAndLeads.size());
    }

    @Test
    @Order(3)
    public void getAllCompany() {
        assertFalse(companyService.getAllCompanies().isEmpty());
    }

    @Test
    @Order(4)
    public void updateCompanyTest() {
        Company companyByName = companyService.getCompanyByName("5");
        companyByName.setCompanyName("upd");
        companyService.updateCompany(companyByName);
        assertSame(companyByName, companyService.getCompanyByName("upd"));
    }

    @Test
    @Order(5)
    public void getCompanyByName() {
        Company company = new Company("company");
        companyService.create(company);
        assertSame(company, companyService.getCompanyByName("company"));
    }

    @Test
    @Order(6)
    public void removeCompanyByName() {
        Company company = companyService.removeCompanyByName("2");
        System.out.println(company);
        assertFalse(companyService.getAllCompanies().contains(company));
    }
}
