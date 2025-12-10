//(GET /students, GET /students/{id} vs.)
package com.example.demo.dto;

public class StudentResponse {
    private  int id;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String maskedPhoneNumber;
    private String maskedtcNo;

    public StudentResponse() {}

    public StudentResponse(int id, String firstName, String lastName, String email, String maskedPhoneNumber, String maskedtcNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.maskedPhoneNumber = maskedPhoneNumber;
        this.maskedtcNo = maskedtcNo;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMaskedPhoneNumber() {
        return maskedPhoneNumber;
    }
    public void setMaskedPhoneNumber(String maskedPhoneNumber) {
        this.maskedPhoneNumber = maskedPhoneNumber;
    }
    public String getMaskedtcNo() {return maskedtcNo;}
    public void setMaskedtcNo(String maskedtcNo) {this.maskedtcNo = maskedtcNo;}
}
