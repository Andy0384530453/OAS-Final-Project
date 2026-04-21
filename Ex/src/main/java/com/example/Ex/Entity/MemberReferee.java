package com.example.Ex.Entity;

public class MemberReferee {




    private String memberId;
    private String refereeId;
    private String relationship;

    public MemberReferee(String memberId, String refereeId, String relationship) {
        this.memberId = memberId;
        this.refereeId = refereeId;
        this.relationship = relationship;
    }



    public String getMemberId() {
        return memberId;
    }

    public MemberReferee setMemberId(String memberId) {
        this.memberId = memberId;
        return this;
    }

    public String getRefereeId() {
        return refereeId;
    }

    public MemberReferee setRefereeId(String refereeId) {
        this.refereeId = refereeId;
        return this;
    }

    public String getRelationship() {
        return relationship;
    }

    public MemberReferee setRelationship(String relationship) {
        this.relationship = relationship;
        return this;
    }

    @Override
    public String toString() {
        return "MemberReferee{" +
                "memberId='" + memberId + '\'' +
                ", refereeId='" + refereeId + '\'' +
                ", relationship='" + relationship + '\'' +
                '}';
    }
}
