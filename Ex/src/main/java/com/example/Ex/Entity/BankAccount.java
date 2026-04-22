package com.example.Ex.Entity;

public class BankAccount extends FinancialAccount {



    private String holderName;
    private String bankName;
    private int bankCode;
    private int bankBranchCode;
    private int bankAccountNumber;
    private int bankAccountKey;

    public BankAccount(){}
    public BankAccount(String holderName, String bankName, int bankCode, int bankBranchCode, int bankAccountNumber, int bankAccountKey) {
        this.holderName = holderName;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.bankBranchCode = bankBranchCode;
        this.bankAccountNumber = bankAccountNumber;
        this.bankAccountKey = bankAccountKey;
    }


    public String getHolderName() {
        return holderName;
    }

    public BankAccount setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public String getBankName() {
        return bankName;
    }

    public BankAccount setBankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public int getBankCode() {
        return bankCode;
    }

    public BankAccount setBankCode(int bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    public int getBankBranchCode() {
        return bankBranchCode;
    }

    public BankAccount setBankBranchCode(int bankBranchCode) {
        this.bankBranchCode = bankBranchCode;
        return this;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public BankAccount setBankAccountNumber(int bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    public int getBankAccountKey() {
        return bankAccountKey;
    }

    public BankAccount setBankAccountKey(int bankAccountKey) {
        this.bankAccountKey = bankAccountKey;
        return this;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "holderName='" + holderName + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankCode=" + bankCode +
                ", bankBranchCode=" + bankBranchCode +
                ", bankAccountNumber=" + bankAccountNumber +
                ", bankAccountKey=" + bankAccountKey +
                ", id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }


}