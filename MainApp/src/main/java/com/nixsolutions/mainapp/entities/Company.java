package com.nixsolutions.mainapp.entities;

import com.nixsolutions.crudlib.BaseEntity;

public class Company extends BaseEntity {
    String companyName;

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id ='" + super.getId() + '\''+
                "companyName='" + companyName + '\'' +
                '}';
    }
}
