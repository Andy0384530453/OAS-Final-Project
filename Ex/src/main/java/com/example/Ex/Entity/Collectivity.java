package com.example.Ex.Entity;

public class Collectivity {

    private String id;
    private String number;
    private String name;
    private String location;
    private String specialty;
    private boolean federationApproval;

    public Collectivity(String id, String number, String name, String location, String specialty, boolean federationApproval) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.location = location;
        this.specialty = specialty;
        this.federationApproval = federationApproval;
    }

    public String getId() {
        return id;
    }
    public Collectivity setId(String id) {
        this.id = id; return this;
    }

    public String getNumber() {
        return number;
    }
    public Collectivity setNumber(String number) {
        this.number = number; return this;
    }

    public String getName() {
        return name;
    }
    public Collectivity setName(String name) {
        this.name = name; return this;
    }

    public String getLocation() {
        return location;
    }
    public Collectivity setLocation(String location) {
        this.location = location; return this;
    }

    public String getSpecialty() {
        return specialty;
    }
    public Collectivity setSpecialty(String specialty) {
        this.specialty = specialty; return this;
    }

    public boolean isFederationApproval() {
        return federationApproval;
    }
    public Collectivity setFederationApproval(boolean federationApproval) {
        this.federationApproval = federationApproval; return this;
    }
}