package com.example.Ex.Entity;

public class MobileBankingAccount extends FinancialAccount {




    private String holderName;
    private String mobileBankingService;
    private String mobileNumber;

    public MobileBankingAccount(String holderName) {
        this.holderName = holderName;
    }

    public MobileBankingAccount(String id, double amount, String holderName) {
        super(id, amount);
        this.holderName = holderName;
    }


    public String getHolderName() {
        return holderName;
    }

    public MobileBankingAccount setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public String getMobileBankingService() {
        return mobileBankingService;
    }

    public MobileBankingAccount setMobileBankingService(String mobileBankingService) {
        this.mobileBankingService = mobileBankingService;
        return this;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public MobileBankingAccount setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    @Override
    public String toString() {
        return "MobileBankingAccount{" +
                "holderName='" + holderName + '\'' +
                ", mobileBankingService='" + mobileBankingService + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }


}