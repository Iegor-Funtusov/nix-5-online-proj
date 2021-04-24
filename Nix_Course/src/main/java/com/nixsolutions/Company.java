package com.nixsolutions;

public class Company {
    
    private int id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String size;
    private String sizeproof;
    private String revenue;
    private String revenueproof;
    private String industry;
    private String phone;
    
    
    public Company() {
        
    }
    
    public Company(int id, String name, String street, String city, String state, String zip, String country,
            String size, String sizeproof, String revenue, String revenueproof, String industry, String phone) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.size = size;
        this.sizeproof = sizeproof;
        this.revenue = revenue;
        this.revenueproof = revenueproof;
        this.industry = industry;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeproof() {
        return sizeproof;
    }

    public void setSizeproof(String sizeproof) {
        this.sizeproof = sizeproof;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRevenueproof() {
        return revenueproof;
    }

    public void setRevenueproof(String revenueproof) {
        this.revenueproof = revenueproof;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
