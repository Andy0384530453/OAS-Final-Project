package com.example.Ex.Entity;

public class MembershipFee {




    private String id;
    private String collectivityId;
    private String eligibleFrom;
    private String frequency;
    private double amount;
    private String label;
    private String status;

    public MembershipFee() {}

    public MembershipFee(String id, String collectivityId, String eligibleFrom,
                         String frequency, double amount, String label, String status) {
        this.id = id;
        this.collectivityId = collectivityId;
        this.eligibleFrom = eligibleFrom;
        this.frequency = frequency;
        this.amount = amount;
        this.label = label;
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public MembershipFee setId(String id) {
        this.id = id;
        return this;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public MembershipFee setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
        return this;
    }

    public String getEligibleFrom() {
        return eligibleFrom;
    }

    public MembershipFee setEligibleFrom(String eligibleFrom) {
        this.eligibleFrom = eligibleFrom;
        return this;
    }

    public String getFrequency() {
        return frequency;
    }

    public MembershipFee setFrequency(String frequency) {
        this.frequency = frequency;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public MembershipFee setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public MembershipFee setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public MembershipFee setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "MembershipFee{" +
                "id='" + id + '\'' +
                ", collectivityId='" + collectivityId + '\'' +
                ", eligibleFrom='" + eligibleFrom + '\'' +
                ", frequency='" + frequency + '\'' +
                ", amount=" + amount +
                ", label='" + label + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


}