//(PUT /students/{id})
package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentUpdateRequest {

    @NotBlank(message = "İsim boş olamaz")
    private String firstName;

    @NotBlank(message = "Soyisim boş olamaz")
    private String lastName;

    @NotBlank(message = "Email boş olamaz")
    @Email(message = "Geçerli bir email girin")
    private String email;

    @NotBlank(message = "Telefon numarası boş olamaz")
    @Size(min = 10, max = 20, message = "Telefon numarası uzunluğu geçersiz")
    private String phoneNumber;

    @NotBlank(message = "TC no boş olamaz")
    @Size(min =11,max = 11,message = "numarası uzunluğu geçersiz")
    private String tcNo;

    // GETTER / SETTER

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTcNo() {return tcNo;}

    public void setTcNo(String tcNo) {this.tcNo = tcNo;}
}
