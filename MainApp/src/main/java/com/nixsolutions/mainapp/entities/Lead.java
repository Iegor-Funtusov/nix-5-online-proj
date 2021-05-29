package com.nixsolutions.mainapp.entities;

import com.nixsolutions.crudlib.BaseEntity;

public class Lead extends BaseEntity {
    String Name;
    String CompanyId;

    public Lead(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    @Override
    public String toString() {
        return "Lead{" +
                "id ='" + super.getId() + '\''+
                "Name='" + Name + '\'' +
                '}';
    }
}
