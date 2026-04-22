package com.example.Ex.Entity;

public class CollectivityStructure {

    private String collectivityId;
    private  String presidentId;
    private String vicePresidentId;
    private String treasurerId;
    private String secretaryId;
    private int mandateYear;




    public CollectivityStructure(String collectivityId, String presidentId,
                                 String vicePresidentId, String treasurerId, String secretaryId,
                                 int mandateYear) {
        this.collectivityId = collectivityId;
        this.presidentId = presidentId;
        this.vicePresidentId = vicePresidentId;
        this.treasurerId = treasurerId;
        this.secretaryId = secretaryId;
        this.mandateYear = mandateYear;
    }



    public String getCollectivityId() {
        return collectivityId;
    }

    public CollectivityStructure setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
        return this;
    }

    public String getPresidentId() {
        return presidentId;
    }

    public CollectivityStructure setPresidentId(String presidentId) {
        this.presidentId = presidentId;
        return this;
    }

    public String getVicePresidentId() {
        return vicePresidentId;
    }

    public CollectivityStructure setVicePresidentId(String vicePresidentId) {
        this.vicePresidentId = vicePresidentId;
        return this;
    }

    public String getTreasurerId() {
        return treasurerId;
    }

    public CollectivityStructure setTreasurerId(String treasurerId) {
        this.treasurerId = treasurerId;
        return this;
    }

    public String getSecretaryId() {
        return secretaryId;
    }

    public CollectivityStructure setSecretaryId(String secretaryId) {
        this.secretaryId = secretaryId;
        return this;
    }

    public int getMandateYear() {
        return mandateYear;
    }

    public CollectivityStructure setMandateYear(int mandateYear) {
        this.mandateYear = mandateYear;
        return this;
    }


    @Override
    public String toString() {
        return "CollectivityStructure{" +
                "collectivityId='" + collectivityId + '\'' +
                ", presidentId='" + presidentId + '\'' +
                ", vicePresidentId='" + vicePresidentId + '\'' +
                ", treasurerId='" + treasurerId + '\'' +
                ", secretaryId='" + secretaryId + '\'' +
                ", mandateYear=" + mandateYear +
                '}';
    }


}
