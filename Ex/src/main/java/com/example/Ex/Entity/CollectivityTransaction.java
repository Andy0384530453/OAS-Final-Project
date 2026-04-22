package com.example.Ex.Entity;

public class CollectivityTransaction {




    private String id;
    private String collectivityId;
    private String creationDate;
    private double amount;
    private String paymentMode;
    private String accountCreditedId;
    private String memberDebitedId;




    public CollectivityTransaction() {}
    public CollectivityTransaction(String id, String collectivityId, String creationDate,
                                   double amount, String paymentMode, String accountCreditedId, String memberDebitedId) {
        this.id = id;
        this.collectivityId = collectivityId;
        this.creationDate = creationDate;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.accountCreditedId = accountCreditedId;
        this.memberDebitedId = memberDebitedId;
    }





    public String getId() {
        return id;
    }

    public CollectivityTransaction setId(String id) {
        this.id = id;
        return this;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public CollectivityTransaction setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public CollectivityTransaction setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public CollectivityTransaction setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public CollectivityTransaction setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public String getAccountCreditedId() {
        return accountCreditedId;
    }

    public CollectivityTransaction setAccountCreditedId(String accountCreditedId) {
        this.accountCreditedId = accountCreditedId;
        return this;
    }

    public String getMemberDebitedId() {
        return memberDebitedId;
    }

    public CollectivityTransaction setMemberDebitedId(String memberDebitedId) {
        this.memberDebitedId = memberDebitedId;
        return this;
    }


    @Override
    public String toString() {
        return "CollectivityTransaction{" +
                "id='" + id + '\'' +
                ", collectivityId='" + collectivityId + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", amount=" + amount +
                ", paymentMode='" + paymentMode + '\'' +
                ", accountCreditedId='" + accountCreditedId + '\'' +
                ", memberDebitedId='" + memberDebitedId + '\'' +
                '}';
    }





}