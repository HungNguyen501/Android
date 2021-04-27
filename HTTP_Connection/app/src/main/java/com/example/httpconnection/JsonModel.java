package com.example.httpconnection;

class Address {
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;
}

class Geo {
    public String lat;
    public String lng;
}

class Company {
    public String name;
    public String catchPhrase;
    public String bs;
}

public class JsonModel {
    String id;
    String name;
    String username;
    String email;
    Address address;
    String phone;
    String website;
    Company company;

    JsonModel(String id, String name, String username, String email, Address address, String phone, String website, Company company) {
        //constructor is here
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }
}
