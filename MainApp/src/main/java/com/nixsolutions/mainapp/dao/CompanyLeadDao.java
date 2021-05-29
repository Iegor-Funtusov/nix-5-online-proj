package com.nixsolutions.mainapp.dao;

import com.nixsolutions.crudlib.CrudFactory;
import com.nixsolutions.crudlib.CrudService;
import com.nixsolutions.mainapp.entities.Company;
import com.nixsolutions.mainapp.entities.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Getter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
public class CompanyLeadDao {

    private static final Logger loggerError = LoggerFactory.getLogger("error");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerInfo = LoggerFactory.getLogger("info");


    CrudService<Lead> leadDao = CrudFactory.getInstance().getCrudService();
    CrudService<Company> companyDao = CrudFactory.getInstance().getCrudService();

    private static CompanyLeadDao companyLeadDao = new CompanyLeadDao();

    private CompanyLeadDao() {
    }

    public static CompanyLeadDao getInstance() {

        if (companyLeadDao == null) {
            loggerInfo.info("New Company and Lead DAO");
            return new CompanyLeadDao();
        }
        loggerInfo.info("Return Company and Lead DAO");
        return companyLeadDao;
    }

    public Collection<Lead> readLeadsCollection() {
        loggerInfo.info("Read all Leads");
        return leadDao.readAll();
    }

    public Collection<Company> readCompaniesCollection() {
        loggerInfo.info("Read all Company");
        return companyDao.readAll();
    }

    public Collection<Lead> getAllLeadsByCompanyName(String name) {
        if (name != null) {
            loggerInfo.info("Return all Leads by Company name", name);
            Set<Lead> setCollect = new HashSet<>();
            for (Lead lead : readLeadsCollection()) {
                if (lead.getCompanyId() != null && lead.getCompanyId().equals(getCompanyByName(name).getId())) {
                    setCollect.add(lead);
                }
            }
            return setCollect;
        }
        loggerError.error("Company Name is null");
        throw new NullPointerException("Company Name is null");
    }

    public Company removeCompany(String name) {
        if (name != null) {
            Collection<Lead> allLeadsByCompanyName = getAllLeadsByCompanyName(name);
            loggerInfo.info("Return all Leads by Company Name", name);
            Company company = getCompanyByName(name);
            companyDao.delete(company.getId());
            loggerInfo.info("Delete Company with id", company.getId());
            loggerWarn.warn("All Leads will be deleted");
            for (Lead lead : allLeadsByCompanyName) {
                leadDao.delete(lead.getId());
                loggerInfo.info("Lead with id " + lead.getId() + " deleted");
            }
            return company;
        }
        loggerError.error("Company Name is null");
        throw new NullPointerException("Company Name can't be null");
    }

    public Company getCompanyByName(String companyName) {
        if (companyName != null) {
            Company company = readCompaniesCollection().stream()
                    .filter(lead -> lead.getCompanyName().equals(companyName))
                    .findFirst()
                    .orElseThrow(() -> {
                        loggerError.error("Can't find Company with entered name", companyName);
                        return new RuntimeException(String.format("Company with entered name: %s doesn`t exists", companyName));
                    });
            loggerInfo.info("Return Company id by name ", company.getId(), companyName);
            return company;
        }
        loggerError.error("Company Name is null");
        throw new NullPointerException("Company Name can't be null");
    }

    public Lead getLeadByName(String leadName) {
        if (leadName != null) {
            Lead lead = readLeadsCollection().stream()
                    .filter(leads -> leads.getName().equals(leadName))
                    .findFirst()
                    .orElseThrow(() -> {
                        loggerError.error("Can't find Lead with this name ", leadName);
                        return new RuntimeException("Lead with name " + leadName + "doesn`t exists");
                    });

            return lead;
        }
        loggerError.error("Lead name is null");
        throw new NullPointerException("Lead name can't be null");
    }

    public Lead removeLead(String name) {
        if (name != null) {
            Lead lead = getLeadByName(name);
            leadDao.delete(lead.getId());
            return lead;
        }
        loggerError.error("Lead Name is null");
        throw new NullPointerException("Lead Name can't be null");
    }
}
