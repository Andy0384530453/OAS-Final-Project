package com.example.Ex.Entity;

public class CollectivityLocalStatistics {
    Member member;
    Long earnedAmount;
    Long unpaidAmount;

    

    public CollectivityLocalStatistics() {
    }

    public CollectivityLocalStatistics(Member member, Long earnedAmount, Long unpaidAmount) {
        this.member = member;
        this.earnedAmount = earnedAmount;
        this.unpaidAmount = unpaidAmount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getEarnedAmount() {
        return earnedAmount;
    }

    public void setEarnedAmount(Long earnedAmount) {
        this.earnedAmount = earnedAmount;
    }

    public Long getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Long unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((member == null) ? 0 : member.hashCode());
        result = prime * result + ((earnedAmount == null) ? 0 : earnedAmount.hashCode());
        result = prime * result + ((unpaidAmount == null) ? 0 : unpaidAmount.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CollectivityLocalStatistics other = (CollectivityLocalStatistics) obj;
        if (member == null) {
            if (other.member != null)
                return false;
        } else if (!member.equals(other.member))
            return false;
        if (earnedAmount == null) {
            if (other.earnedAmount != null)
                return false;
        } else if (!earnedAmount.equals(other.earnedAmount))
            return false;
        if (unpaidAmount == null) {
            if (other.unpaidAmount != null)
                return false;
        } else if (!unpaidAmount.equals(other.unpaidAmount))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CollectivityLocalStatistics [member=" + member + ", earnedAmount=" + earnedAmount + ", unpaidAmount="
                + unpaidAmount + "]";
    }

    
}
