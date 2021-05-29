package com.nixsolutions.mainapp.service;

import com.nixsolutions.mainapp.dao.CompanyLeadDao;
import com.nixsolutions.mainapp.entities.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;

public class LeadService {

    private static final Logger loggerInfo = LoggerFactory.getLogger("info");
    private static final Logger loggerWarn = LoggerFactory.getLogger("warn");
    private static final Logger loggerError = LoggerFactory.getLogger("error");

    CompanyLeadDao companyLeadDao = CompanyLeadDao.getInstance();

    public Lead create(Lead lead) {
        if (lead != null) {
            if (lead.getName().isEmpty()) {
                loggerWarn.warn("Lead Name is empty");
            }
            companyLeadDao.getLeadDao().create(lead);
            loggerInfo.info("Create Lead with id - " + lead.getId());
            return lead;
        }
        loggerError.error("Lead is null");
        throw new NullPointerException("Lead can't be null");
    }

    public void updateLead(Lead lead) {
        if (lead != null) {
            loggerInfo.info("Update Lead with id - " + lead.getId());
            companyLeadDao.getLeadDao().update(lead);
        } else {
            loggerError.error("Lead is null");
            throw new NullPointerException("Lead can't be null");
        }
    }

    public Lead getLeadByName(String name) {
        if (name != null) {
            Lead leadName = companyLeadDao.getLeadByName(name);
            loggerInfo.info("Get Lead by name id - " + leadName.getId());
            return leadName;
        }
        loggerError.error("Lead Name is null");
        throw new NullPointerException("Lead Name can't be null");
    }

    public Collection<Lead> getAllLeads() {
        loggerInfo.info("Get all Leads collection");
        Collection<Lead> leads = companyLeadDao.getLeadDao().readAll();
        if (leads != null) {
            return leads;
        }
        loggerError.error("Can't get all Leads");
        throw new NullPointerException("Lead collection is not exist");
    }

    public Lead removeLeadByName(String name) {
        if (name != null) {
            Lead lead = companyLeadDao.removeLead(name);
            loggerInfo.info("Remove Lead with id - " + lead.getId());
            return lead;
        }
        loggerError.error("Lead name is null");
        throw new NullPointerException("Lead Name can't be null");
    }
}
