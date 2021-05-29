package com.nixsolutions.crudtests;

import com.nixsolutions.mainapp.entities.Company;
import com.nixsolutions.mainapp.entities.Lead;
import org.junit.jupiter.api.*;
import com.nixsolutions.mainapp.service.CompanyService;
import com.nixsolutions.mainapp.service.LeadService;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeadTest {

    private static LeadService leadService = new LeadService();
    private static CompanyService companyService = new CompanyService();

    @BeforeAll
    public static void setUp() {
        Company company = new Company("Company");
        companyService.create(company);
        for (int i = 0; i < 10; i++) {
            Lead lead = new Lead(String.valueOf(i));
            lead.setCompanyId(company.getId());
            leadService.create(lead);
        }
    }

    @Test
    @Order(1)
    public void createLead() {
        Lead lead = new Lead("Lead created");
        leadService.create(lead);
        Lead createdLead = leadService.getLeadByName("Lead created");
        assertEquals(lead, createdLead);
    }

    @Test
    @Order(2)
    public void removeLeadByName() {
        Lead toRemove = leadService.removeLeadByName("3");
        assertFalse(leadService.getAllLeads().contains(toRemove));
    }

    @Test
    @Order(3)
    public void getAllLeads() {
        assertFalse(leadService.getAllLeads().isEmpty());
    }

    @Test
    @Order(4)
    public void updateLeadTest() {
        Lead lead = leadService.getLeadByName("5");
        lead.setName("Lead updated");
        leadService.updateLead(lead);
        assertSame(lead, leadService.getLeadByName("Lead updated"));
    }

    @Test
    @Order(5)
    public void getLeadByName() {
        Lead lead = new Lead("name");
        leadService.create(lead);
        assertSame(lead, leadService.getLeadByName("name"));
    }
}
