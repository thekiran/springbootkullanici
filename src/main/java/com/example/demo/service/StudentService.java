package com.example.demo.service;

import com.example.demo.dto.NameResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.PhoneMaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        normalizeStudent(student);
        validateStudentForCreate(student);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Öğrenci bulunamadı: " + id));
    }

    public Student updateStudent(Integer id, Student updatedData) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Öğrenci bulunamadı: " + id));

        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhoneNumber(updatedData.getPhoneNumber());

        normalizeStudent(existing);
        validateStudentForUpdate(existing);

        return studentRepository.save(existing);
    }

    public boolean deleteStudentById(int id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    // ---------- VALIDASYON ----------

    private void validateStudentForCreate(Student student) {
        basicFieldChecks(student);

        // Yeni kayıt → email herhangi bir öğrencide olmamalı
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Bu email zaten kayıtlı");
        }
    }

    private void validateStudentForUpdate(Student student) {
        basicFieldChecks(student);

        // Update → aynı email’e sahip ama FARKLI id’li öğrenci var mı?
        if (studentRepository.existsByEmailAndIdNot(student.getEmail(), student.getId())) {
            throw new IllegalArgumentException("Bu email başka bir öğrenciye ait");
        }
    }

    private void basicFieldChecks(Student student) {
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
    }

    // ---------- NORMALİZASYON ----------

    private void normalizeStudent(Student student) {
        if (student.getLastName() != null) {
            student.setLastName(
                    student.getLastName()
                            .trim()
                            .toUpperCase()
            );
        }

        if(student.getEmail() != null) {
            student.setEmail(
                    student.getEmail()
                            .trim()
                            .toLowerCase()
            );
        }

        if (student.getFirstName() != null) {
            student.setFirstName(
                    capitalize(student.getFirstName())
            );
        }

        if (student.getPhoneNumber() != null) {
            student.setPhoneNumber(student.getPhoneNumber().trim());
        }
    }

    private String capitalize(String text) {
        text = text.trim();
        if (text.isEmpty()) return text;

        return text.substring(0, 1).toUpperCase() +
                text.substring(1).toLowerCase();
    }

    public String maskPhoneNumber(String phone) {
        return PhoneMaskUtil.mask(phone);
    }

    // NameResponse
    public NameResponse processName(String firstName) {
        return new NameResponse(firstName, firstName.length());
    }

    public NameResponse processEmail(String email) {
        return new NameResponse(email, email.length());
    }
}
