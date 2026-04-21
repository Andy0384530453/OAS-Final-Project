package main;

import java.util.List;

public class Collectivities {
    int id;
    String location;
    Boolean federationApproval;
    String gender;
    List<Members> members;
    public Collectivities(int id, String location, Boolean federationApproval, String gender, List<Members> members) {
        this.id = id;
        this.location = location;
        this.federationApproval = federationApproval;
        this.gender = gender;
        this.members = members;
    }
 
    public Collectivities() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public Boolean getFederationApproval() {
        return federationApproval;
    }
    public void setFederationApproval(Boolean federationApproval) {
        this.federationApproval = federationApproval;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public List<Members> getMembers() {
        return members;
    }
    public void setMembers(List<Members> members) {
        this.members = members;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + ((federationApproval == null) ? 0 : federationApproval.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((members == null) ? 0 : members.hashCode());
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
        Collectivities other = (Collectivities) obj;
        if (id != other.id)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (federationApproval == null) {
            if (other.federationApproval != null)
                return false;
        } else if (!federationApproval.equals(other.federationApproval))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (members == null) {
            if (other.members != null)
                return false;
        } else if (!members.equals(other.members))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Collectivities [id=" + id + ", location=" + location + ", federationApproval=" + federationApproval
                + ", gender=" + gender + ", members=" + members + "]";
    }
}
