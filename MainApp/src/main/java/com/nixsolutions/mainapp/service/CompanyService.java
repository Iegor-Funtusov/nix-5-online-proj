package com.nixsolutions.mainapp.service;

import com.nixsolutions.mainapp.dao.CompanyLeadDao;
import com.nixsolutions.mainapp.entities.Company;
import com.nixsolutions.mainapp.entities.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class CompanyService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    CompanyLeadDao companyLeadDao = CompanyLeadDao.getInstance();

    public Company create(Company company) {
        if (company != null) {
            if (company.getCompanyName().isEmpty()) {
                loggerWarn.warn("Company Name is empty");
            }
            companyLeadDao.getCompanyDao().create(company);
            loggerInfo.info("Create new Company with Name - " + company.getCompanyName() +
                    " and id - " + company.getId());
            return company;
        }
        loggerError.error("Company can't be null");
        throw new NullPointerException("Company is null");
    }

    public void updateCompany(Company company) {
        if (company != null) {
            companyLeadDao.getCompanyDao().update(company);
            loggerInfo.info("Company with id - " + company.getId() + " updated");
        } else {
            loggerError.error("Company is null");
            throw new NullPointerException("Company can't be null");
        }
    }

    public Company getCompanyByName(String name) {
        if (name != null) {
            if (name.isEmpty()) {
                loggerWarn.warn("Company Name is empty");
            }
            Company companyByName = companyLeadDao.getCompanyByName(name);
            loggerInfo.info("Get Company with id - " + companyByName.getId());
            return companyByName;
        }
        loggerError.error("Company Name can't be null");
        throw new NullPointerException("Company Name can't be null");
    }

    public Collection<Company> getAllCompanies() {
        Collection<Company> companies = companyLeadDao.readCompaniesCollection();
        loggerInfo.info("Get all Companies collection");
        return companies;
    }

    public Collection<Lead> getAllCompanyLeads(String companyName) {
        if (companyName != null) {
            if (companyName.isEmpty()) {
                loggerWarn.warn("Company Name is empty");
            }
            loggerInfo.info("Get all Leads with Company Name - " + companyName);
            return companyLeadDao.getAllLeadsByCompanyName(companyName);

        } else {
            loggerError.error("Company Name is null");
            throw new NullPointerException("Company Name can't be null");
        }
    }

    public Company removeCompanyByName(String name) {
        if (name == null) {
            loggerError.error("Company Name is null");
            throw new NullPointerException("Company Name can't be null");
        }
        Company company = companyLeadDao.removeCompany(name);
        loggerInfo.info("Remove Company with name - " +  company.getCompanyName() +
                " and id - " + company.getId());
        return company;
    }
}
