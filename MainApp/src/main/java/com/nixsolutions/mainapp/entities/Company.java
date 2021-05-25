package com.nixsolutions.mainapp.entities;

import com.nixsolutions.crudlib.BaseEntity;

public class Company extends BaseEntity {
    String companyName;
    String domain;
    String industry;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id ='" + super.getId() + '\''+
                "companyName='" + companyName + '\'' +
                ", domain='" + domain + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
