package com.example.demo.util;

import com.example.demo.model.Student;

public class StudentValidator {

    private StudentValidator() {
        // util class, instance oluşturulmasın
    }

    public static void basicFieldChecks(Student student) {
        if (student.getFirstName() == null || student.getFirstName().isBlank()) {
            throw new IllegalArgumentException("Ad alanı boş olamaz");
        }

        if (student.getLastName() == null || student.getLastName().isBlank()) {
            throw new IllegalArgumentException("Soyad alanı boş olamaz");
        }

        if (student.getEmail() == null || student.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email alanı boş olamaz");
        }

        if (student.getPhoneNumber() == null || student.getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("Telefon alanı boş olamaz");
        }

        if (student.getTcNo() == null || student.getTcNo().isBlank()) {
            throw new IllegalArgumentException("Tc no boş olamaz");
        }
    }
}
