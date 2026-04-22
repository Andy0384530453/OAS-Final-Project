package com.example.Ex.Entity;

public abstract class FinancialAccount {




    protected String id;
    protected double amount;

    public FinancialAccount(){}



    public FinancialAccount(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }


    public String getId() {
        return id;
    }

    public FinancialAccount setId(String id) {
        this.id = id;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public FinancialAccount setAmount(double amount) {
        this.amount = amount;
        return this;
    }
    @Override
    public String toString() {
        return "FinancialAccount{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }

}