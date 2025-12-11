package com.example.demo.util;

import com.example.demo.model.Student;

public class StudentNormalizeUtil {

    private StudentNormalizeUtil() {
        // util class, instance oluşturulmasın
    }

    public static void normalize(Student student) {
        if (student == null) return;

        if (student.getLastName() != null) {
            student.setLastName(
                    student.getLastName()
                            .trim()
                            .toUpperCase()
            );
        }

        if (student.getEmail() != null) {
            student.setEmail(
                    student.getEmail()
                            .trim()
                            .toLowerCase()
            );
        }

        if (student.getFirstName() != null) {
            student.setFirstName(
                    capitalize(student
                            .getFirstName()
                            .trim())
            );
        }

        if (student.getPhoneNumber() != null) {
            student.setPhoneNumber(student
                    .getPhoneNumber()
                    .trim());
        }

        if (student.getTcNo() != null) {
            student.setTcNo(student
                    .getTcNo()
                    .trim());
        }
    }

    private static String capitalize(String text) {
        text = text.trim();
        if (text.isEmpty()) return text;

        return text.substring(0, 1).toUpperCase() +
                text.substring(1).toLowerCase();
    }
}
