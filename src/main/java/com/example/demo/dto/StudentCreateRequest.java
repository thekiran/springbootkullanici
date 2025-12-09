//(POST /students)
package com.example.demo.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentCreateRequest {

    @NotBlank(message = "İsim boş olamaz")
    private String firstName;
    @NotBlank(message = "Soyisim boş olamaz")
    private String lastName;
    @NotBlank( message = "Email boş olamaz")
    @Email(message = "Geçerli bir email girin")
    private String email;
    @NotBlank(message = "Telefon numarası boş olamaz")
    @Size(min = 10, max = 20,message = "Telefon numarası uzunluğu geçersiz")
    private String telephone;

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
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
