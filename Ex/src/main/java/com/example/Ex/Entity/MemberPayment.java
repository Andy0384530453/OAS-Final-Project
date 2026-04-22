package com.example.Ex.Entity;

public class MemberPayment {



    private String id;
    private String memberId;
    private int amount;
    private String paymentMode;
    private String accountCreditedId;
    private String creationDate;
    private String membershipFeeId;

    public MemberPayment() {}

    public MemberPayment(String id, String memberId, int amount, String paymentMode,
                         String accountCreditedId, String creationDate, String membershipFeeId) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.accountCreditedId = accountCreditedId;
        this.creationDate = creationDate;
        this.membershipFeeId = membershipFeeId;
    }

    public String getId() {
        return id;
    }

    public MemberPayment setId(String id) {
        this.id = id;
        return this;
    }

    public String getMemberId() {
        return memberId;
    }

    public MemberPayment setMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public MemberPayment setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public MemberPayment setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public String getAccountCreditedId() {
        return accountCreditedId;
    }

    public MemberPayment setAccountCreditedId(String accountCreditedId) {
        this.accountCreditedId = accountCreditedId;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public MemberPayment setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getMembershipFeeId() {
        return membershipFeeId;
    }

    public MemberPayment setMembershipFeeId(String membershipFeeId) {
        this.membershipFeeId = membershipFeeId;
        return this;
    }


    @Override
    public String toString() {
        return "MemberPayment{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", amount=" + amount +
                ", paymentMode='" + paymentMode + '\'' +
                ", accountCreditedId='" + accountCreditedId + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", membershipFeeId='" + membershipFeeId + '\'' +
                '}';
    }


}