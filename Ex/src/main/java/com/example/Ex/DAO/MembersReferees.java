package main.java.com.example.Ex.DAO;


public class MembersReferees {
 
    private String memberId;
    private String refereeId;
    public MembersReferees(String memberId, String refereeId) {
        this.memberId = memberId;
        this.refereeId = refereeId;
    }
    public MembersReferees() {
    }
    public String getMember() {
        return memberId;
    }
    public void setMember(String memberId) {
        this.memberId = memberId;
    }
    public String getRefereeId() {
        return refereeId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
        result = prime * result + ((refereeId == null) ? 0 : refereeId.hashCode());
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
        MembersReferees other = (MembersReferees) obj;
        if (memberId == null) {
            if (other.memberId != null)
                return false;
        } else if (!memberId.equals(other.memberId))
            return false;
        if (refereeId == null) {
            if (other.refereeId != null)
                return false;
        } else if (!refereeId.equals(other.refereeId))
            return false;
        return true;
    }
    
    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }
    @Override
    public String toString() {
        return "MembersReferees [memberId=" + memberId + ", refereeId=" + refereeId + "]";
    }

    
    
}
