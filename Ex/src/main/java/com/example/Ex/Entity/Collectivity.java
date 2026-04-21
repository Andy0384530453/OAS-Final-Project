package com.example.Ex.Entity;

public class Collectivity {




    private String id;
    private String name;
    private String location;
    private String specialty;
    private boolean federationApproval;



    public Collectivity(String id, String name, String location, String specialty, boolean federationApproval) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.specialty = specialty;
        this.federationApproval = federationApproval;
    }


    public String getId() {
        return id;
    }

    public Collectivity setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Collectivity setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Collectivity setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Collectivity setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public boolean isFederationApproval() {
        return federationApproval;
    }

    public Collectivity setFederationApproval(boolean federationApproval) {
        this.federationApproval = federationApproval;
        return this;
    }


    @Override
    public String toString() {
        return "Collectivity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", specialty='" + specialty + '\'' +
                ", federationApproval=" + federationApproval +
                '}';
    }
}
