package main;

public class Members {
    int id;
    String firstName;
    String lastName;
    String address;
    String profession;
    Long phoneNumber;
    String email;
    Boolean registrationFeePaid;
    Boolean memberShipDuePaid;

    public Members(int id, String firstName, String lastName, String address, String profession, Long phoneNumber,
            String email, Boolean registrationFeePaid, Boolean memberShipDuePaid) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registrationFeePaid = registrationFeePaid;
        this.memberShipDuePaid = memberShipDuePaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getRegistrationFeePaid() {
        return registrationFeePaid;
    }

    public void setRegistrationFeePaid(Boolean registrationFeePaid) {
        this.registrationFeePaid = registrationFeePaid;
    }

    public Boolean getMemberShipDuePaid() {
        return memberShipDuePaid;
    }

    public void setMemberShipDuePaid(Boolean memberShipDuePaid) {
        this.memberShipDuePaid = memberShipDuePaid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((profession == null) ? 0 : profession.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((registrationFeePaid == null) ? 0 : registrationFeePaid.hashCode());
        result = prime * result + ((memberShipDuePaid == null) ? 0 : memberShipDuePaid.hashCode());
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
        Members other = (Members) obj;
        if (id != other.id)
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (profession == null) {
            if (other.profession != null)
                return false;
        } else if (!profession.equals(other.profession))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (registrationFeePaid == null) {
            if (other.registrationFeePaid != null)
                return false;
        } else if (!registrationFeePaid.equals(other.registrationFeePaid))
            return false;
        if (memberShipDuePaid == null) {
            if (other.memberShipDuePaid != null)
                return false;
        } else if (!memberShipDuePaid.equals(other.memberShipDuePaid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Members [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
                + ", profession=" + profession + ", phoneNumber=" + phoneNumber + ", email=" + email
                + ", registrationFeePaid=" + registrationFeePaid + ", memberShipDuePaid=" + memberShipDuePaid + "]";
    }

    
    
}
