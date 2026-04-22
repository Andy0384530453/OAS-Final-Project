package com.example.Ex.DTO;

import com.example.Ex.Entity.FinancialAccount;
import com.example.Ex.Entity.Member;

public class CollectivityTransactionDTO {
    private String id;
    private String creationDate;
    private double amount;
    private String paymentMode;
    private FinancialAccount accountCredited;
    private Member memberDebited;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public FinancialAccount getAccountCredited() { return accountCredited; }
    public void setAccountCredited(FinancialAccount accountCredited) { this.accountCredited = accountCredited; }
    public Member getMemberDebited() { return memberDebited; }
    public void setMemberDebited(Member memberDebited) { this.memberDebited = memberDebited; }
}