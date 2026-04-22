package com.example.Ex.Entity;

public class Member {

    private String id;
    private  String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String address;
    private String profession;
    private String phoneNumber;
    private String email;
    private String occupation;
    private boolean registrationFeePaid;
    private  boolean membershipDuesPaid;
    private String collectivityId;

    public Member() {
    }


    public Member(String id, String firstName, String lastName, String birthDate,
                  String gender, String address, String profession, String phoneNumber,
                  String email, String occupation, boolean registrationFeePaid,
                  boolean membershipDuesPaid, String collectivityId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.profession = profession;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.occupation = occupation;
        this.registrationFeePaid = registrationFeePaid;
        this.membershipDuesPaid = membershipDuesPaid;
        this.collectivityId = collectivityId;
    }


    public String getId() {
        return id;
    }

    public Member setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Member setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Member setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Member setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Member setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Member setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getProfession() {
        return profession;
    }

    public Member setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Member setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public Member setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public boolean isRegistrationFeePaid() {
        return registrationFeePaid;
    }

    public Member setRegistrationFeePaid(boolean registrationFeePaid) {
        this.registrationFeePaid = registrationFeePaid;
        return this;
    }

    public boolean isMembershipDuesPaid() {
        return membershipDuesPaid;
    }

    public Member setMembershipDuesPaid(boolean membershipDuesPaid) {
        this.membershipDuesPaid = membershipDuesPaid;
        return this;
    }

    public String getCollectivityId() {
        return collectivityId;
    }

    public Member setCollectivityId(String collectivityId) {
        this.collectivityId = collectivityId;
        return this;
    }




    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", occupation='" + occupation + '\'' +
                ", registrationFeePaid=" + registrationFeePaid +
                ", membershipDuesPaid=" + membershipDuesPaid +
                ", collectivityId='" + collectivityId + '\'' +
                '}';
    }











}
